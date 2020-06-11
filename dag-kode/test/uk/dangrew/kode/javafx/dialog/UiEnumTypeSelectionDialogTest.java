package uk.dangrew.kode.javafx.dialog;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.sun.javafx.application.PlatformImpl;

import javafx.geometry.Pos;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.launch.TestApplication;

public class UiEnumTypeSelectionDialogTest {

   private UiEnumTypeSelectionDialog< Pos > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      JavaFxThreading.runAndWait( () -> systemUnderTest = new UiEnumTypeSelectionDialog<>( Pos.class, Pos.BASELINE_LEFT ) );
   }//End Method

   @Test public void shouldContainOptions() {
      assertThat( systemUnderTest.getItems(), containsInAnyOrder( Pos.values() ) );
   }//End Method
   
   @Test public void shouldProvideDefaultChoice(){
      assertThat( systemUnderTest.getSelectedItem(), is( Pos.BASELINE_LEFT ) );
   }//End Method

}//End Class
