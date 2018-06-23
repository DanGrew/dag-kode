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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import uk.dangrew.kode.javafx.css.DynamicCssOnlyProperties;
import uk.dangrew.kode.settings.tree.SettingsController;

/**
 * {@link ScrollableSettingsItem} provides a base class that wraps the given content
 * in a {@link ScrollPane}. This is important to make sure content is completely usable
 * but a choice as because a wrapping {@link ScrollPane} may effect nested {@link ScrollPane}s
 * if used.
 */
public class ScrollableSettingsItem extends SimpleSettingsItem {
   
   protected ScrollableSettingsItem( 
            SettingsItemType type,
            String itemName, 
            Node contentTitle, 
            SettingsController controller, 
            Node content 
   ) {
      this( new DynamicCssOnlyProperties(), type, controller, itemName, contentTitle, content );
   }//End Constructor
   
   protected ScrollableSettingsItem( 
            DynamicCssOnlyProperties css,
            SettingsItemType type,
            SettingsController controller, 
            String itemName, 
            Node contentTitle, 
            Node content 
   ) {
      super( controller, type, itemName, contentTitle, wrapInScroller( css, content ) );
   }//End Constructor

   private static Node wrapInScroller( DynamicCssOnlyProperties css, Node content ){
      ScrollPane scroller = new ScrollPane( content );
      scroller.setHbarPolicy( ScrollBarPolicy.NEVER );
      scroller.setFitToWidth( true );
      css.removeScrollPaneBorder( scroller );
      return scroller;
   }//End Method

}//End Class
