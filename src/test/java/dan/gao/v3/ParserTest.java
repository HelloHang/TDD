package dan.gao.v3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {

  private Parser parser;
  private Schema schema;

  @Before
  public void init() {
    parser = new Parser();
    schema = new Schema(new String[]{"l:boolean","p:int","d:String"});
  }

  @Test
  public void test(){
    String[] input = {"l:boolean","p:int","d:String"};
    Schema schema = new Schema(input);
    assertEquals(schema.getType("l"),"boolean");
    assertEquals(schema.getType("p"),"int");
    assertEquals(schema.getType("d"),"String");
    assertTrue(schema.isValidParam("l"));
    assertFalse(schema.isValidParam("q"));
  }

  @Test
  public void testParse_EmptyInput(){
    String[] input = {};
    parser.parse(input, schema);
    assertEquals(parser.getValue("l"), Boolean.FALSE);
    assertEquals(parser.getValue("p"), 0);
    assertEquals(parser.getValue("d"),"");
  }

  @Test
  public void testParse_SingleParam_boolean(){
    String[] input = {"-l"};
    parser.parse(input,schema);
    assertEquals(parser.getValue("l"), Boolean.TRUE);
  }

  @Test
  public void testSingle_int(){
    String[] input = {"-p", "8080"};
    parser.parse(input, schema);
    assertEquals(parser.getValue("p"), 8080);
  }

  @Test
  public void testSingle_String(){
    String[] input = {"-d", "/var"};
    parser.parse(input, schema);
    assertEquals(parser.getValue("d"), "/var");
  }



}