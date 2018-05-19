package uk.dangrew.kode.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeFormats {

   public static final String UNKNOWN = "Unknown";
   
   private static final DateTimeFormatter NAME_NUMBERMONTH_YEAR = DateTimeFormatter.ofPattern( "EEEE dd MMMM yyyy" );
   private static final DateTimeFormatter DATE_TIMESTAMP = DateTimeFormatter.ofPattern( "dd-MM-yyyy" );
   private static final DateTimeFormatter TIME_TIMESTAMP = DateTimeFormatter.ofPattern( "HH:mm:ss" );
   
   public String nameNumberMonthYear( LocalDate date ) {
      return NAME_NUMBERMONTH_YEAR.format( date );
   }//End Method
   
   public String toDateTimestampString( LocalDate date ) {
      if ( date == null ) {
         return UNKNOWN;
      }
      return DATE_TIMESTAMP.format( date );
   }//End Method
   
   public String toTimeTimestampString( LocalTime time ) {
      if ( time == null ) {
         return UNKNOWN;
      }
      return TIME_TIMESTAMP.format( time );
   }//End Method
   
   public long toDayBeginningEpochSeconds( LocalDate timestamp ) {
      if ( timestamp == null ) {
         return 0L;
      }
      return LocalDateTime.of( timestamp, LocalTime.MIN ).toEpochSecond( ZoneOffset.UTC );
   }//End Method
   
}//End Class
