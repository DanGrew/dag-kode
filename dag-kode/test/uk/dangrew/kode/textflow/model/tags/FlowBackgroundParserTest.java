package uk.dangrew.kode.textflow.model.tags;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.paint.Color;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.textflow.model.TextFlowFormat;

public class FlowBackgroundParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private FlowBackgroundParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new FlowBackgroundParser( format );
   }//End Method

   @Test public void shouldResetColour() {
      assertThat( systemUnderTest.build( 
               builder, 
               "RED", 
               Color.RED 
      ), is( format.flowBackgroundTag() + format.wrap( "RED" ) ) );
      verify( builder ).withFlowBackground( Color.RED );
   }//End Method
   
   @Test public void shouldBuildColour() {
      assertThat( systemUnderTest.build( 
               builder, 
               null, 
               null 
      ), is( format.flowBackgroundTag() ) );
      verify( builder ).resetFlowBackground();
   }//End Method

}//End Class
