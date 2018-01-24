package uk.dangrew.kode.datetime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class DateTimeFormatsTest {

   private DateTimeFormats systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new DateTimeFormats();
   }//End Method

   @Test public void shouldFormatWithReadableDay() {
      assertThat( systemUnderTest.nameNumberMonthYear( LocalDate.of( 2018, 1, 23 ) ), is( "Tuesday 23 January 2018" ) );
      assertThat( systemUnderTest.nameNumberMonthYear( LocalDate.of( 2018, 1, 3 ) ), is( "Wednesday 03 January 2018" ) );
   }//End Method

}//End Class
