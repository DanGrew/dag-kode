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
import java.util.function.Consumer;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * {@link SynchronizedObservableList} provides an {@link ObservableListWrapper}
 * that synchronizes certain calls to avoid concurrency issues.
 */
public class SynchronizedObservableList< TypeT > implements ObservableList<TypeT> {

   private final ObservableList<TypeT> backingObservable;
   private final Object lock;
   
   /**
    * Constructs a new {@link SynchronizedObservableList} with a {@link FXCollections#observableArrayList()}.
    */
   public SynchronizedObservableList(){
      this.backingObservable = FXCollections.observableArrayList();
      this.lock = new Object();
   }//End Constructor

   @Override public int size() {
      return backingObservable.size();
   }

   @Override public boolean isEmpty() {
      return backingObservable.isEmpty();
   }

   @Override public boolean contains(Object o) {
      return backingObservable.contains(o);
   }

   @Override public Iterator<TypeT> iterator() {
      return backingObservable.iterator();
   }

   @Override public Object[] toArray() {
      return backingObservable.toArray();
   }

   @Override public <T> T[] toArray(T[] a) {
      return backingObservable.toArray(a);
   }

   /**
    * {@inheritDoc}
    * Synchronizes the instruction.
    */
   @Override public boolean add( TypeT item ) {
      synchronized ( lock ) {
         return backingObservable.add( item );
      }
   }//End Method

   @Override public boolean remove(Object o) {
      return backingObservable.remove(o);
   }

   @Override public boolean containsAll(Collection<?> c) {
      return backingObservable.containsAll(c);
   }

   @Override public boolean addAll(Collection<? extends TypeT> c) {
      return backingObservable.addAll(c);
   }

   @Override public boolean addAll(int index, Collection<? extends TypeT> c) {
      return backingObservable.addAll(index, c);
   }

   @Override public boolean removeAll(Collection<?> c) {
      return backingObservable.removeAll(c);
   }

   @Override public boolean retainAll(Collection<?> c) {
      return backingObservable.retainAll(c);
   }

   @Override public void clear() {
      backingObservable.clear();
   }

   @Override public TypeT get(int index) {
      return backingObservable.get(index);
   }

   @Override public TypeT set(int index, TypeT element) {
      return backingObservable.set(index, element);
   }

   @Override public void add(int index, TypeT element) {
      backingObservable.add(index, element);
   }

   @Override public TypeT remove(int index) {
      return backingObservable.remove(index);
   }

   @Override public int indexOf(Object o) {
      return backingObservable.indexOf(o);
   }

   @Override public int lastIndexOf(Object o) {
      return backingObservable.lastIndexOf(o);
   }

   @Override public ListIterator<TypeT> listIterator() {
      return backingObservable.listIterator();
   }

   @Override public ListIterator<TypeT> listIterator(int index) {
      return backingObservable.listIterator(index);
   }

   @Override public List<TypeT> subList(int fromIndex, int toIndex) {
      return backingObservable.subList(fromIndex, toIndex);
   }

   /**
    * {@inheritDoc}
    * Synchronizes the instruction.
    */
   @Override public void forEach( Consumer< ? super TypeT > action) {
      synchronized ( lock ) {
         backingObservable.forEach( action );
      }
   }//End Method

   @Override public void addListener(ListChangeListener<? super TypeT> listener) {
      backingObservable.addListener(listener);
   }

   @Override public void removeListener(ListChangeListener<? super TypeT> listener) {
      backingObservable.removeListener(listener);
   }

   @Override public boolean addAll(TypeT... elements) {
      return backingObservable.addAll(elements);
   }

   @Override public boolean setAll(TypeT... elements) {
      return backingObservable.setAll(elements);
   }

   @Override public boolean setAll(Collection<? extends TypeT> col) {
      return backingObservable.setAll(col);
   }

   @Override public boolean removeAll(TypeT... elements) {
      return backingObservable.removeAll(elements);
   }

   @Override public boolean retainAll(TypeT... elements) {
      return backingObservable.retainAll(elements);
   }

   @Override public void remove(int from, int to) {
      backingObservable.remove(from, to);
   }

   @Override public void addListener(InvalidationListener listener) {
      backingObservable.addListener(listener);
   }

   @Override public void removeListener(InvalidationListener listener) {
      backingObservable.removeListener(listener);
   }
}//End Class
