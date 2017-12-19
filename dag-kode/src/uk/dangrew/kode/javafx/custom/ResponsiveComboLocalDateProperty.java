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

/**
 * {@link ResponsiveComboEpochDayProperty} provides a {@link ComboBox} of {@link LocalDate}s.
 */
public class ResponsiveComboLocalDateProperty extends ResponsiveComboProperty< LocalDate > {

   public ResponsiveComboLocalDateProperty( 
            ObservableList< LocalDate > items, 
            LocalDate selected, 
            ChangeListener< LocalDate > listener 
   ) {
      super( items, selected, listener );
   }//End Constructor

}//End Class
