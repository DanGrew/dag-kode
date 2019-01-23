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
import uk.dangrew.kode.javafx.registrations.ChangeListenerMismatchBindingImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link Double}.
 */
public class BoundDoubleAsTextProperty extends ReadOnlyDoubleAsTextProperty implements ResponsiveRegionProperty {
   
   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    * @param editable whether the {@link TextField} is editable.
    */
   public BoundDoubleAsTextProperty( ObjectProperty< Double > property, boolean editable ) {
      super( property );
      this.region().setEditable( editable );
   }//End Constructor
   
   @Override protected RegistrationImpl getRegistration() {
      return new ChangeListenerMismatchBindingImpl<>( 
               property(), region().textProperty(), 
               conversions().stringToDoubleFunction(), conversions().doubleToStringFunction()
      );
   }//End Constructor
   
   ObjectProperty< Double > property() {
      return ( ObjectProperty< Double > )super.property();
   }//End Method

}//End Class
