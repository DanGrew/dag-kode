/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.synchronization;

import org.junit.Before;
import org.junit.Test;
import uk.dangrew.kode.TestCommon;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * {@link SynchronizedObservableList} test.
 */
public class SynchronizedObservableListTest {

   private SynchronizedObservableList< String > systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      systemUnderTest = new SynchronizedObservableList<>();
   }//End Method
   
   @Test public void addShouldCallThrough() {
      systemUnderTest.add( "anything" );
      assertThat( systemUnderTest, contains( "anything" ) );
   }//End Method
   
   @Test public void foreachShouldCallThrough() {
      List< String > collected = new ArrayList<>();
      Consumer< String > consumer = item -> collected.add( item );
      
      systemUnderTest.add( "one" );
      systemUnderTest.add( "three" );
      systemUnderTest.add( "two" );
      systemUnderTest.add( "four" );
      
      systemUnderTest.forEach( consumer );
      assertThat( collected, contains( "one", "three", "two", "four" ) );
   }//End Method
   
   @Test public void addAndForeachShouldBeSynchronized() {
      systemUnderTest = new SynchronizedObservableList<>();
      TestCommon.assertConcurrencyIsNotAnIssue(
               count -> systemUnderTest.add( "" + count ), 
               count -> systemUnderTest.forEach( item -> item.toString() ), 
               1000 
      );
   }//End Method

}//End Class
