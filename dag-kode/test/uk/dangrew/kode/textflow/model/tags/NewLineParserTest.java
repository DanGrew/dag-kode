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

public class NewLineParserTest {

   @Mock private TextFlowBuilder builder;
   private TextFlowFormat format;
   private NewLineParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      format = new TextFlowFormat();
      systemUnderTest = new NewLineParser( format );
   }//End Method

   @Test public void shouldParse() {
      assertThat( systemUnderTest.parse( 
               null, 
               0, 
               builder 
      ), is( format.newLineTag() ) );
      verify( builder ).newLine();
   }//End Method

}//End Class
