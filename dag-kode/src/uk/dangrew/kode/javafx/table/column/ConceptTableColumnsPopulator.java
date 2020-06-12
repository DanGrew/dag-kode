/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table.column;

import uk.dangrew.kode.javafx.table.base.ConceptTable;

/**
 * {@link ConceptTableColumnsPopulator} provides an interface for populating a {@link ConceptTable}.
 */
public interface ConceptTableColumnsPopulator< TypeT > {
   
   /**
    * Instructing to populate the {@link ConceptTable} with the relevant {@link javafx.scene.control.TableColumn}s.
    * @param table the {@link ConceptTable} to populate.
    */
   public void populateColumns( ConceptTable< TypeT > table );

}//End Interface

