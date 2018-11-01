package uk.dangrew.kode.concept;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.layout.BorderPane;
import uk.dangrew.kode.utility.InstanceVerifier;

public class InstanceVerifierTest {

   private InstanceVerifier< BorderPane > systemUnderTest;
   
   private static class BorderPaneExtension extends BorderPane {

   }//End Class

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new InstanceVerifier< BorderPane >( BorderPane.class );
   }//End Method

   @Test public void shouldAcceptExactObject() {
      systemUnderTest.verify( new BorderPane() );
   }//End Method
   
   @Test( expected = IllegalArgumentException.class ) public void shouldNotAcceptAnythingElse() {
      systemUnderTest.verify( mock( BorderPane.class ) );
   }//End Method
   
   @Test( expected = IllegalArgumentException.class ) public void shouldNotAcceptExtension() {
      systemUnderTest.verify( new BorderPaneExtension() );
   }//End Method

}//End Class
