/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.custom;

import java.util.function.BiConsumer;

import javafx.scene.control.TextField;
import uk.dangrew.kode.javafx.registrations.ReadOnlyChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;
import uk.dangrew.kode.javafx.style.Conversions;

/**
 * {@link ResponsiveDoubleAsTextProperty} provides a {@link TextField} that is converted to a {@link Double}.
 */
public class ResponsiveDoubleAsTextProperty implements ResponsiveRegionProperty {
   
   private final Conversions conversions;
   
   private final TextField field;
   private final RegistrationImpl registration;
   
   /**
    * Constructs a new {@link ResponsiveDoubleAsTextProperty}.
    * @param initial the initial value in the {@link TextField}.
    * @param listener the {@link BiConsumer} to notify changes.
    * @param editable whether the {@link TextField} is editable.
    */
   public ResponsiveDoubleAsTextProperty( Double initial, BiConsumer< Double, Double > listener, boolean editable ) {
      this.conversions = new Conversions();
      this.field = new TextField();
      this.field.setEditable( editable );
      this.field.setText( conversions.doubleToStringFunction().apply( initial ) );
      
      this.registration = new ReadOnlyChangeListenerRegistrationImpl<>( 
               field.textProperty(), 
               ( s, o, n ) -> listener.accept( conversions.stringToDoubleFunction().apply( o ), conversions.stringToDoubleFunction().apply( n ) )
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

}//End Class
