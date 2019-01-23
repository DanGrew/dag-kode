/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.custom;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.control.TextField;
import uk.dangrew.kode.javafx.registrations.ReadOnlyChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;
import uk.dangrew.kode.javafx.style.Conversions;

/**
 * Provides a {@link TextField} bound to a {@link ObjectProperty} of {@link Double} that cannot be edited.
 */
public class ReadOnlyDoubleAsTextProperty implements ResponsiveRegionProperty {
   
   private final Conversions conversions;
   
   private final ReadOnlyObjectProperty< Double > property;
   private final TextField field;
   private /*final*/ RegistrationImpl registration;
   
   /**
    * Constructs a new {@link BoundTextProperty}.
    * @param property the {@link ObjectProperty} to bind to.
    * @param editable whether the {@link TextField} is editable.
    */
   public ReadOnlyDoubleAsTextProperty( ReadOnlyObjectProperty< Double > property ) {
      this.conversions = new Conversions();
      this.property = property;
      this.field = new TextField();
      this.field.setText( conversions.doubleToStringFunction().apply( property.get() ) );
      this.field.setEditable( false );
      this.registration = getRegistration();
   }//End Constructor
   
   protected RegistrationImpl getRegistration(){
      return new ReadOnlyChangeListenerRegistrationImpl<>( 
               property, 
               ( s, o, n ) -> field.setText( conversions.doubleToStringFunction().apply( n ) )
      );
   }//End Method
   
   protected Conversions conversions(){
      return conversions;
   }//End Method
   
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

   ReadOnlyObjectProperty< Double > property() {
      return property;
   }//End Method

   boolean isEditable() {
      return field.isEditable();
   }//End Method

}//End Class
