/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table.base;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import uk.dangrew.kode.javafx.table.column.ConceptTableColumnsPopulator;
import uk.dangrew.kode.javafx.table.controller.ConceptTableViewController;

/**
 * {@link ConceptTable} provides a {@link TableView} for {@link uk.dangrew.kode.concept.Concept}s.
 */
public class ConceptTable< TypeT > 
   extends TableView< ConceptTableRow< TypeT > > 
   implements FriendlyTableView< ConceptTableRow< TypeT > > 
{

   private final ConceptTableViewController< TypeT > controller;

   public ConceptTable( 
            ConceptTableColumnsPopulator< TypeT > columnsPopulator,
            ConceptTableViewController< TypeT > controller
   ) {
      this.controller = controller;
      this.controller.associate( this );
      this.setEditable( true );
      columnsPopulator.populateColumns( this );
   }//End Constructor
   
   /**
    * Friendly access to the {@link #getItems()}.
    * @return the {@link ObservableList}.
    */
   public ObservableList< ConceptTableRow< TypeT > > getRows(){
      return getItems();
   }//End Method
   
}//End Class
