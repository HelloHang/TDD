package dan.gao.v3;

import java.util.HashMap;

/**
 * @author: Daniels Gao
 * @date: 2019/5/30 22:56
 */
public class Parser {


  private HashMap<String, Object> result;

  public void parse(String[] input, Schema schema) {
    result = new HashMap<>();
    schema.getDefineModel().forEach((key, value) -> {
      if ("boolean".equals(value)) {
        result.put(key, Boolean.FALSE);
      }
      if ("int".equals(value)) {
        result.put(key, 0);
      }
      if ("String".equals(value)) {
        result.put(key, "");
      }
    });

    for (int i = 0; i < input.length; i++) {
      if (input[i].startsWith("-")) {
        String type = schema.getType(input[i].substring(1));
        if ("boolean".equals(type)) {
          result.put(input[i].substring(1), Boolean.TRUE);
        }
        if ("int".equals(type)) {
          result.put(input[i].substring(1), Integer.valueOf(input[i + 1]));
        }
        if ("String".equals(type)) {
          result.put(input[i].substring(1), input[i + 1]);
        }
      }
    }

  }

  public Object getValue(String param) {
    return result.get(param);
  }
}
