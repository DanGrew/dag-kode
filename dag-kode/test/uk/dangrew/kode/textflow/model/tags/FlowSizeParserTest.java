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

public class FlowSizeParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private FlowSizeParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new FlowSizeParser( format );
   }//End Method

   @Test public void shouldResetSize() {
      assertThat( systemUnderTest.build( 
               builder, 
               "anything",
               null
      ), is( format.flowSizeTag() ) );
      verify( builder ).resetTextSize();
   }//End Method
   
   @Test public void shouldBuildSize() {
      assertThat( systemUnderTest.build( 
               builder, 
               "20.0",
               20.0
      ), is( format.flowSizeTag() + format.wrap( 20.0 ) ) );
      verify( builder ).withFontSize( 20.0 );
   }//End Method

}//End Class
