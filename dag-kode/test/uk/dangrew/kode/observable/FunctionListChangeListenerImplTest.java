/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.observable;

import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sun.javafx.collections.NonIterableChange.SimpleAddChange;
import com.sun.javafx.collections.NonIterableChange.SimpleRemovedChange;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import uk.dangrew.kode.comparator.Comparators;
import javafx.collections.ObservableList;

/**
 * {@link FunctionListChangeListenerImpl} test.
 */
public class FunctionListChangeListenerImplTest {

   private ObservableList< String > testList;
   @Mock private Consumer< String > addFunction;
   @Mock private Consumer< String > removeFunction;
   @Mock private Runnable permutationFunction;
   private ListChangeListener< String > systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      MockitoAnnotations.initMocks( this );
      testList = FXCollections.observableArrayList( 
               "something", "in", "this", "list", "for", "testing", "purposes" 
      );
      systemUnderTest = new FunctionListChangeListenerImpl<>( addFunction, removeFunction, permutationFunction );
   }//End Method
   
   @Test public void shouldCallFunctionWhenAdded() {
      Change< String > change = new SimpleAddChange<>( 0, testList.size(), testList );
      systemUnderTest.onChanged( change );
      for ( int i = 0; i < testList.size(); i++ ) {
         Mockito.verify( addFunction ).accept( testList.get( i ) );
      }
      Mockito.verifyNoMoreInteractions( addFunction, removeFunction );
   }//End Method
   
   @Test public void shouldIgnoreNullRemoveFunction() {
      systemUnderTest = new FunctionListChangeListenerImpl<>( addFunction, null );
      Change< String > change = new SimpleRemovedChange<>( 0, 0, testList.get( 3 ), testList );
      systemUnderTest.onChanged( change );
   }//End Method
   
   @Test public void shouldIgnoreNullAddFunction() {
      systemUnderTest = new FunctionListChangeListenerImpl<>( null, removeFunction );
      Change< String > change = new SimpleAddChange<>( 0, testList.size(), testList );
      systemUnderTest.onChanged( change );
   }//End Method
   
   @Test public void shouldCallFunctionWhenRemoved() {
      Change< String > change = new SimpleRemovedChange<>( 0, 0, testList.get( 3 ), testList );
      systemUnderTest.onChanged( change );
      Mockito.verify( removeFunction ).accept( testList.get( 3 ) );
      Mockito.verifyNoMoreInteractions( addFunction, removeFunction );
   }//End Method

   @Test public void shouldCallFunctionWhenAddedAndRemoved() {
      Change< String > change = new SimpleRemovedChange<>( 0, 3, testList.get( 3 ), testList );
      systemUnderTest.onChanged( change );
      for ( int i = 0; i < 3; i++ ) {
         Mockito.verify( addFunction ).accept( testList.get( i ) );
      }
      Mockito.verify( removeFunction ).accept( testList.get( 3 ) );
      Mockito.verifyNoMoreInteractions( addFunction, removeFunction );
   }//End Method
   
   @Test public void shouldAvoidAddConcurrencyException(){
      systemUnderTest = new FunctionListChangeListenerImpl<>( item -> testList.add( item ), removeFunction );
      Change< String > change = new SimpleRemovedChange<>( 0, 3, testList.get( 3 ), testList );
      systemUnderTest.onChanged( change );
      Assert.assertEquals( 10, testList.size() );
   }
   
   @Test public void shouldAvoidRemoveConcurrencyException(){
      systemUnderTest = new FunctionListChangeListenerImpl<>( addFunction, item -> testList.remove( item ) );
      Change< String > change = new SimpleRemovedChange<>( 0, 0, testList.get( 3 ), testList );
      systemUnderTest.onChanged( change );
      Assert.assertEquals( 6, testList.size() );
   }//End Method
   
   @Test public void shouldCallPermutationFunction(){
      testList.addListener( systemUnderTest );
      testList.sort( Comparators.STRING_ALPHABETICAL );
      verify( permutationFunction ).run();
   }//End Method
}//End Class
