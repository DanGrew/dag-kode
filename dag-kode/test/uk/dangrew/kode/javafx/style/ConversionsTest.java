package uk.dangrew.kode.javafx.style;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ConversionsTest {

   private Conversions systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new Conversions();
   }//End Method

   @Test public void shouldFormatDoubles() {
      assertThat( systemUnderTest.format( 12345.6789 ), is( "12345.68" ) );
      assertThat( systemUnderTest.format( null ), is( "0.0" ) );
   }//End Method
   
   @Test public void shouldConvertDoublesToStrings() {
      assertThat( systemUnderTest.doubleToStringFunction().apply( 12345.6789 ), is( "12345.68" ) );
      assertThat( systemUnderTest.doubleToStringFunction().apply( null ), is( "0.0" ) );
   }//End Method
   
   @Test public void shouldConvertStringsToDoubles() {
      assertThat( systemUnderTest.stringToDoubleFunction().apply( "25" ), is( 25.0 ) );
      assertThat( systemUnderTest.stringToDoubleFunction().apply( "anything" ), is( 0.0 ) );
   }//End Method

}//End Class
