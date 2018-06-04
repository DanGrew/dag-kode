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

public class HyperlinkParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private HyperlinkParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new HyperlinkParser( format );
   }//End Method

   @Test public void shouldBuildText() {
      assertThat( systemUnderTest.parse( 
               format.hyperlinkTag() + "some text", 
               ( format.hyperlinkTag() + "some text" ).length(),
               builder 
      ), is( format.hyperlinkTag() + "some text" ) );
      verify( builder ).withHyperlink( "some text", HyperlinkParser.desktop );
   }//End Method

}//End Class