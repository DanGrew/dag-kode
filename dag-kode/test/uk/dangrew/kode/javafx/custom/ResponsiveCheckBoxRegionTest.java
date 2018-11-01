package uk.dangrew.kode.javafx.custom;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.CheckBox;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.launch.TestApplication;

public class ResponsiveCheckBoxRegionTest {

   private CheckBox box;
   private ObjectProperty< Boolean > property;
   private ResponsiveCheckBoxRegion systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      box = new CheckBox();
      property = new SimpleObjectProperty<>( true );
      systemUnderTest = new ResponsiveCheckBoxRegion( box, property );
   }//End Method

   @Test public void shouldProvideRegion() {
      assertThat( systemUnderTest.region(), is( box ) );
   }//End Method
   
   @Test public void shouldProvideRegistration() {
      new RegistrationManager().apply( systemUnderTest.registration() );
      
      assertThat( box.isSelected(), is( true ) );
      property.set( false );
      assertThat( box.isSelected(), is( false ) );
      
      box.setSelected( true );
      assertThat( property.get(), is( true ) );
   }//End Method

}//End Class
