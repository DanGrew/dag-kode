package uk.dangrew.kode.datetime;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.Before;
import org.junit.Test;

public class DateTimeFormatsTest {

   private static final LocalDateTime SUBJECT = LocalDateTime.of( 2018, 5, 8, 8, 3 );
   
   private DateTimeFormats systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new DateTimeFormats();
   }//End Method

   @Test public void shouldFormatWithReadableDay() {
      assertThat( systemUnderTest.nameNumberMonthYear( LocalDate.of( 2018, 1, 23 ) ), is( "Tuesday 23 January 2018" ) );
      assertThat( systemUnderTest.nameNumberMonthYear( LocalDate.of( 2018, 1, 3 ) ), is( "Wednesday 03 January 2018" ) );
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
   
   @Test public void shouldFormatToDayBeginning(){
      assertThat( systemUnderTest.toDayBeginningEpochSeconds( SUBJECT.toLocalDate() ), is( 1525737600L ) );
      assertThat( systemUnderTest.toDayBeginningEpochSeconds( null ), is( 0L ) );
   }//End Method

}//End Class
