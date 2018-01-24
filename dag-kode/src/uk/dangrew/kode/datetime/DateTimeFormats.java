package uk.dangrew.kode.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeFormats {

   private static final DateTimeFormatter NAME_NUMBERMONTH_YEAR = DateTimeFormatter.ofPattern( "EEEE dd MMMM yyyy" );
   
   public String nameNumberMonthYear( LocalDate date ) {
      return NAME_NUMBERMONTH_YEAR.format( date );
   }//End Method
   
}//End Class
