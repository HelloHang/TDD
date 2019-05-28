package dan.gao;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * Created by dangao on 5/28/2019.
 */
public class ArgsApplication {

  public static void main(String[] args) throws DocumentException {
    ArgsApplication argsApplication = new ArgsApplication();
    Result result = argsApplication.validate(argsApplication.convertArgs(args),
          argsApplication.resolveParameter("schema.xml"));
    System.out.println(result.getMessage());
  }

  public Map<String, String> convertArgs(String[] args) {
    Map<String, String> argMap = new HashMap<>();
    String previousParam = "";
    for (String arg : args) {
      if (previousParam.startsWith("-")) {
        if (arg.startsWith("-")) {
          argMap.put(arg.substring(1), null);
        } else {
          argMap.put(previousParam.substring(1), arg);
        }
      } else {
        if (arg.startsWith("-")) {
          argMap.put(arg.substring(1), null);
        } else {
          throw new IllegalArgumentException("Invalid parameter error");
        }
      }
      previousParam = arg;
    }
    return argMap;
  }

  public List<ParameterModel> resolveParameter(String fileName)
      throws DocumentException {
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

    List<ParameterModel> parameterModelList = new ArrayList<>();
    SAXReader reader = new SAXReader();
    Document document = reader.read(in);
    Element root = document.getRootElement();
    Iterator<Element> iterator = root.elementIterator();
    while (iterator.hasNext()) {
      Element element = iterator.next();
      List<Attribute> attributes = element.attributes();
      ParameterModel parameterModel = new ParameterModel();
      parameterModelList.add(parameterModel);
      for (Attribute attribute : attributes) {
        String attributeName = attribute.getName();
        String attributeValue = attribute.getValue();
        if (attributeName == "name") {
          parameterModel.setName(attributeValue);
        }
        if (attributeName == "class") {
          parameterModel.setType(getClassFor(attributeValue));
        }
        if (attributeName == "defaultValue") {
          parameterModel.setDefaultValue(attributeValue);
        }
      }
    }
    return parameterModelList;
  }

  private Class getClassFor(String attributeValue) {
    if ("int".equals(attributeValue)) {
      return Integer.class;
    }
    if ("boolean".endsWith(attributeValue)) {
      return Boolean.class;
    }
    if ("String".equals(attributeValue)) {
      return String.class;
    }
    throw new IllegalArgumentException("Only support int, boolean and String");
  }

  public Result validate(Map<String, String> map, List<ParameterModel> parameterModels) {
    Result result = new Result();
    map.forEach((key, value) -> {
      if (parameterModels.stream().map(ParameterModel::getName).anyMatch(n -> n.equals(key))
          ) {
        ParameterModel parameterModel = parameterModels.stream()
            .filter(p -> p.getName().equals(key)).findFirst().get();
        try {
          Class aClass = parameterModel.getType();
          if (Integer.class.equals(aClass)) {
            Integer.valueOf(value);
          }
          if (String.class.equals(aClass)) {
            String.valueOf(value);
          }
          if (Boolean.class.equals(aClass)) {
            Boolean.valueOf(value);
          }
          if (value == null) {
            map.put(key, parameterModel.getDefaultValue());
          }
        } catch (NumberFormatException e) {
          result.setStatus(false);
          result.setMessage(String.format("Invalid Parameter Value: %s", value));
          return;
        }
      } else {
        result.setStatus(false);
        result.setMessage(String.format("Invalid Parameter: %s", key));
        return;
      }
    });
    if (result.isStatus() == null) {
      result.setStatus(true);
      result.setMessage(map.toString());
    }
    return result;
  }
}
