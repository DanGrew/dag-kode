/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.style;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * {@link Conversions} provides common conversion tasks and {@link Function}s.
 */
public class Conversions {
   
   private static final DecimalFormat FORMAT = new DecimalFormat( "###.##" );
   
   private static final Function< Double, String > DOUBLE_TO_STRING = d -> {
      try {
         return FORMAT.format( d );
      } catch ( IllegalArgumentException e ) {
         return "0.0";
      }
   };

   private static final Function< String, Double > STRING_TO_DOUBLE = s -> {
      try {
         return Double.valueOf( s );
      } catch ( NumberFormatException e ) {
         return 0.0;
      }
   };
   
   private static final Function< String, Double > NULLABLE_STRING_TO_DOUBLE = s -> {
      if ( s == null ) {
         return null;
      }
      try {
         return Double.valueOf( s );
      } catch ( NumberFormatException e ) {
         return null;
      }
   };

   /**
    * Format the given as a {@link String} to 2dp.
    * @param value the value to format.
    * @return the formatted value, or 0.0 if not possible.
    */
   public String format( Double value ) {
      return doubleToStringFunction().apply( value );
   }//End Method

   /**
    * Access to a {@link Function} that converts {@link Double}s to {@link String}s safely.
    * @return the {@link Function}.
    */
   public Function< Double, String > doubleToStringFunction() {
      return DOUBLE_TO_STRING;
   }//End Method

   /**
    * Access to a {@link Function} that converts {@link String}s to {@link Double}s safely.
    * @return the {@link Function}.
    */
   public Function< String, Double > stringToDoubleFunction() {
      return STRING_TO_DOUBLE;
   }//End Method
   
   public Function< String, Double > nullableStringToDoubleFunction() {
      return NULLABLE_STRING_TO_DOUBLE;
   }//End Method
   
}//End Class
