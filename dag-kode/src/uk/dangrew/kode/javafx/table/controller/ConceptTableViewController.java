/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table.controller;

import uk.dangrew.kode.javafx.table.base.ConceptTable;

/**
 * {@link ConceptTableViewController} provides a controller interface for a {@link ConceptTable}.
 */
public interface ConceptTableViewController< TypeT > extends ConceptTableController< TypeT >{

   /**
    * Associated the {@link ConceptTableController} with the {@link ConceptTable}.
    * @param table the {@link ConceptTable} to control.
    */
   public void associate( ConceptTable< TypeT > table );
   
}//End Interface

