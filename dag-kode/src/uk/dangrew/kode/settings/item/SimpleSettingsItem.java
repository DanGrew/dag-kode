/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.item;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.settings.tree.SettingsController;

/**
 * The {@link SimpleSettingsItem} provides a base class for {@link SettingsItem} that
 * simply call through to a {@link SettingsController}.
 */
public class SimpleSettingsItem implements SettingsItem {

   private final TreeItem< SettingsItem > treeItem;
   private final SettingsItemType type;
   private final String itemName;
   private final Node contentTitle;
   private final SettingsController controller;
   private final Node content;
   
   protected SimpleSettingsItem( 
            SettingsController controller, 
            SettingsItemType type,
            String itemName, 
            Node contentTitle,
            Node content 
   ) {
      this.itemName = itemName;
      this.type = type;
      this.contentTitle = contentTitle;
      this.controller = controller;
      this.content = content;
      this.treeItem = new TreeItem<>( this );
   }//End Constructor
   
   public SimpleSettingsItem( 
            SettingsController controller,
            SettingsItemType type,
            String itemName
   ) {
      this( 
               controller, 
               type,
               itemName, 
               new TextFlowBuilder().bold( itemName ).build(),
               new TextFlowBuilder().normal( itemName + " content" ).build()
      );
   }//End Constructor
   
   @Override public SettingsItemType type() {
      return type;
   }//End Method
   
   @Override public TreeItem< SettingsItem > treeItem() {
      return treeItem;
   }//End Method
   
   @Override public void select() {
      controller.displayContent( contentTitle, content );
   }//End Method
   
   @Override public String toString() {
      return itemName;
   }//End Method
}//End Class
