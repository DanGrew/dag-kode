package uk.dangrew.kode.javafx.registrations;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import uk.dangrew.kode.launch.TestApplication;

public class ButtonActionBindingImplTest {

   private Button button;
   @Mock private EventHandler< ActionEvent > handler;
   private ButtonActionBindingImpl systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      button = new Button( "anything" );
      systemUnderTest = new ButtonActionBindingImpl( button, handler );
   }//End Method

   @Test public void shouldRegister() {
      assertThat( button.getOnAction(), is( nullValue() ) );
      systemUnderTest.register();
      assertThat( button.getOnAction(), is( handler ) );
   }//End Method
   
   @Test public void shouldUnregister() {
      systemUnderTest.register();
      assertThat( button.getOnAction(), is( handler ) );
      systemUnderTest.unregister();
      assertThat( button.getOnAction(), is( nullValue() ) );
   }//End Method

}//End Class
