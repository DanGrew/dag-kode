package uk.dangrew.kode.javafx.table.column;

import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.column.ConceptTableColumnsPopulator;

/**
 * {@link ConceptTableColumnsPopulator} wraping around another enforcing that columns are not editable.
 * @param <TypeT> the type of {@link Concept}.
 */
public class ReadOnlyTableColumns< TypeT extends Concept > implements ConceptTableColumnsPopulator< TypeT >{

   private ConceptTableColumnsPopulator< TypeT > original;
   
   public ReadOnlyTableColumns( ConceptTableColumnsPopulator< TypeT > original ) {
      this.original = original;
   }//End Constructor
   
   @Override public void populateColumns( ConceptTable< TypeT > table ) {
      original.populateColumns( table );
      table.getColumns().forEach( c -> c.setEditable( false ) );
   }//End Method

}//End Class
