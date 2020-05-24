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
import uk.dangrew.kode.javafx.style.Conversions;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link Double} that cannot be edited.
 */
public class ReadOnlyIntegerAsTextProperty extends ReadOnlyNumberAsTextProperty<Integer> implements ResponsiveRegionProperty {

   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    */
   public ReadOnlyIntegerAsTextProperty(ReadOnlyObjectProperty< Integer > property ) {
      super(property, new Conversions().integerToStringFunction());
   }//End Constructor
   
}//End Class
