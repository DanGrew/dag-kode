package uk.dangrew.kode.datetime;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Before;
import org.junit.Test;

public class TimestampFormatTest {

   private static final LocalDateTime SUBJECT = LocalDateTime.of( 2018, 5, 8, 8, 3 );
   
   private TimestampFormat systemUnderTest;
   
   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new TimestampFormat();
   }//End Method
   
   @Test public void shouldFormatTimestamp(){
      assertThat( systemUnderTest.toTimestampString( SUBJECT ), is( "08-05-2018 08:03:00" ) );
      assertThat( systemUnderTest.toTimestampString( ( LocalDateTime )null ), is( DateTimeFormats.UNKNOWN ) );
   }//End Method
   
   @Test public void shouldFormatTimestampSeconds(){
      assertThat( systemUnderTest.toTimestampString( SUBJECT.toEpochSecond( ZoneOffset.UTC ) ), is( "08-05-2018 08:03:00" ) );
      assertThat( systemUnderTest.toTimestampString( ( Number )null ), is( DateTimeFormats.UNKNOWN ) );
   }//End Method
   
   @Test public void shouldParseTimestamp(){
      assertThat( systemUnderTest.parseTimestamp( systemUnderTest.toTimestampString( SUBJECT ) ), is( SUBJECT ) );
      assertThat( systemUnderTest.parseTimestamp( null ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldParseTimestampToEpochSeconds(){
      assertThat( systemUnderTest.parseTimestampToEpochSeconds( systemUnderTest.toTimestampString( SUBJECT ) ), is( SUBJECT.toEpochSecond( ZoneOffset.UTC ) ) );
      assertThat( systemUnderTest.parseTimestampToEpochSeconds( null ), is( 0L ) );
   }//End Method
   
   @Test public void shouldConvertBetweenEpochSecondsAndTimestamp(){
      LocalDateTime now = LocalDateTime.now();
      now = now.minusNanos( now.getNano() );
      
      long seconds = systemUnderTest.toEpochSeconds( now );
      assertThat( systemUnderTest.fromEpochSeconds( seconds ), is( now ) );
   }//End Method

}//End Class
