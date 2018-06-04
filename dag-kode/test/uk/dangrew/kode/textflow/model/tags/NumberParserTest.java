package uk.dangrew.kode.textflow.model.tags;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class NumberParserTest {

   private TextFlowFormat format;
   private TextFlowSchema schema;
   @Mock private TextFlowBuilder builder;
   private NumberParser systemUnderTest;
   
   private TextFlowBuilder builtBuilder;
   private String builtParsedValue;
   private Double builtNumber;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      schema = TextFlowSchema.FontSize;
      systemUnderTest = new NumberParser( format, schema ) {
         
         @Override protected String build( 
                  TextFlowBuilder builder, 
                  String parsedValue, 
                  Double number 
         ) {
            builtBuilder = builder;
            builtParsedValue = parsedValue;
            builtNumber = number;
            return null;
         }//End Method
      };
   }//End Method

   @Test public void shouldParseLastEntry() {
      systemUnderTest.parse( 
               schema.token( format ) + format.wrap( 35.0 ), 
               0, 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( "35.0" ) );
      assertThat( builtNumber, is( 35.0 ) );
   }//End Method
   
   @Test public void shouldParseIntermediateEntry() {
      systemUnderTest.parse( 
               schema.token( format ) + format.wrap( 35.0 )
               + format.boldTag() + format.wrap( "anything" ), 
               0, 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( "35.0" ) );
      assertThat( builtNumber, is( 35.0 ) );
   }//End Method
   
   @Test public void shouldHandleInvalidNumber() {
      systemUnderTest.parse( 
               schema.token( format ) + format.wrap( "not-a-number" )
               + format.boldTag() + format.wrap( "anything" ), 
               ( schema.token( format ) + format.wrap( "not-a-number" ) ).length(), 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( "not-a-number" ) );
      assertThat( builtNumber, is( nullValue() ) );
   }//End Method
   
   @Test public void shouldHandleNoNumber() {
      systemUnderTest.parse( 
               schema.token( format ) + "not-a-number"
               + format.boldTag() + format.wrap( "anything" ), 
               ( schema.token( format ) + "not-a-number" ).length(), 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( nullValue() ) );
      assertThat( builtNumber, is( nullValue() ) );
   }//End Method
   
   @Test public void shouldIgnoreProgress() {
      systemUnderTest.parse( 
               schema.token( format ) + format.wrap( 35.0 )
               + format.boldTag() + format.wrap( "anything" ), 
               109, 
               builder 
      );
      
      assertThat( builtBuilder, is( builder ) );
      assertThat( builtParsedValue, is( "35.0" ) );
      assertThat( builtNumber, is( 35.0 ) );
   }//End Method
   
}//End Class
