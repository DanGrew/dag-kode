/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.item;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import uk.dangrew.kode.javafx.style.JavaFxStyle;

/**
 * The {@link SimpleConfigurationTitle} provides a simple implementation of a title and 
 * description panel.
 */
public class SimpleSettingsContentTitle extends VBox {

   static final int SPACING = 10;
   
   private final Label titleLabel;
   private final Label descriptionLabel;
   
   /**
    * Constructs a new {@link SimpleConfigurationTitle}.
    * @param title the {@link String} title.
    * @param description the {@link String} description.
    */
   public SimpleSettingsContentTitle( String title, String description ) {
      this( title, description, new JavaFxStyle() );
   }//End Constructor
   
   /**
    * Constructs a new {@link SimpleConfigurationTitle}.
    * @param title the {@link String} title.
    * @param description the {@link String} description.
    * @param styling the {@link JavaFxStyle} to use.
    */
   SimpleSettingsContentTitle( String title, String description, JavaFxStyle styling ) {
      this.titleLabel = styling.createBoldLabel( title );
      this.titleLabel.setAlignment( Pos.CENTER_LEFT );
      this.getChildren().add( titleLabel );
      
      if ( description != null ) {
         this.descriptionLabel = styling.createWrappedTextLabel( description );
         this.getChildren().add( descriptionLabel );
      } else {
         this.descriptionLabel = null;
      }
      
      this.getChildren().add( new Separator() );
      this.setSpacing( SPACING );
   }//End Constructor

   Label getTitle() {
      return titleLabel;
   }//End Method
   
   Label getDescription() {
      return descriptionLabel;
   }//End Method
   
}//End Class
