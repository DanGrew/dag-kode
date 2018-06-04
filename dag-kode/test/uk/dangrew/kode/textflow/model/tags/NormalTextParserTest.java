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

public class NormalTextParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private NormalTextParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new NormalTextParser( format );
   }//End Method

   @Test public void shouldBuildText() {
      assertThat( systemUnderTest.parse( 
               format.normalTag() + "some text", 
               ( format.normalTag() + "some text" ).length(),
               builder 
      ), is( format.normalTag() + "some text" ) );
      verify( builder ).normal( "some text" );
   }//End Method

}//End Class
