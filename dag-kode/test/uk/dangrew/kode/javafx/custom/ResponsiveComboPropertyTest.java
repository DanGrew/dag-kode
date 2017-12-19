package uk.dangrew.kode.javafx.custom;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.launch.TestApplication;

public class ResponsiveComboPropertyTest {

   @Mock private ChangeListener< LocalDate > listener;
   private ObservableList< LocalDate > items;
   private ResponsiveComboProperty< LocalDate > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      items = FXCollections.observableArrayList();
      for ( int i = 0; i < 10; i++ ) {
         items.add( LocalDate.now().plusDays( i ) );
      }
      systemUnderTest = new ResponsiveComboProperty<>( items, items.get( 2 ), listener );
   }//End Method

   @Test public void shouldProvideComboBoxWithItems() {
      assertThat( systemUnderTest.region().getItems(), is( items ) );
   }//End Method
   
   @Test public void shouldProvideRegistrationForSelection() {
      systemUnderTest.region().getSelectionModel().select( 6 );
      verifyZeroInteractions( listener );
      
      new RegistrationManager().apply( systemUnderTest.registration() );
      systemUnderTest.region().getSelectionModel().select( 7 );
      verify( listener ).changed( systemUnderTest.region().getSelectionModel().selectedItemProperty(), items.get( 6 ), items.get( 7 ) );
   }//End Method
   
   @Test public void shouldImmediatelySelect() {
      assertThat( systemUnderTest.region().getSelectionModel().getSelectedItem(), is( items.get( 2 ) ) );
   }//End Method

}//End Class
