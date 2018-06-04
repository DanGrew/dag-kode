package uk.dangrew.kode.textflow.model.tags;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.paint.Color;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;
import uk.dangrew.kode.utility.conversion.ColorConverter;

public class ColourParserTest {

   private ColorConverter colours;
   private TextFlowFormat format;
   private TextFlowSchema schema;
   @Mock private TextFlowBuilder builder;
   private ColourParser systemUnderTest;
   
   private TextFlowBuilder builtBuilder;
   private String builtParsedValue;
   private Color builtColour;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      colours = new ColorConverter();
      format = new TextFlowFormat();
      schema = TextFlowSchema.TextColour;
      systemUnderTest = new ColourParser( format, schema ) {
         
         @Override protected String build( 
                  TextFlowBuilder builder, 
                  String parsedValue, 
                  Color colour 
         ) {
            builtBuilder = builder;
            builtParsedValue = parsedValue;
            builtColour = colour;
            return null;
         }//End Method
      };
   }//End Method

   @Test public void shouldParseColorName() {
      systemUnderTest.parse( 
               schema.token( format ) + format.wrap( "RED" ), 
               0, 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( "RED" ) );
      assertThat( builtColour, is( Color.RED ) );
   }//End Method
   
   @Test public void shouldParseColorValue() {
      String testColour = colours.colorToHex( Color.BLUE );
      systemUnderTest.parse( 
               schema.token( format ) + format.wrap( testColour )
               + format.boldTag() + format.wrap( "anything" ), 
               0, 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( testColour ) );
      assertThat( builtColour, is( Color.BLUE ) );
   }//End Method
   
   @Test public void shouldNotParseInvalidColour() {
      systemUnderTest.parse( 
               schema.token( format ) + format.wrap( "not-a-color" )
               + format.boldTag() + format.wrap( "anything" ), 
               0, 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( "not-a-color" ) );
      assertThat( builtColour, is( nullValue() ) );
   }//End Method
   
}//End Class
