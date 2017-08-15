/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.custom;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import uk.dangrew.kode.javafx.registrations.ReadOnlyChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

/**
 * {@link ResponsiveComboEpochDayProperty} provides a {@link ComboBox} of {@link LocalDate}s.
 */
public class ResponsiveComboLocalDateProperty implements ResponsiveRegionProperty {
   
   private final ComboBox< LocalDate > field;
   private final RegistrationImpl registration;
   
   /**
    * Constructs a new {@link ResponsiveComboLocalDateProperty}.
    * @param items the {@link ObservableList} of {@link LocalDate} as options.
    * @param selected the currently selected {@link LocalDate}.
    * @param listener the {@link ChangeListener} to notify when changed.
    */
   public ResponsiveComboLocalDateProperty( ObservableList< LocalDate > items, LocalDate selected, ChangeListener< LocalDate > listener ) {
      this.field = new ComboBox<>();
      this.field.setItems( items );
      this.field.getSelectionModel().select( selected );
      
      this.registration = new ReadOnlyChangeListenerRegistrationImpl< LocalDate >(  
               field.getSelectionModel().selectedItemProperty(), 
               listener::changed
      );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public ComboBox< LocalDate > region(){
      return field;
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public RegistrationImpl registration(){
      return registration;
   }//End Method

}//End Class
