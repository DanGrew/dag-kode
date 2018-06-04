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

public class TextColourParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private TextColourParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new TextColourParser( format );
   }//End Method

   @Test public void shouldResetColour() {
      assertThat( systemUnderTest.build( 
               builder, 
               "RED", 
               Color.RED 
      ), is( format.textColourTag() + format.wrap( "RED" ) ) );
      verify( builder ).withTextColour( Color.RED );
   }//End Method
   
   @Test public void shouldBuildColour() {
      assertThat( systemUnderTest.build( 
               builder, 
               null, 
               null 
      ), is( format.textColourTag() ) );
      verify( builder ).resetTextColour();
   }//End Method

}//End Class
