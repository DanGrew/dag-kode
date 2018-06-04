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
import uk.dangrew.kode.utility.conversion.ColorConverter;

public class FlowBorderParserTest {

   private ColorConverter colours;
   private TextFlowFormat format;
   @Mock private TextFlowBuilder builder;
   private FlowBorderParser systemUnderTest;
   
   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      colours = new ColorConverter();
      format = new TextFlowFormat();
      systemUnderTest = new FlowBorderParser( format );
   }//End Method

   @Test public void shouldResetBorder() {
      assertThat( 
            systemUnderTest.parse( 
               format.flowBorderTag() + format.normalTag() + "anything", 
               0, builder 
            ),
      is( format.flowBorderTag() ) );
      
      verify( builder ).resetFlowBorder();
   }//End Method
   
   @Test public void shouldResetAsLastEntry() {
      assertThat( 
            systemUnderTest.parse( 
               format.flowBorderTag(), 
               0, builder 
            ),
      is( format.flowBorderTag() ) );
      
      verify( builder ).resetFlowBorder();
   }//End Method
   
   @Test public void shouldSetThickness() {
      assertThat( 
            systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( 20.0 ) 
               + format.normalTag() + "anything", 
               0, builder 
            ),
      is( format.flowBorderTag() + format.wrap( 20.0 ) ) );
      
      verify( builder ).withFlowBorder( 20.0 );
   }//End Method
   
   @Test public void shouldSetColour() {
      assertThat( 
            systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( "RED" ) 
               + format.normalTag() + "anything", 
               0, builder 
            ),
      is( format.flowBorderTag() + format.wrap( "RED" ) ) );
      
      verify( builder ).withFlowBorder( Color.RED );
   }//End Method
   
   @Test public void shouldSetColourAndThickness() {
      assertThat( 
            systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( "RED" ) + format.wrap( 20.0 )
               + format.normalTag() + "anything", 
               0, builder 
            ),
      is( format.flowBorderTag() + format.wrap( "RED" ) + format.wrap( 20.0 ) ) );
      
      verify( builder ).withFlowBorder( Color.RED, 20.0 );
   }//End Method
   
   @Test public void shouldSetHexColourAndThickness() {
      String testColour = colours.colorToHex( Color.RED );
      assertThat( 
            systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( testColour ) + format.wrap( 20.0 )
               + format.normalTag() + "anything", 
               0, builder 
            ),
      is( format.flowBorderTag() + format.wrap( testColour ) + format.wrap( 20.0 ) ) );
      
      verify( builder ).withFlowBorder( Color.RED, 20.0 );
   }//End Method
   
}//End Class
