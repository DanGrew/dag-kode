package uk.dangrew.kode.datetime;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.OptionalInt;

import org.junit.Test;

public class TimestampComponentsTest {

   private static final LocalDateTime NOW = LocalDateTime.now();
   
   @Test public void shouldContainPositions() {
      assertThat( TimestampComponents.Day.caretHighlightsComponent( 0 ), is( true ) );
      assertThat( TimestampComponents.Day.caretHighlightsComponent( 4 ), is( false ) );
      
      for ( int i = 0; i < 20; i++ ) {
         assertThat( TimestampComponents.Separator.caretHighlightsComponent( i ), is( false ) );
      }
   }//End Method
   
   @Test public void shouldProvideIncrementFunctions(){
      assertThat( TimestampComponents.Hour.increment( NOW ), is( NOW.plusHours( 1 ) ) );
      assertThat( TimestampComponents.Year.increment( NOW ), is( NOW.plusYears( 1 ) ) );
      assertThat( TimestampComponents.Separator.increment( NOW ), is( NOW ) );
   }//End Method
   
   @Test public void shouldProvideDecrementFunctions(){
      assertThat( TimestampComponents.Hour.decrement( NOW ), is( NOW.minusHours( 1 ) ) );
      assertThat( TimestampComponents.Year.decrement( NOW ), is( NOW.minusYears( 1 ) ) );
      assertThat( TimestampComponents.Separator.decrement( NOW ), is( NOW ) );
   }//End Method
   
   @Test public void shouldProvideStartingCaretPosition(){
      assertThat( TimestampComponents.Hour.startingCaretPosition().getAsInt(), is( 9 ) );
      assertThat( TimestampComponents.Second.startingCaretPosition().getAsInt(), is( 15 ) );
      assertThat( TimestampComponents.Separator.startingCaretPosition(), is( OptionalInt.empty() ) );
   }//End Method

}//End Class
