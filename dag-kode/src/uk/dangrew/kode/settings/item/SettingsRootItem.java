/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.item;

import javafx.scene.control.TreeItem;

/**
 * The {@link SettingsRootItem} provides a basic implementation
 * that can be used for the root of a {@link javafx.scene.control.TreeView}.
 */
public class SettingsRootItem implements SettingsItem {

   private final TreeItem< SettingsItem > item;
   
   public SettingsRootItem() {
      this.item = new TreeItem<>( this );
   }//End Constructor
   
   @Override public TreeItem< SettingsItem > treeItem() {
      return item;
   }//End Method
   
   @Override public void select() {
      //do nothing
   }//End Method
   
   @Override public SettingsItemType type() {
      return null;
   }//End Method
   
}//End Class
