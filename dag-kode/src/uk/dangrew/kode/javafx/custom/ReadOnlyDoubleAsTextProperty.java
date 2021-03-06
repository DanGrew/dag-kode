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
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.TextField;
import uk.dangrew.kode.javafx.registrations.ReadOnlyChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;
import uk.dangrew.kode.javafx.style.Conversions;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link Double} that cannot be edited.
 */
public class ReadOnlyDoubleAsTextProperty extends ReadOnlyNumberAsTextProperty<Double> implements ResponsiveRegionProperty {
   
   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    */
   public ReadOnlyDoubleAsTextProperty( ReadOnlyObjectProperty< Double > property ) {
      super(property, new Conversions().doubleToStringFunction());
   }//End Constructor
   
}//End Class
