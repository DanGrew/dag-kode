/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.window;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.settings.window.SettingsWindowController;

public class SettingsWindowControllerTest {
   
   private Parent pane;
   private SettingsWindowController systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      pane = new BorderPane();
      systemUnderTest = new SettingsWindowController();
      systemUnderTest.associateWithSettingsTreePane( pane );
   }//End Method
   
   @Test( expected = IllegalStateException.class ) public void shouldNotAllowShowIfNotAssociated(){
      systemUnderTest = new SettingsWindowController();
      systemUnderTest.showSettingsWindow();
   }//End Method
   
   @Test( expected = IllegalStateException.class ) public void shouldNotAllowHideIfNotAssociated(){
      systemUnderTest = new SettingsWindowController();
      systemUnderTest.hideSettingsWindow();
   }//End Method
   
   @Test( expected = IllegalStateException.class ) public void shouldNotAllowIsShowingCheckIfNotAssociated(){
      systemUnderTest = new SettingsWindowController();
      systemUnderTest.isSettingsWindowShowing();
   }//End Method
   
   @Test public void shouldHaveInitialisedStage() {
      assertThat( systemUnderTest.stage(), is( notNullValue() ) );
   }//End Method
   
   @Test public void stageShouldHaveConfigurationWindowWithinScene(){
      assertThat( systemUnderTest.stage().getScene(), is( notNullValue() ) );
      assertThat( systemUnderTest.stage().getScene().getRoot(), is( notNullValue() ) );
      assertThat( systemUnderTest.stage().getScene().getRoot(), is( pane ) );
   }//End Method
   
   @Test public void stageShouldBeHiddenInitially(){
      assertThat( systemUnderTest.stage().isShowing(), is( false ) );
   }//End Method
   
   @Test public void stageShouldShowWhenTold(){
      assertThat( systemUnderTest.stage().isShowing(), is( false ) );
      systemUnderTest.showSettingsWindow();
      JavaFxThreading.runAndWait();
      assertThat( systemUnderTest.stage().isShowing(), is( true ) );
      assertThat( systemUnderTest.isSettingsWindowShowing(), is( true ) );
   }//End Method
   
   @Test public void stageShouldHideWhenTold(){
      stageShouldShowWhenTold();
      assertThat( systemUnderTest.stage().isShowing(), is( true ) );
      systemUnderTest.hideSettingsWindow();
      JavaFxThreading.runAndWait();
      assertThat( systemUnderTest.stage().isShowing(), is( false ) );
      assertThat( systemUnderTest.isSettingsWindowShowing(), is( false ) );
   }//End Method

   @Test public void shouldSizeStageAndMakeNotFullScreen(){
      assertThat( systemUnderTest.stage().isFullScreen(), is( false ) );
      assertThat( systemUnderTest.stage().widthProperty().get(), is( SettingsWindowController.WIDTH ) );
      assertThat( systemUnderTest.stage().heightProperty().get(), is( SettingsWindowController.HEIGHT ) );
   }//End Method
   
   @Test public void shouldAlwaysBeOnTop(){
      assertThat( systemUnderTest.stage().isAlwaysOnTop(), is( true ) );
   }//End Method
   
   @Test public void shouldHaveTitle(){
      assertThat( systemUnderTest.stage().getTitle(), is( SettingsWindowController.WINDOW_TITLE ) );
   }//End Method
}//End Class
