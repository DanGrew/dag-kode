package uk.dangrew.kode.datetime;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimestampFormat {

   private static final String TIMESTAMP_PATTERN = "dd-MM-yyyy HH:mm:ss";
   private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern( TIMESTAMP_PATTERN );
   
   public String toTimestampString( LocalDateTime timestamp ) {
      if ( timestamp == null ) {
         return DateTimeFormats.UNKNOWN;
      }
      return TIMESTAMP.format( timestamp );
   }//End Method
   
   public String toTimestampString( Number epochSeconds ) {
      if ( epochSeconds == null ) {
         return DateTimeFormats.UNKNOWN;
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
   
   public long toEpochSeconds( LocalDateTime timestamp ) {
      if ( timestamp == null ) {
         return 0L;
      }
      return timestamp.toEpochSecond( ZoneOffset.UTC );
   }//End Method
   
   public LocalDateTime fromEpochSeconds( Number seconds ) {
      return LocalDateTime.ofEpochSecond( seconds.longValue(), 0, ZoneOffset.UTC );
   }//End Method
   
   public String pattern(){
      return TIMESTAMP_PATTERN;
   }//End Method
   
   public int patternLength(){
      return TIMESTAMP_PATTERN.length();
   }//End Method
   
}//End Class
