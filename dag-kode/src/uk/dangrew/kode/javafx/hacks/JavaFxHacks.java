package uk.dangrew.kode.javafx.hacks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

public class JavaFxHacks {

   static final String LOOKUP_TABLE_ROW_CELL = ".table-row-cell";

   public < RowObjectT > List< TableRow< RowObjectT > > lookupTableRows( TableView< RowObjectT > table ) {
      Set< Node > tableRows = table.lookupAll( LOOKUP_TABLE_ROW_CELL );
      if ( tableRows.isEmpty() ) {
         return Collections.emptyList();
      }
      if ( tableRows.iterator().next() instanceof TableRow ) {
         
         List< TableRow< RowObjectT > > converted = new ArrayList<>( table.getItems().size() );
         for ( Node node : tableRows ) {
            
            @SuppressWarnings("unchecked")
            TableRow< RowObjectT > row = ( TableRow< RowObjectT > ) node;
            if ( row.getItem() == null ) {
               continue;
            } else if ( !table.getItems().contains( row.getItem() ) ) {
               continue;
            }
            
            int index = table.getItems().indexOf( row.getItem() );
            if ( index >= converted.size() ) {
               converted.add( row );
            } else {
               converted.add( index, row );
            }
         }
         
         return converted;
      }
      return Collections.emptyList();
   }//End Method
   
}//End Class
