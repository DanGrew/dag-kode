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
 * The {@link SettingsItem} describes an item in the {@link uk.dangrew.kode.settings.tree.SettingsTree}
 * describing an item or collection of items that can be configured. 
 */
public interface SettingsItem {
   
   public SettingsItemType type();

   public TreeItem< SettingsItem > treeItem();

   public default void appendChild( SettingsItem item ) {
      treeItem().getChildren().add( item.treeItem() );
   }//End Method
   
   public void select();
}//End Interface
