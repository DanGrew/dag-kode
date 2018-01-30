/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.event.structure;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import uk.dangrew.kode.event.structure.Event;

/**
 * {@link Event} test.
 */
public class EventTest {

   private Event< Object > systemUnderTest;
   
   @Test public void shouldProvideValueGiven() {
      Object value = new Object();
      systemUnderTest = new Event<>( value );
      
      assertThat( systemUnderTest.getValue(), is( value ) );
   }//End Method

}//End Class
