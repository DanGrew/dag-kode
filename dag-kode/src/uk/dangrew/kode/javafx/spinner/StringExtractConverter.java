/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.spinner;

import java.util.function.Function;

import javafx.util.StringConverter;

/**
 * {@link StringExtractConverter} provides a {@link StringConverter} with a covenient interfae
 * that accepts lambdas.
 */
public class StringExtractConverter< TypeT > extends StringConverter< TypeT >{

   private final Function< TypeT, String > extractor;
   private final Function< String, TypeT> resolver;
   private final String defaultResponse;
   
   /**
    * Constructs a new {@link StringConverter}.
    * @param extractor the extractor of the {@link String}.
    * @param resolver the resolver of {@link String} to the object.
    * @param defaultResponse the default {@link String} when object is null.
    */
   public StringExtractConverter( 
            Function< TypeT, String > extractor, 
            Function< String, TypeT > resolver,
            String defaultResponse
   ) {
      this.extractor = extractor;
      this.resolver = resolver;
      this.defaultResponse = defaultResponse;
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public String toString( TypeT object ) {
      if ( object == null ) {
         return defaultResponse;
      }
      return extractor.apply( object );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public TypeT fromString( String string ) {
      if ( string == null ) {
         return null;
      }
      return resolver.apply( string );
   }//End Method

}//End Class
