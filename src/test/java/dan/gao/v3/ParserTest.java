package dan.gao.v3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class ParserTest
{

	private Parser parser;
	private Schema schema;


	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void init()
	{
		parser = new Parser();
		schema = new Schema(new String[] { "l:boolean", "p:int", "d:String" });
	}

	@Test
	public void test()
	{
		String[] input = { "l:boolean", "p:int", "d:String" };
		Schema schema = new Schema(input);
		assertEquals(schema.getType("l"), "boolean");
		assertEquals(schema.getType("p"), "int");
		assertEquals(schema.getType("d"), "String");
		assertTrue(schema.isValidParam("l"));
		assertFalse(schema.isValidParam("q"));
	}

	@Test
	public void testParse_EmptyInput()
	{
		String[] input = {};
		parser.parse(input, schema);
		assertEquals(parser.getValue("l"), Boolean.FALSE);
		assertEquals(parser.getValue("p"), 0);
		assertEquals(parser.getValue("d"), "");
	}

	@Test
	public void testParse_SingleParam_boolean()
	{
		String[] input = { "-l" };
		parser.parse(input, schema);
		assertEquals(parser.getValue("l"), Boolean.TRUE);
	}

	@Test
	public void testSingle_int()
	{
		String[] input = { "-p", "8080" };
		parser.parse(input, schema);
		assertEquals(parser.getValue("p"), 8080);
	}

	@Test
	public void testSingle_String()
	{
		String[] input = { "-d", "/var" };
		parser.parse(input, schema);
		assertEquals(parser.getValue("d"), "/var");
	}

	@Test
	public void testParse_SingleParam_int()
	{
		String[] input = { "-p" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Please input a valid value for param[p]");
		parser.parse(input, schema);
	}

	@Test
	public void testParse_SingleParam_String()
	{
		String[] input = { "-d" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Please input a valid value for param[d]");
		parser.parse(input, schema);
	}

	@Test
	public void testParse_InvalidInt()
	{
		String[] input = { "-l", "-p", "-d", "/var" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Please input a valid value for param[p]");
		parser.parse(input, schema);
	}

	@Test
	public void test_Parse_InvalidBoolean()
	{
		String[] input = { "-l", "12331" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid value[12331]");
		parser.parse(input, schema);
	}

	@Test
	public void testParse_InvalidString()
	{
		String[] input = { "-l", "-d", "-p", "8080" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Please input a valid value for param[d]");
		parser.parse(input, schema);
	}

	@Test
	public void testParse_InvalidParam()
	{
		String[] input = { "8080" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Please input a valid param.");
		parser.parse(input, schema);
	}

	@Test
	public void testParse_InvalidParamNoKey()
	{
		String[] input = { "-d", "/var", "p" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Please input a valid param");
		parser.parse(input, schema);
	}

	@Test
	public void testParse_InvalidParamKay(){
		String[] input = { "-w", "ss" };
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Invalid param");
		parser.parse(input,schema);
	}
}