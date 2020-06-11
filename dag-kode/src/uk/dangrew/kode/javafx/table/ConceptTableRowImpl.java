/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table;

/**
 * Basic implmentation of {@link ConceptTableRow}.
 * @param <TypeT> the type of object being represented in a row.
 */
public class ConceptTableRowImpl< TypeT > implements ConceptTableRow< TypeT > {

   private final TypeT concept;
   
   public ConceptTableRowImpl( TypeT concept ) {
      this.concept = concept;
   }//End Constructor
   
   @Override public TypeT concept(){
      return concept;
   }//End Method
   
}//End Class
