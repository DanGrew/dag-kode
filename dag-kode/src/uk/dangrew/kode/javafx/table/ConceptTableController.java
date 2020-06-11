/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table;

import uk.dangrew.kode.concept.Concept;

/**
 * {@link ConceptTableController} provides a controller interface for a {@link ConceptTable}.
 */
public interface ConceptTableController< TypeT > {

   /**
    * Instruction to create a new {@link Concept} of the associated type.
    * @return the created {@link Concept}.
    */
   public TypeT createConcept();

   /**
    * Instruction to remove the currently selected {@link Concept}.
    */
   public void removeSelectedConcept();

   /**
    * Method to copy the selected {@link Concept} in the {@link uk.dangrew.kode.concept.ConceptStore}.
    */
   public void copySelectedConcept();
   
}//End Interface

