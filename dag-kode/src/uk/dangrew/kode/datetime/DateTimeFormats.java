package uk.dangrew.kode.datetime;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeFormats {

   public static final String UNKNOWN = "Unknown";
   private static final DateTimeFormatter NAME_NUMBERMONTH_YEAR = DateTimeFormatter.ofPattern( "EEEE dd MMMM yyyy" );
   private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss" );
   
   public String nameNumberMonthYear( LocalDate date ) {
      return NAME_NUMBERMONTH_YEAR.format( date );
   }//End Method
   
   public String toTimestampString( LocalDateTime timestamp ) {
      if ( timestamp == null ) {
         return UNKNOWN;
      }
      return TIMESTAMP.format( timestamp );
   }//End Method
   
   public String toTimestampString( Number epochSeconds ) {
      if ( epochSeconds == null ) {
         return UNKNOWN;
      }
      return toTimestampString( LocalDateTime.ofEpochSecond( epochSeconds.longValue(), 0, ZoneOffset.UTC ) );
   }//End Method
   
   public LocalDateTime parseTimestamp( String timestamp ) {
      if ( timestamp == null ) {
         return null;
      }
      try { 
         return LocalDateTime.parse( timestamp, TIMESTAMP );
      } catch ( DateTimeException exception ) {
         return null;
      }
   }//End Method
   
   public long parseTimestampToEpochSeconds( String timestamp ) {
      if ( timestamp == null ) {
         return 0;
      }
      LocalDateTime parsed = parseTimestamp( timestamp );
      if ( parsed == null ) {
         return 0;
      }
      return parsed.toEpochSecond( ZoneOffset.UTC );
   }//End Method
   
   public long toDayBeginningEpochSeconds( LocalDate timestamp ) {
      if ( timestamp == null ) {
         return 0L;
      }
      return LocalDateTime.of( timestamp, LocalTime.MIN ).toEpochSecond( ZoneOffset.UTC );
   }//End Method
   
}//End Class
