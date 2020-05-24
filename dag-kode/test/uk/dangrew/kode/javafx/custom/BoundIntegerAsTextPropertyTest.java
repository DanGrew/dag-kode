package uk.dangrew.kode.javafx.custom;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.launch.TestApplication;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BoundIntegerAsTextPropertyTest {

   private ObjectProperty< Integer > property;
   private BoundIntegerAsTextProperty systemUnderTest;
   
   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      property = new SimpleObjectProperty< Integer >( 34 );
      systemUnderTest = new BoundIntegerAsTextProperty( property, false );
   }//End Method

   @Test public void shouldProvideTextField() {
      assertThat( systemUnderTest.region().getText(), is( "34" ) );
      assertThat( systemUnderTest.region().isEditable(), is( false ) );
   }//End Method
   
   @Test public void shouldProvideRegistrationForSelection() {
      systemUnderTest.region().setText( "56" );
      assertThat( property.get(), is( 34 ) );
      
      new RegistrationManager().apply( systemUnderTest.registration() );
      systemUnderTest.region().setText( "65" );
      assertThat( property.get(), is( 65 ) );
   }//End Method
   
   @Test public void shouldProvideProperty(){
      assertThat( systemUnderTest.property(), is( property ) );
   }//End Method

}//End Class
