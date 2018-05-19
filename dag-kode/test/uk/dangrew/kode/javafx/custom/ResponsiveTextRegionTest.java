package uk.dangrew.kode.javafx.custom;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.launch.TestApplication;

public class ResponsiveTextRegionTest {

   private TextField field;
   @Mock private ChangeListener< String > listener;
   private ResponsiveTextRegion systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      field = new TextField();
      systemUnderTest = new ResponsiveTextRegion( field, listener );
   }//End Method

   @Test public void shouldNotifyWhenTextChanges() {
      new RegistrationManager().apply( systemUnderTest.registration() );
      field.setText( "anything" );
      verify( listener ).changed( field.textProperty(), "", "anything" );
   }//End Method

}//End Class
