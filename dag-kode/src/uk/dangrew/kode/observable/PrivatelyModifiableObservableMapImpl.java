/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.observable;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javafx.beans.InvalidationListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

/**
 * {@link ObservableMap} that can't be modified.
 */
public class PrivatelyModifiableObservableMapImpl< KeyT, ValueT > implements ObservableMap< KeyT, ValueT > { 

   private final ObservableMap< KeyT, ValueT > backingMap;

   /**
    * Constructs a new {@link PrivatelyModifiableObservableMapImpl}.
    * @param backingMap the backing {@link ObservableMap}.
    */
   public PrivatelyModifiableObservableMapImpl( ObservableMap< KeyT, ValueT > backingMap ) {
      this.backingMap = backingMap;
   }//End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public int size() {
      return backingMap.size();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean isEmpty() {
      return backingMap.isEmpty();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean containsKey( Object key ) {
      return backingMap.containsKey( key );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean containsValue( Object ValueT ) {
      return backingMap.containsValue( ValueT );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public ValueT get( Object key ) {
      return backingMap.get( key );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public ValueT put( KeyT key, ValueT ValueT ) {
      throw new UnsupportedOperationException( "Not modifiable." );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public ValueT remove( Object key ) {
      throw new UnsupportedOperationException( "Not modifiable." );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void putAll( Map< ? extends KeyT, ? extends ValueT > m ) {
      throw new UnsupportedOperationException( "Not modifiable." );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void clear() {
      throw new UnsupportedOperationException( "Not modifiable." );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public Set< KeyT > keySet() {
      return backingMap.keySet();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public Collection< ValueT > values() {
      return backingMap.values();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public Set< Entry< KeyT, ValueT > > entrySet() {
      return backingMap.entrySet();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addListener( InvalidationListener listener ) {
      backingMap.addListener( listener );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void removeListener( InvalidationListener listener ) {
      backingMap.removeListener( listener );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void addListener( MapChangeListener< ? super KeyT, ? super ValueT > listener ) {
      backingMap.addListener( listener );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void removeListener( MapChangeListener< ? super KeyT, ? super ValueT > listener ) {
      backingMap.removeListener( listener );
   }//End Method

}//End Class
