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
import uk.dangrew.kode.javafx.registrations.ChangeListenerMismatchBindingImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;
import uk.dangrew.kode.javafx.style.Conversions;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link Double}.
 */
public class BoundTextProperty implements ResponsiveRegionProperty {
   
   private final Conversions conversions;
   
   private final ObjectProperty< Double > property;
   private final TextField field;
   private final RegistrationImpl registration;
   
   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    * @param editable whether the {@link TextField} is editable.
    */
   public BoundTextProperty( ObjectProperty< Double > property, boolean editable ) {
      this.conversions = new Conversions();
      this.property = property;
      this.field = new TextField();
      this.field.setText( conversions.doubleToStringFunction().apply( property.get() ) );
      this.field.setEditable( editable );
      
      this.registration = new ChangeListenerMismatchBindingImpl<>( 
               property, field.textProperty(), 
               conversions.stringToDoubleFunction(), conversions.doubleToStringFunction()
      );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public TextField region(){
      return field;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public RegistrationImpl registration(){
      return registration;
   }//End Method

   ObjectProperty< Double > property() {
      return property;
   }//End Method

   boolean isEditable() {
      return field.isEditable();
   }//End Method

}//End Class
