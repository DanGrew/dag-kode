/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.synchronization;

import java.util.*;
import java.util.function.BiConsumer;

import com.sun.javafx.collections.ObservableMapWrapper;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;

/**
 * The {@link SynchronizedObservableMap} provides an extension to {@link ObservableMapWrapper}
 * that attempts to resolve a weakness in the Synchronized Map in {@link javafx.collections.FXCollections} which
 * does not synchronize the forEach method so that it can conflict with put commands.
 */
public class SynchronizedObservableMap< K, V > implements ObservableMap<K, V> {

   private final ObservableMap<K, V> backingObservable;
   private final Object lock;
   
   /**
    * Constructs a new {@link SynchronizedObservableMap} with a {@link HashMap}.
    */
   public SynchronizedObservableMap(){
      this( new HashMap<>() );
   }//End Constructor
   
   /**
    * Constructs a new {@link SynchronizedObservableMap} wrapping the given backing map.
    * @param map the backing {@link Map}.
    */
   public SynchronizedObservableMap( Map< K, V > map ) {
      this.backingObservable = FXCollections.synchronizedObservableMap(FXCollections.observableMap(map));
      this.lock = new Object();
   }//End Constructor

   @Override public int size() {
      return backingObservable.size();
   }

   @Override public boolean isEmpty() {
      return backingObservable.isEmpty();
   }

   @Override public boolean containsKey(Object key) {
      return backingObservable.containsKey(key);
   }

   @Override public boolean containsValue(Object value) {
      return backingObservable.containsValue(value);
   }

   @Override public V get(Object key) {
      return backingObservable.get(key);
   }

   /**
    * {@inheritDoc}
    * Synchronizes the instruction.
    */
   @Override public V put( K key, V value ) {
      synchronized ( lock ) {
         return backingObservable.put( key, value );
      }
   }//End Method

   @Override public V remove(Object key) {
      return backingObservable.remove(key);
   }

   @Override public void putAll(Map<? extends K, ? extends V> m) {
      backingObservable.putAll(m);
   }

   @Override public void clear() {
      backingObservable.clear();
   }

   @Override public Set<K> keySet() {
      return backingObservable.keySet();
   }

   @Override public Collection<V> values() {
      return backingObservable.values();
   }

   @Override public Set<Entry<K, V>> entrySet() {
      return backingObservable.entrySet();
   }

   /**
    * {@inheritDoc}
    * Synchronizes the instruction.
    */
   @Override public void forEach( BiConsumer<? super K, ? super V> action) {
      synchronized ( lock ) {
         backingObservable.forEach( action );
      }
   }//End Method

   @Override public void addListener(MapChangeListener<? super K, ? super V> listener) {
      backingObservable.addListener(listener);
   }

   @Override public void removeListener(MapChangeListener<? super K, ? super V> listener) {
      backingObservable.removeListener(listener);
   }

   @Override public void addListener(InvalidationListener listener) {
      backingObservable.addListener(listener);
   }

   @Override public void removeListener(InvalidationListener listener) {
      backingObservable.removeListener(listener);
   }
}//End Class
