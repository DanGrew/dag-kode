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

import java.util.function.Function;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link Double} that cannot be edited.
 */
public class ReadOnlyNumberAsTextProperty< NumberTypeT extends Number> implements ResponsiveRegionProperty {

   private final Conversions conversions;
   private final Function<NumberTypeT, String> numberTypeTStringFunction;

   private final ReadOnlyObjectProperty< NumberTypeT > property;
   private final TextField field;
   private final RegistrationImpl registration;

   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    * @param numberToStringFunction the {@link Function} for converting from number to string.
    */
   public ReadOnlyNumberAsTextProperty(ReadOnlyObjectProperty< NumberTypeT > property, Function<NumberTypeT, String> numberToStringFunction ) {
      this.numberTypeTStringFunction = numberToStringFunction;
      this.property = property;
      this.field = new TextField();
      this.field.setText( numberToStringFunction.apply( property.get() ) );
      this.field.setEditable( false );
      this.conversions = new Conversions();
      this.registration = getRegistration();
   }//End Constructor
   
   protected RegistrationImpl getRegistration(){
      return new ReadOnlyChangeListenerRegistrationImpl<>( 
               property, 
               ( s, o, n ) -> field.setText( numberTypeTStringFunction.apply( n ) )
      );
   }//End Method

   protected Conversions conversions(){
      return conversions;
   }

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

   ReadOnlyObjectProperty< NumberTypeT > property() {
      return property;
   }//End Method

   boolean isEditable() {
      return field.isEditable();
   }//End Method

}//End Class
