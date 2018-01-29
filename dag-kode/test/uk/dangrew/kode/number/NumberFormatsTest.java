package uk.dangrew.kode.number;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class NumberFormatsTest {

   private NumberFormats systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new NumberFormats();
   }//End Method

   @Test public void shouldFormatWithReadableDay() {
      assertThat( systemUnderTest.twoDecimalPlace( 2.0 ), is( "2.00" ) );
      assertThat( systemUnderTest.twoDecimalPlace( 11.11111 ), is( "11.11" ) );
   }//End Method

}//End Class
