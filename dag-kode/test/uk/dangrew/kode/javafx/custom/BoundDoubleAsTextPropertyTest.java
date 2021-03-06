package uk.dangrew.kode.javafx.custom;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.launch.TestApplication;

public class BoundDoubleAsTextPropertyTest {

   private ObjectProperty< Double > property;
   private BoundDoubleAsTextProperty systemUnderTest;
   
   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      property = new SimpleObjectProperty< Double >( 34.23 );
      systemUnderTest = new BoundDoubleAsTextProperty( property, false );
   }//End Method

   @Test public void shouldProvideTextField() {
      assertThat( systemUnderTest.region().getText(), is( "34.23" ) );
      assertThat( systemUnderTest.region().isEditable(), is( false ) );
   }//End Method
   
   @Test public void shouldProvideRegistrationForSelection() {
      systemUnderTest.region().setText( "56.7" );
      assertThat( property.get(), is( 34.23 ) );
      
      new RegistrationManager().apply( systemUnderTest.registration() );
      systemUnderTest.region().setText( "0.65" );
      assertThat( property.get(), is( 0.65 ) );
   }//End Method
   
   @Test public void shouldProvideProperty(){
      assertThat( systemUnderTest.property(), is( property ) );
   }//End Method

}//End Class
