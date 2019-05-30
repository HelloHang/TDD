package dan.gao.v3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Daniels Gao
 * @date: 2019/5/30 22:59
 */
public class Schema {

  private Map<String, String> defineModel;

  public Schema(String[] input) {
    defineModel = new HashMap<>();
    for (String info : input) {
      String[] keyValue = info.split(":");
      defineModel.put(keyValue[0], keyValue[1]);
    }


  }

  public Map<String, String> getDefineModel() {
    return defineModel;
  }

  public String getType(String key) {

    return defineModel.get(key);
  }

  public boolean isValidParam(String param) {
    return defineModel.keySet().contains(param);
  }
}
