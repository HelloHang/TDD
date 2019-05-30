package dan.gao.v2;

/**
 * @author: Daniels Gao
 * @date: 2019/5/29 22:53
 */
public class Field<T> {

  private String name;

  private Class<T> type;

  private T defaultValue;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Class<T> getType() {
    return type;
  }

  public void setType(Class<T> type) {
    this.type = type;
  }

  public T getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(T defaultValue) {
    this.defaultValue = defaultValue;
  }
}
