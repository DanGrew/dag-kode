/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.window;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;

/**
 * The {@link SettingsWindowController} is responsible for controlling a
 * {@link uk.dangrew.kode.settings.tree.SettingsTreePane} in a separate {@link Stage}. Note that this requires
 * the JavaFx {@link Thread} therefore some of these calls are run later.
 */
public class SettingsWindowController {
   
   static final String WINDOW_TITLE = "System Settings";
   
   static final double HEIGHT = 600;
   static final double WIDTH = 900;
   
   private Stage windowStage;
   
   public void associateWithSettingsTreePane(
            Parent configurationWindow
   ) {
         Scene settingsScene = new Scene( configurationWindow );
         JavaFxThreading.runAndWait( () -> {
            windowStage = new Stage();
            windowStage.setTitle( WINDOW_TITLE );
            windowStage.setWidth( WIDTH );
            windowStage.setHeight( HEIGHT );
            windowStage.setFullScreen( false );
            windowStage.setScene( settingsScene );
            windowStage.setAlwaysOnTop( true );
         } );
   }//End Constructor
   
   private void verifyState(){
      if ( windowStage == null ) {
         throw new IllegalStateException( "Controller has not be associated." );
      }
   }//End Method
   
   public void showSettingsWindow(){
      verifyState();
      JavaFxThreading.runLater( () -> {
         windowStage.show();
         windowStage.toFront();
      } );
   }//End Method
   
   public void hideSettingsWindow(){
      verifyState();
      JavaFxThreading.runLater( () -> windowStage.hide() );
   }//End Method
   
   public boolean isSettingsWindowShowing(){
      verifyState();
      return windowStage.isShowing();
   }//End Method
   
   Stage stage(){
      return windowStage;
   }//End Method

}//End Class
