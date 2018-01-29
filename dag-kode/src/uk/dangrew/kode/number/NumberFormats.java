package uk.dangrew.kode.number;

import java.text.DecimalFormat;

public class NumberFormats {

   private static final DecimalFormat TWO_DECIMAL_PLACE = new DecimalFormat("#.00");;
   
   public String twoDecimalPlace( double number ) {
      return TWO_DECIMAL_PLACE.format( number );
   }//End Method
   
}//End Class
