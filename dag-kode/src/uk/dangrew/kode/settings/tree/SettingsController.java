/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.tree;

import javafx.scene.Node;
import uk.dangrew.kode.event.structure.Event;
import uk.dangrew.kode.settings.content.SettingsTreeContent;
import uk.dangrew.kode.settings.window.SettingsBehaviour;
import uk.dangrew.kode.settings.window.SettingsOpenEvent;
import uk.dangrew.kode.settings.window.SettingsWindowController;

public class SettingsController {

   private final SettingsWindowController controller;
   private final SettingsTreeContent contentHolder;
   private final SettingsTree tree;
   
   public SettingsController( SettingsTree tree, SettingsTreeContent content, SettingsWindowController controller ) {
      this.controller = controller;
      this.contentHolder = content;
      this.tree = tree;
      
      new SettingsOpenEvent().register( this::eventFired );
   }//End Constructor

   private void eventFired( Event< SettingsBehaviour > event ) {
      if ( event.getValue() == null ) {
         return;
      }
      switch ( event.getValue().getWindowPolicy() ) {
         case Close:
            controller.hideSettingsWindow();
            break;
         case Open:
            controller.showSettingsWindow();
            break;
         default:
            break;
      }
      
      if ( event.getValue().getSelection() == null ) {
         return;
      }
      tree.select( event.getValue().getSelection() );
   }//End Method
   
   /**
    * Method to display the content in the {@link SettingsTreeContent}.
    * @param title the {@link Node} providing the title.
    * @param content the {@link Node} providing the settings content.
    */
   public void displayContent( Node title, Node content ){
      contentHolder.setContent( title, content );
   }//End Method
   
}//End Class
