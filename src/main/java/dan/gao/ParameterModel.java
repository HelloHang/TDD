package dan.gao;

/**
 * Created by dangao on 5/28/2019.
 */
public class ParameterModel
{
	private String name;

	private Class type;

	private String defaultValue;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Class getType()
	{
		return type;
	}

	public void setType(Class type)
	{
		this.type = type;
	}

	public String getDefaultValue()
	{
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue)
	{
		this.defaultValue = defaultValue;
	}
}
