/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.tree;

import javafx.scene.layout.BorderPane;
import uk.dangrew.kode.settings.content.SettingsTreeContent;
import uk.dangrew.kode.settings.window.SettingsWindowController;

public class SettingsTreePane extends BorderPane {
   
   private final SettingsTree tree;
   private final SettingsTreeContent content;
   private final SettingsController controller;
   
   public SettingsTreePane(
            SettingsTreeItems treeItems,
            SettingsWindowController windowController
   ) {
      this.tree = new SettingsTree();
      this.content = new SettingsTreeContent();
      this.controller = new SettingsController( tree, content, windowController );
      this.tree.build( controller, treeItems );
      
      setLeft( tree );
      setCenter( content );
   }//End Constructor
   
}//End Class
