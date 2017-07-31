package uk.dangrew.kode.observable;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

public class PrivatelyModifiableObservableMapImplTest {

   private ObservableMap< Object, Object > backingMap;
   private PrivatelyModifiableObservableMapImpl< Object, Object > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      backingMap = spy( FXCollections.observableHashMap() );
      systemUnderTest = new PrivatelyModifiableObservableMapImpl<>( backingMap );
   }//End Method

   @Test public void size() {
      when( backingMap.size() ).thenReturn( 0, 20, 3 );
      assertThat( systemUnderTest.size(), is( 0 ) );
      assertThat( systemUnderTest.size(), is( 20 ) );
      assertThat( systemUnderTest.size(), is( 3 ) );
   }//End Method

   @Test public void isEmpty() {
      when( backingMap.isEmpty() ).thenReturn( true, false, true );
      assertThat( systemUnderTest.isEmpty(), is( true ) );
      assertThat( systemUnderTest.isEmpty(), is( false ) );
      assertThat( systemUnderTest.isEmpty(), is( true ) );
   }//End Method

   @Test public void containsKey() {
      when( backingMap.containsKey( Mockito.any() ) ).thenReturn( true, false, true );
      assertThat( systemUnderTest.containsKey( new Object() ), is( true ) );
      assertThat( systemUnderTest.containsKey( new Object() ), is( false ) );
      assertThat( systemUnderTest.containsKey( new Object() ), is( true ) );
   }//End Method

   @Test public void containsValue() {
      when( backingMap.containsValue( Mockito.any() ) ).thenReturn( true, false, true );
      assertThat( systemUnderTest.containsValue( new Object() ), is( true ) );
      assertThat( systemUnderTest.containsValue( new Object() ), is( false ) );
      assertThat( systemUnderTest.containsValue( new Object() ), is( true ) );
   }//End Method

   @Test public void get() {
      Object object = new Object();
      when( systemUnderTest.get( Mockito.any() ) ).thenReturn( object, null, object );
      assertThat( systemUnderTest.get( new Object() ), is( object ) );
      assertThat( systemUnderTest.get( new Object() ), is( nullValue() ) );
      assertThat( systemUnderTest.get( new Object() ), is( object ) );
   }//End Method

   @Test( expected = UnsupportedOperationException.class ) public void put() {
      systemUnderTest.put( new Object(), new Object() );
   }//End Method

   @Test( expected = UnsupportedOperationException.class ) public void remove() {
      systemUnderTest.remove( new Object() );
   }//End Method

   @Test( expected = UnsupportedOperationException.class ) public void putAll() {
      systemUnderTest.putAll( new HashMap<>() );
   }//End Method

   @Test( expected = UnsupportedOperationException.class ) public void clear() {
      systemUnderTest.clear();
   }//End Method

   @Test public void keySet() {
      Set< Object > keys = new HashSet<>();
      when( backingMap.keySet() ).thenReturn( keys, null, keys );
      assertThat( systemUnderTest.keySet(), is( keys ) );
      assertThat( systemUnderTest.keySet(), is( nullValue() ) );
      assertThat( systemUnderTest.keySet(), is( keys ) );
   }//End Method

   @Test public void values() {
      Set< Object > values = new HashSet<>();
      when( backingMap.values() ).thenReturn( values, null, values );
      assertThat( systemUnderTest.values(), is( values ) );
      assertThat( systemUnderTest.values(), is( nullValue() ) );
      assertThat( systemUnderTest.values(), is( values ) );
   }//End Method

   @Test public void entrySet() {
      Set< Entry< Object, Object > > entries = new HashSet<>();
      when( backingMap.entrySet() ).thenReturn( entries, null, entries );
      assertThat( systemUnderTest.entrySet(), is( entries ) );
      assertThat( systemUnderTest.entrySet(), is( nullValue() ) );
      assertThat( systemUnderTest.entrySet(), is( entries ) );
   }//End Method

   @Test public void addInvalidationListener() {
      InvalidationListener listener = mock( InvalidationListener.class );
      systemUnderTest.addListener( listener );
      verify( backingMap ).addListener( listener );
   }//End Method

   @Test public void removeInvalidationListener() {
      InvalidationListener listener = mock( InvalidationListener.class );
      systemUnderTest.removeListener( listener );
      verify( backingMap ).removeListener( listener );
   }//End Method

   @Test public void addMapListener() {
      MapChangeListener< Object, Object > listener = mock( MapChangeListener.class );
      systemUnderTest.addListener( listener );
      verify( backingMap ).addListener( listener );
   }//End Method

   @Test public void removeMapListener() {
      MapChangeListener< Object, Object > listener = mock( MapChangeListener.class );
      systemUnderTest.removeListener( listener );
      verify( backingMap ).removeListener( listener );
   }//End Method

}//End Class
