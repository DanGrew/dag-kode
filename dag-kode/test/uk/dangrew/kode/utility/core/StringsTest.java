package uk.dangrew.kode.utility.core;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.launch.TestApplication;

public class StringsTest {

   private Strings systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new Strings();
   }//End Method

   @Test public void shouldFindAtStart() {
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED]" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED][n]" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED][]" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED][[" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED]]]" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED][][]" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED][[]]" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "[RED]anything" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "(", ")", "(RED)(n)" ), is( "RED" ) );
      assertThat( systemUnderTest.findAtStart( "[", "]", "anything[RED]" ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldReplaceAtStart(){
      assertThat( systemUnderTest.replaceAtStart( "[RED}", "[", "{" ), is( "{RED}" ) );
      assertThat( systemUnderTest.replaceAtStart( "[RED]", "(", "{" ), is( "[RED]" ) );
      assertThat( systemUnderTest.replaceAtStart( "anything[RED]", "[", "{" ), is( "anything[RED]" ) );
   }//End Method
   
   @Test public void shouldRemoveAtStart(){
      assertThat( systemUnderTest.removeAtStart( "[RED}", "[" ), is( "RED}" ) );
      assertThat( systemUnderTest.removeAtStart( "[RED]", "(" ), is( "[RED]" ) );
      assertThat( systemUnderTest.removeAtStart( "anything[RED]", "[" ), is( "anything[RED]" ) );
   }//End Method

}//End Class
