/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.custom;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextField;
import uk.dangrew.kode.javafx.registrations.ChangeListenerBindingImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link String}.
 */
public class BoundTextProperty implements ResponsiveRegionProperty {
   
   private final ObjectProperty< String > property;
   private final TextField field;
   private final RegistrationImpl registration;
   
   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    * @param editable whether the {@link TextField} is editable.
    */
   public BoundTextProperty( ObjectProperty< String > property, boolean editable ) {
      this.property = property;
      this.field = new TextField();
      this.field.setText( property.get() );
      this.field.setEditable( editable );
      
      this.registration = new ChangeListenerBindingImpl<>( 
               property, field.textProperty() 
      );
   }//End Constructor
   
   @Override public TextField region(){
      return field;
   }//End Method
   
   @Override public RegistrationImpl registration(){
      return registration;
   }//End Method

   ObjectProperty< String > property() {
      return property;
   }//End Method

   boolean isEditable() {
      return field.isEditable();
   }//End Method

}//End Class
