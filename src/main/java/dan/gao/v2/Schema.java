package dan.gao.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Daniels Gao
 * @date: 2019/5/29 22:50
 */
public class Schema {

  private List<Field> fields;

  public Schema(String[] input) {
    fields = new ArrayList<>();
    for (String fieldInfo : input) {
      String[] fieldInfoes = fieldInfo.split(",");
      String type = fieldInfoes[1];
      if ("boolean".equals(type)) {
        Field<Boolean> field = new Field<>();
        field.setName(fieldInfoes[0]);
        field.setType(Boolean.class);
        field.setDefaultValue(Boolean.valueOf(fieldInfoes[2]));
        fields.add(field);
      }
      if ("int".equals(type)) {
        Field<Integer> field = new Field<>();
        field.setName(fieldInfoes[0]);
        field.setType(Integer.class);
        field.setDefaultValue(Integer.valueOf(fieldInfoes[2]));
        fields.add(field);
      }
      if ("String".equals(type)) {
        Field<String> field = new Field<>();
        field.setName(fieldInfoes[0]);
        field.setType(String.class);
        field.setDefaultValue(fieldInfoes[2]);
        fields.add(field);
      }
    }
  }

  public List<Field> getFields() {
    return fields;
  }
}
