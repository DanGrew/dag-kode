package uk.dangrew.kode.javafx.custom;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import uk.dangrew.kode.javafx.registrations.ButtonActionBindingImpl;
import uk.dangrew.kode.launch.TestApplication;

public class ResponsiveButtonRegionTest {

   private Button button;
   @Mock private EventHandler< ActionEvent > handler;
   private ResponsiveButtonRegion systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      button = new Button( "anything" );
      systemUnderTest = new ResponsiveButtonRegion( button, handler );
   }//End Method

   @Test public void shouldProvideProperties() {
      assertThat( systemUnderTest.region(), is( button ) );
      assertThat( systemUnderTest.registration(), is( instanceOf( ButtonActionBindingImpl.class ) ) );
   }//End Method
   
}//End Class
