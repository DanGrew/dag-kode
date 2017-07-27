/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.registrations;

import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;

/**
 * {@link ReadOnlyChangeListenerRegistrationImpl} provides a {@link RegistrationImpl} that can handle
 * the registration of a {@link ChangeListener}.
 * @param <PropertyTypeT> the {@link ChangeListener} property type.
 */
public class ReadOnlyChangeListenerRegistrationImpl< PropertyTypeT > extends RegistrationImpl {
   
   private final ReadOnlyProperty< PropertyTypeT > property;
   private final ChangeListener< PropertyTypeT > action;
   private boolean registered = false;
   
   /**
    * Constructs a new {@link ReadOnlyChangeListenerRegistrationImpl}.
    * @param property the {@link Property} associated.
    * @param action the {@link ChangeListener} associated with the property.
    */
   public ReadOnlyChangeListenerRegistrationImpl( 
            ReadOnlyProperty< PropertyTypeT > property, 
            ChangeListener< PropertyTypeT > action 
   ) {
      this.property = property;
      this.action = action;
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override protected void register() {
      if ( registered ) {
         throw new IllegalStateException( "Registered multiple times which is not supported." );
      }
      
      registered = true;
      property.addListener( action );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override protected void unregister() {
      if ( !registered ) {
         throw new IllegalStateException( "Unregistering something that was never registered." );
      }
      
      property.removeListener( action );
   }//End Method

}//End Class
