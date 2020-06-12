package uk.dangrew.kode.javafx.table.tools;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.base.ConceptTableRow;
import uk.dangrew.kode.javafx.table.base.ConceptTableRowImpl;
import uk.dangrew.kode.observable.FunctionListChangeListenerImpl;

/**
 * Tools to synchronize the {@link Concept}s held in an {@link ObservableList} with the associated {@link ConceptTable}.
 * @param <TypeT> the type of {@link Concept}.
 */
public class RowSynchronizer< TypeT extends Concept > {

   private final ObservableList< TypeT > items;
   private final ConceptTable< TypeT > table;
   private final FunctionListChangeListenerImpl< TypeT > synchronizer;
   
   public RowSynchronizer( ConceptTable< TypeT > table, ObservableList< TypeT > items ) {
      this.table = table;
      this.items = items;
      
      this.table.getRows().clear();
      this.items.addListener( synchronizer = new FunctionListChangeListenerImpl<>( 
               this::addRow, this::removeRow 
      ) );
      this.items.forEach( this::addRow );
   }//End Constructor
   
   private void addRow( TypeT concept ) {
      table.getItems().add( new ConceptTableRowImpl<>( concept ) );
   }//End Method
   
   private void removeRow( TypeT concept ) {
      Set< ConceptTableRow< TypeT > > rowsToRemove = new HashSet<>();
      for ( ConceptTableRow< TypeT > row : table.getItems() ) {
         if ( row.concept().properties().id().equals( concept.properties().id() ) ) {
            rowsToRemove.add( row );
         }
      }
      
      table.getItems().removeAll( rowsToRemove );
   }//End Method

   public void detach() {
      this.items.removeListener( synchronizer );
   }//End Method

}//End Class
