package dan.gao.v3;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;


/**
 * @author: Daniels Gao
 * @date: 2019/5/30 22:56
 */
public class Parser
{
	public static final String TYPE_STRING = "String";
	public static final String TYPE_INT = "int";
	public static final String BOOLEAN = "boolean";
	public static final String PREFIX_PARAM = "-";
	private HashMap<String, Object> result;

	public void parse(String[] input, Schema schema)
	{
		result = new HashMap<>();
		schema.getDefineModel().forEach(this::initResult);
		setParamValueBy(input, schema);
	}

	private void initResult(String key, String value)
	{
		if (isBoolean(value))
		{
			result.put(key, Boolean.FALSE);
		}
		if (isIntType(value))
		{
			result.put(key, 0);
		}
		if (isStringType(value))
		{
			result.put(key, StringUtils.EMPTY);
		}
	}

	private void setParamValueBy(String[] input, Schema schema)
	{
		for (int i = 0; i < input.length; i++)
		{
			if (isParamKey(input[i]))
			{
				resolveParamKey(input, schema, i);
			}

			if (isFirstParam(i) && !isParamKey(input[i]))
			{
				throw new IllegalArgumentException("Please input a valid param.");
			}

			if (!isFirstParam(i) && !isParamKey(input[i - 1]))
			{
				throw new IllegalArgumentException("Please input a valid param");
			}
		}
	}

	private void resolveParamKey(String[] input, Schema schema, int i)
	{
		String type = schema.getType(getParamKey(input[i]));
		if (isBoolean(type))
		{
			resolveBooleanParam(input, i);
		}
		if (isIntType(type))
		{
			resolveIntParam(input, i);
		}
		if (isStringType(type))
		{
			resolveStringParam(input, i);
		}
		if (type == null)
		{
			throw new IllegalArgumentException("Invalid param");
		}
	}

	private void resolveStringParam(String[] input, int i)
	{
		if (!hasNext(input, i) || isParamKey(input[i + 1]))
		{
			throw new IllegalArgumentException("Please input a valid value for param[d]");
		}
		result.put(getParamKey(input[i]), input[i + 1]);
	}

	private void resolveIntParam(String[] input, int i)
	{
		if (!hasNext(input, i) || !StringUtils.isNumeric(input[i + 1]))
		{
			throw new IllegalArgumentException("Please input a valid value for param[p]");
		}
		result.put(getParamKey(input[i]), Integer.valueOf(input[i + 1]));
	}

	private void resolveBooleanParam(String[] input, int i)
	{
		if (hasNext(input, i) && !isParamKey(input[i + 1]))
		{
			throw new IllegalArgumentException(String.format("Invalid value[%s]", input[i + 1]));
		}
		result.put(getParamKey(input[i]), Boolean.TRUE);
	}

	private boolean isFirstParam(int i)
	{
		return i - 1 < 0;
	}

	private String getParamKey(String s)
	{
		return s.substring(1);
	}

	private boolean hasNext(String[] input, int i)
	{
		return i + 1 < input.length;
	}

	private boolean isParamKey(String str)
	{
		return StringUtils.startsWith(str, PREFIX_PARAM);
	}

	private boolean isStringType(String value)
	{
		return TYPE_STRING.equals(value);
	}

	private boolean isIntType(String value)
	{
		return TYPE_INT.equals(value);
	}

	private boolean isBoolean(String value)
	{
		return BOOLEAN.equals(value);
	}

	public Object getValue(String param)
	{
		return result.get(param);
	}
}
