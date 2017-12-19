/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.custom;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import uk.dangrew.kode.javafx.registrations.ReadOnlyChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

/**
 * {@link ResponsiveComboProperty} provides a {@link ComboBox} of objects.
 */
public class ResponsiveComboProperty< TypeT > implements ResponsiveRegionProperty {
   
   private final ComboBox< TypeT > field;
   private final RegistrationImpl registration;
   
   /**
    * Constructs a new {@link ResponsiveComboProperty}.
    * @param items the {@link ObservableList} of objects as options.
    * @param selected the currently selected object.
    * @param listener the {@link ChangeListener} to notify when changed.
    */
   public ResponsiveComboProperty( ObservableList< TypeT > items, TypeT selected, ChangeListener< TypeT > listener ) {
      this.field = new ComboBox<>();
      this.field.setItems( items );
      this.field.getSelectionModel().select( selected );
      
      this.registration = new ReadOnlyChangeListenerRegistrationImpl< TypeT >(  
               field.getSelectionModel().selectedItemProperty(), 
               listener::changed
      );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public ComboBox< TypeT > region(){
      return field;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public RegistrationImpl registration(){
      return registration;
   }//End Method

}//End Class
