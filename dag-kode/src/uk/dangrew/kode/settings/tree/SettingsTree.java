/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.tree;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import uk.dangrew.kode.settings.item.SettingsItem;
import uk.dangrew.kode.settings.item.SettingsItemType;
import uk.dangrew.kode.settings.item.SettingsRootItem;

public class SettingsTree extends TreeView< SettingsItem > {
   
   public SettingsTree() {
      this.getSelectionModel().setSelectionMode( SelectionMode.SINGLE );
      this.getSelectionModel().selectedItemProperty().addListener( ( source, old, updated ) -> updated.getValue().select() );
   }//End Constructor
   
   public void build( SettingsController controller, SettingsTreeItems treeItems ) {
      SettingsRootItem root = new SettingsRootItem();
      this.setRoot( root.treeItem() );
      this.setShowRoot( false );
      
      treeItems.build( controller, root );
   }//End Method
   
   public boolean isSelected( SettingsItem item ) {
      TreeItem< SettingsItem > treeItem = item.treeItem();
      TreeItem< SettingsItem > selected = getSelectionModel().getSelectedItem();
      return treeItem == selected;
   }//End Method
   
   public void select( SettingsItemType item ) {
      item.find( this ).ifPresent( treeItem -> PlatformImpl.runAndWait( () -> getSelectionModel().select( treeItem ) ) );
   }//End Method
   
}//End Class
