/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table.controller;

import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.controller.ConceptTableViewController;

/**
 * Basic implementation of {@link ConceptTableViewController} that does nothing.
 * @param <TypeT> the type of {@link Concept}.
 */
public class UnresponsiveConceptTableController< TypeT extends Concept > implements ConceptTableViewController< TypeT > {

   @Override public void associate( ConceptTable< TypeT > table ) {}//End Method
   
   @Override public TypeT createConcept() {
      return null;
   }//End Method

   @Override public void removeSelectedConcept() {}//End Method
   
   @Override public void copySelectedConcept() {}//End Method
   
}//End Class
