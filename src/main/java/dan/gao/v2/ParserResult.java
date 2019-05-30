package dan.gao.v2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Daniels Gao
 * @date: 2019/5/30 0:00
 */
public class ParserResult {

  public ParserResult() {
    result = new HashMap<>();
  }

  private Map<String, Object> result;

  public Object getValue(String key) {
    return result.get(key);
  }

  public void setValue(String key, Object value) {
    result.put(key, value);
  }


}
