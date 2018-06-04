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

public class BoldTextParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private BoldTextParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new BoldTextParser( format );
   }//End Method

   @Test public void shouldBuildText() {
      assertThat( systemUnderTest.parse( 
               format.boldTag() + "some text", 
               ( format.boldTag() + "some text" ).length(),
               builder 
      ), is( format.boldTag() + "some text" ) );
      verify( builder ).bold( "some text" );
   }//End Method

}//End Class