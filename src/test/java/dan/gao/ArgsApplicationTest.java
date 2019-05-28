package dan.gao;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


/**
 * Created by dangao on 5/28/2019.
 */
public class ArgsApplicationTest
{
	private ArgsApplication argsApplication;

	@Before
	public void init()
	{
		argsApplication = new ArgsApplication();
	}

	@Test
	public void testConvertArgs()
	{
		String[] args = { "-l", "-p", "8080", "-d", "/root" };
		Map<String, String> result = argsApplication.convertArgs(args);
		assertTrue(result.containsKey("l"));
		assertNull(result.get("l"));
		assertEquals("8080",result.get("p"));
		assertEquals("/root",result.get("d"));
	}


	@Test(expected = IllegalArgumentException.class)
	public void testConvertArgsIllegalArgument()
	{
		String[] args = { "-l", "8080", "90" };
		argsApplication.convertArgs(args);
	}

	@Test
	public void testAnalyzeSchema()
	{
		String fileName = "schema.xml";
		List<ParameterModel> params = argsApplication.resolveParameter(fileName);
		assertEquals(params.stream().map(ParameterModel::getName).collect(Collectors.joining(",")),"l,p,d");
		assertEquals(params.stream().map(ParameterModel::getDefaultValue).collect(Collectors.joining(",")),"true,9001,/var");
		assertEquals(params.stream().map(p -> p.getType().getName()).collect(Collectors.joining(",")), "Boolean,Integer,String");
	}

}