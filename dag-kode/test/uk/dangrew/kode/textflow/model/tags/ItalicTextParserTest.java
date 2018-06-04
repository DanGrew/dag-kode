package uk.dangrew.kode.textflow.model.tags;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.textflow.model.TextFlowFormat;

public class ItalicTextParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private ItalicTextParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new ItalicTextParser( format );
   }//End Method

   @Test public void shouldBuildText() {
      assertThat( systemUnderTest.parse( 
               format.italicTag() + "some text", 
               ( format.italicTag() + "some text" ).length(),
               builder 
      ), is( format.italicTag() + "some text" ) );
      verify( builder ).italic( "some text" );
   }//End Method

}//End Class
