/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.style;

import javafx.beans.property.ObjectProperty;

/**
 * {@link PropertyRowBuilder} provides a nice interface for creating a standard {@link ObjectProperty}
 * layout using {@link JavaFxStyle#createPropertiesPane()}.
 */
public class PropertyRowBuilder {
      
   private String labelName;
   private ObjectProperty< Double > property;
   private boolean editable;
   
   /**
    * Constructs a new {@link PropertyRowBuilder}.
    */
   public PropertyRowBuilder() {
      this.labelName = "";
      this.editable = true;
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
   public ObjectProperty< Double > property() {
      return property;
   }//End Method

   /**
    * Access to whether the {@link javafx.scene.control.TextField} is editable.
    * @return the value.
    */
   public boolean isEditable() {
      return editable;
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
    * Configure the {@link ObjectProperty} to bind.
    * @param name the value.
    * @return this {@link PropertyRowBuilder}.
    */
   public PropertyRowBuilder withProperty( ObjectProperty< Double > property ) {
      this.property = property;
      return this;
   }//End Method

   /**
    * Configure the editability of the {@link javafx.scene.control.TextField}.
    * @param name the value.
    * @return this {@link PropertyRowBuilder}.
    */
   public PropertyRowBuilder allowEditing( boolean editable ) {
      this.editable = editable;
      return this;
   }//End Method

}//End Class
