package uk.dangrew.kode.javafx.custom;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.javafx.style.Conversions;
import uk.dangrew.kode.launch.TestApplication;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class ReadOnlyNumberAsTextPropertyTest {

   private ObjectProperty< Double > property;
   private ReadOnlyNumberAsTextProperty systemUnderTest;
   
   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      property = new SimpleObjectProperty<>( 34.23 );
      systemUnderTest = new ReadOnlyNumberAsTextProperty( property, new Conversions().doubleToStringFunction());
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
      assertThat( property.get(), is( not( 0.65 ) ) );
      
      property.set( 0.65 );
      assertThat( systemUnderTest.region().getText(), is( "0.65" ) );
   }//End Method
   
   @Test public void shouldProvideProperty(){
      assertThat( systemUnderTest.property(), is( property ) );
   }//End Method

}//End Class
