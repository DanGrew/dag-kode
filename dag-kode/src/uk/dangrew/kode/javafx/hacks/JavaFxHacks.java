package uk.dangrew.kode.javafx.hacks;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.TableView;

public class JavaFxHacks {

   static final String LOOKUP_TABLE_ROW_CELL = ".table-row-cell";

   public Optional< Node > lookupTableRow( TableView< ? > table, int row ) {
      Set< Node > tableRowCell = table.lookupAll( LOOKUP_TABLE_ROW_CELL );
      if ( tableRowCell.isEmpty() || row >= tableRowCell.size() ) {
         return Optional.empty();
      }
      return Optional.of( new ArrayList<>( tableRowCell ).get( row ) );
   }//End Method
   
}//End Class
