package uk.dangrew.kode.textflow.model;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.launch.TestApplication;

public class TextFlowSchemaTest {

   private TextFlowFormat format;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      format = new TextFlowFormat();
   }//End Method
   
   @Test public void shouldProvideFunctions(){
      for ( TextFlowSchema schema : TextFlowSchema.values() ) {
         assertThat( schema.parser( format ), is( notNullValue() ) );
         assertThat( schema.parser( format ), is( not( schema.parser( format ) ) ) );
         assertThat( schema.token( format ), is( notNullValue() ) );
      }
   }//End Method

}//End Class
