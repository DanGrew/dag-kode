/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.style;

import uk.dangrew.kode.javafx.custom.ResponsiveRegionProperty;

/**
 * {@link PropertyRowBuilder} provides a nice interface for creating a standard {@link ObjectProperty}
 * layout using {@link JavaFxStyle#createPropertiesPane()}.
 */
public class PropertyRowBuilder {
      
   private String labelName;
   private ResponsiveRegionProperty binding;
   
   /**
    * Constructs a new {@link PropertyRowBuilder}.
    */
   public PropertyRowBuilder() {
      this.labelName = "";
   }//End Constructor
   
   /**
    * Access to the {@link javafx.scene.control.Label} name.
    * @return the name.
    */
   public String labelName() {
      return labelName;
   }//End Method

   /**
    * Access to the {@link ObjectProperty}.
    * @return the property.
    */
   public ResponsiveRegionProperty binding() {
      return binding;
   }//End Method

   /**
    * Configure the {@link javafx.scene.control.Label} name.
    * @param name the value.
    * @return this {@link PropertyRowBuilder}.
    */
   public PropertyRowBuilder withLabelName( String name ) {
      this.labelName = name;
      return this;
   }//End Method

   /**
    * Configure the {@link ResponsiveRegionProperty} to provide in the pane.
    * @param binding the {@link ResponsiveRegionProperty}.
    * @return this {@link PropertyRowBuilder}.
    */
   public PropertyRowBuilder withBinding( ResponsiveRegionProperty binding ) {
      this.binding = binding;
      return this;
   }//End Method

}//End Class
