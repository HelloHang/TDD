package dan.gao.v2;

import static org.junit.Assert.assertEquals;

import dan.gao.Result;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

  private Parser parser;

  private Schema schema;

  @Before
  public void init() {
    parser = new Parser();
    schema = new Schema(new String[]{"l,boolean,true","p,int,8080","d,String,/var"});
  }

  @Test
  public void testSchemaBooleanType(){
    String[] input = {"l,boolean,true"};
    Schema schema = new Schema(input);
    List<Field> fields = schema.getFields();
    assertEquals(fields.get(0).getName(), "l");
    assertEquals(fields.get(0).getType(),Boolean.class);
    assertEquals(fields.get(0).getDefaultValue(), Boolean.TRUE);
  }
  
  @Test
  public void testSchemaIntType(){
    String[] input = {"p,int,8080"};
    Schema schema = new Schema(input);
    List<Field> fields = schema.getFields();
    assertEquals(fields.get(0).getName(),"p");
    assertEquals(fields.get(0).getType(), Integer.class);
    assertEquals(fields.get(0).getDefaultValue(),8080);
  }

  @Test
  public void testSchemaStringType(){
    String[] input = {"d,String,/var"};
    Schema schema = new Schema(input);
    List<Field> fields = schema.getFields();
    assertEquals(fields.get(0).getName(), "d");
    assertEquals(fields.get(0).getType(), String.class);
    assertEquals(fields.get(0).getDefaultValue(), "/var");
  }
  
  @Test
  public void testParse_p(){
    String[] input = {"-p", "10001"};
    ParserResult result = parser.parse(input, schema);
    assertEquals(result.getValue("p"), 10001);
  }

}