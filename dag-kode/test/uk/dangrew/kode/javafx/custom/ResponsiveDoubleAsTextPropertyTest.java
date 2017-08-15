package uk.dangrew.kode.javafx.custom;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.function.BiConsumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.launch.TestApplication;

public class ResponsiveDoubleAsTextPropertyTest {

   @Mock private BiConsumer< Double, Double > listener;
   private ResponsiveDoubleAsTextProperty systemUnderTest;
   
   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new ResponsiveDoubleAsTextProperty( 34.23, listener, false );
   }//End Method

   @Test public void shouldProvideTextField() {
      assertThat( systemUnderTest.region().getText(), is( "34.23" ) );
      assertThat( systemUnderTest.region().isEditable(), is( false ) );
   }//End Method
   
   @Test public void shouldProvideRegistrationForSelection() {
      systemUnderTest.region().setText( "56.7" );
      verifyZeroInteractions( listener );
      
      new RegistrationManager().apply( systemUnderTest.registration() );
      systemUnderTest.region().setText( "0.65" );
      verify( listener ).accept( 56.7, 0.65 );
   }//End Method
   
}//End Class
