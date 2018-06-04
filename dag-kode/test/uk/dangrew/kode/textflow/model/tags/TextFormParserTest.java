package uk.dangrew.kode.textflow.model.tags;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.function.BiConsumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class TextFormParserTest {

   private TextFlowFormat format;
   private TextFlowSchema schema;
   @Mock private BiConsumer< TextFlowBuilder, String > builderFunction;
   private TextFlowBuilder builder;
   private TextFormParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      schema = TextFlowSchema.Bold;
      systemUnderTest = new TextFormParser( format, schema, builderFunction );
   }//End Method

   @Test public void shouldParseLastEntry() {
      assertThat( systemUnderTest.parse( 
               "[b]this is the text", 
               ( "[b]this is the text" ).length(), 
               builder 
      ), is( "[b]this is the text" ) );
      verify( builderFunction ).accept( builder, "this is the text" );
   }//End Method
   
   @Test public void shouldParseIntermediate() {
      assertThat( systemUnderTest.parse( 
               "[b]this is the text[n]something to not include", 
               ( "[b]this is the text" ).length(), 
               builder 
      ), is( "[b]this is the text" ) );
      verify( builderFunction ).accept( builder, "this is the text" );
   }//End Method
   
   @Test public void shouldParseAndAccountForProgressThroughInput() {
      assertThat( systemUnderTest.parse( 
               "[b]this is the text[n]something to not include", 
               ( "[b]this is the text" ).length(),
               builder 
      ), is( "[b]this is the text" ) );
      verify( builderFunction ).accept( builder, "this is the text" );
   }//End Method

}//End Class
