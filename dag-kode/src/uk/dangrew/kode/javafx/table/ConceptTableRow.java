package uk.dangrew.kode.javafx.table;

/**
 * Provides an interface for table rows in a {@link ConceptTable}.
 * @param <TypeT> the type of object being represented in the row.
 */
public interface ConceptTableRow< TypeT > {

   public TypeT concept();

}//End Interface
