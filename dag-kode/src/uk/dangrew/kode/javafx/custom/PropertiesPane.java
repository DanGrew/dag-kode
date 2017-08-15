/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.custom;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.javafx.style.PropertyRowBuilder;

/**
 * {@link PropertiesPane} provides a custom but standard way of laying out some {@link javafx.beans.property.ObjectProperty}s
 * and binding them to {@link TextField}s.
 */
public class PropertiesPane extends TitledPane {
   
   private final RegistrationManager registrations;
   private final GridPane content;
   
   /**
    * Constructs a new {@link PropertiesPane}.
    * @param paneName the name of the {@link TitledPane}.
    * @param properties the {@link PropertyRowBuilder}s for the rows.
    */
   public PropertiesPane( 
            String paneName, 
            PropertyRowBuilder... properties 
   ){
      this( new JavaFxStyle(), paneName, properties );
   }//End Constructor
   
   /**
    * Constructs a new {@link PropertiesPane}.
    * @param styling the {@link JavaFxStyle}.
    * @param paneName the name of the {@link TitledPane}.
    * @param properties the {@link PropertyRowBuilder}s for the rows.
    */
   PropertiesPane(
            JavaFxStyle styling,
            String paneName, 
            PropertyRowBuilder... properties
   ) {
      this.registrations = new RegistrationManager();
      this.content = new GridPane();
      
      styling.configureConstraintsForEvenRows( content, properties.length );
      styling.configureConstraintsForEvenColumns( content, 2 );
      
      for ( int i = 0; i < properties.length; i++ ) {
         content.add( new Label( properties[ i ].labelName() ), 0, i );

         Region region = properties[ i ].binding().region();
         region.setMaxWidth( Double.MAX_VALUE );
         content.add( region, 1, i );
         registrations.apply( properties[ i ].binding().registration() );
      }
      
      setText( paneName );
      setContent( content );
      setCollapsible( false );
   }//End Constructor
   
   GridPane content(){
      return content;
   }//End Method
   
   Label labelForRow( int i ){
      return ( Label ) content.getChildren().get( i * 2 );
   }//End Method
   
   TextField textFieldForRow( int i ){
      return ( TextField ) content.getChildren().get( ( i * 2 ) + 1 );
   }//End Method

}//End Class
