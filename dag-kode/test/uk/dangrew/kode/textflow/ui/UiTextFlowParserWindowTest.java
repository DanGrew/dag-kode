package uk.dangrew.kode.textflow.ui;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.text.TextFlow;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.textflow.model.TextFlowParser;

public class UiTextFlowParserWindowTest {
   
   @Mock private TextFlowParser parser;
   private UiTextFlowParserWindow systemUnderTest;
   
   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new UiTextFlowParserWindow( parser );
   }//End Method

   @Ignore
   @Test public void manual() throws InterruptedException {
      TestApplication.launch( () -> new UiTextFlowParserWindow() );
      
      Thread.sleep( 9999999 );
   }//End Method

   @Test public void shouldSetParsedFlow() {
      TextFlow flow = new TextFlow();
      when( parser.parse( "anything" ) ).thenReturn( Optional.of( flow ) );
      
      systemUnderTest.inputArea().setText( "anything" );
      assertThat( systemUnderTest.textWrapper().getCenter(), is( flow ) );
   }//End Method

}//End Class
