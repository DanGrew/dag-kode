/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table.controls;

import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.base.ConceptTableRow;

/**
 * {@link ConceptTableWithControls} provides a {@link ConceptTable} with {@link BasicConceptControls}.
 */
public class ConceptTableWithControls< TypeT > extends TableWithControls< ConceptTableRow< TypeT >, TypeT > {

   public ConceptTableWithControls( String title, ConceptTable< TypeT > table, TableControls controls ) {
      this( new JavaFxStyle(), title, table, controls );
   }//End Constructor
   
   public ConceptTableWithControls(
            JavaFxStyle styling, 
            String title, 
            ConceptTable< TypeT > table,
            TableControls controls
   ) {
      super( styling, title, table, controls );
   }//End Constructor

   @Override public ConceptTable< TypeT > table() {
      return (ConceptTable< TypeT > )super.table();
   }//End Method

}//End Class
