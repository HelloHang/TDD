package dan.gao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dangao on 5/28/2019.
 */
public class ArgsApplication
{
	public Map<String, String> convertArgs(String[] args)
	{
		Map<String, String> argMap = new HashMap<>();
		String previousParam = "";
		for (String arg : args)
		{
			if (previousParam.startsWith("-"))
			{
				if (arg.startsWith("-"))
				{
					argMap.put(arg.substring(1),null);
				}
				else
				{
					argMap.put(previousParam.substring(1), arg);
				}
			}
			else
			{
				if (arg.startsWith("-"))
				{
					argMap.put(arg.substring(1), null);
				}
				else
				{
					throw new IllegalArgumentException("Invalid parameter error");
				}
			}
			previousParam = arg;
		}
		return argMap;
	}

	public List<ParameterModel> resolveParameter(String fileName)
	{
		return null;
	}
}
