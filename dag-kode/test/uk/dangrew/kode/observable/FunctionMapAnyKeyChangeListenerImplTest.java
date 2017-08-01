package uk.dangrew.kode.observable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class FunctionMapAnyKeyChangeListenerImplTest {

   private Object object1;
   private Object object2;
   
   private ObservableMap< Object, Object > map;
   @Mock private Consumer< Object > consumer;
   private FunctionMapAnyKeyChangeListenerImpl< Object, Object > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      object1 = new Object();
      object2 = new Object();
      
      systemUnderTest = new FunctionMapAnyKeyChangeListenerImpl<>( consumer );
      map = FXCollections.observableHashMap();
      map.addListener( systemUnderTest );
   }//End Method

   @Test public void shouldCallOnAdd() {
      map.put( object1, object2 );
      verify( consumer ).accept( object1 );
   }//End Method
   
   @Test public void shouldCallOnRemove() {
      map.put( object1, object2 );
      verify( consumer ).accept( object1 );
      
      map.remove( object1 );
      verify( consumer, times( 2 ) ).accept( object1 );
   }//End Method
   
   @Test public void shouldCallOnChange() {
      map.put( object1, object2 );
      verify( consumer ).accept( object1 );
      
      map.put( object1, object1 );
      verify( consumer, times( 2 ) ).accept( object1 );
   }//End Method

}//End Class
