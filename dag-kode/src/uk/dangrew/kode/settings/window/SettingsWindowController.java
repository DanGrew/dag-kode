/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.window;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The {@link SettingsWindowController} is responsible for controlling a
 * {@link uk.dangrew.kode.settings.tree.SettingsTreePane} in a separate {@link Stage}. Note that this requires
 * the {@link PlatformImpl} {@link Thread} therefore some of these calls are run later.
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
         PlatformImpl.runAndWait( () -> {
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
      PlatformImpl.runLater( () -> { 
         windowStage.show();
         windowStage.toFront();
      } );
   }//End Method
   
   public void hideSettingsWindow(){
      verifyState();
      PlatformImpl.runLater( () -> windowStage.hide() );
   }//End Method
   
   public boolean isSettingsWindowShowing(){
      verifyState();
      return windowStage.isShowing();
   }//End Method
   
   Stage stage(){
      return windowStage;
   }//End Method

}//End Class
