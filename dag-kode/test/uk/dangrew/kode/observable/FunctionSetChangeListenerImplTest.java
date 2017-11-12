package uk.dangrew.kode.observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.LinkedHashSet;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class FunctionSetChangeListenerImplTest {

   @Mock private Object o1;
   @Mock private Object o2;
   @Mock private Object o3;
   
   @Mock private Consumer< Object > addFunction;
   @Mock private Consumer< Object > removeFunction;
   private ObservableSet< Object > set;
   private FunctionSetChangeListenerImpl< Object > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      set = FXCollections.observableSet( new LinkedHashSet<>() );
      set.add( o1 );
      set.add( o2 );
      systemUnderTest = new FunctionSetChangeListenerImpl<>( addFunction, removeFunction );
      set.addListener( systemUnderTest );
   }//End Method

   @Test public void shouldUseAddFunction() {
      set.add( o3 );
      verify( addFunction ).accept( o3 );
      verifyZeroInteractions( removeFunction );
      
      set.add( o1 );
      verify( addFunction, never() ).accept( o1 );
      verifyZeroInteractions( removeFunction );
   }//End Method
   
   @Test public void shouldUseRemoveFunction() {
      set.remove( o2 );
      verify( removeFunction ).accept( o2 );
      verifyZeroInteractions( addFunction );
      
      set.remove( o3 );
      verify( removeFunction, never() ).accept( o3 );
      verifyZeroInteractions( addFunction );
      
      set.clear();
      verify( removeFunction ).accept( o1 );
      verify( removeFunction ).accept( o2 );
      verifyZeroInteractions( addFunction );
   }//End Method

}//End Class
