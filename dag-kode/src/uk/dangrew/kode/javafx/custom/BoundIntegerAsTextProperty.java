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
import uk.dangrew.kode.javafx.style.Conversions;

import java.awt.*;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link Double}.
 */
public class BoundIntegerAsTextProperty extends ReadOnlyIntegerAsTextProperty implements ResponsiveRegionProperty {

   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    * @param editable whether the {@link TextField} is editable.
    */
   public BoundIntegerAsTextProperty(ObjectProperty< Integer > property, boolean editable ) {
      super( property );
      this.region().setEditable( editable );
   }//End Constructor
   
   @Override protected RegistrationImpl getRegistration() {
      return new ChangeListenerMismatchBindingImpl<>( 
               property(), region().textProperty(), 
               conversions().stringToIntegerFunction(), conversions().integerToStringFunction()
      );
   }//End Constructor
   
   ObjectProperty< Integer > property() {
      return ( ObjectProperty< Integer > )super.property();
   }//End Method

}//End Class
