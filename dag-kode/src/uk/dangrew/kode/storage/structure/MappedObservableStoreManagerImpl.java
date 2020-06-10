/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.storage.structure;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import uk.dangrew.kode.observable.PrivatelyModifiableObservableListImpl;
import uk.dangrew.kode.synchronization.SynchronizedObservableList;

/**
 * The {@link MappedObservableStoreManagerImpl} provides an implementation of the {@link ObjectStoreManager} that
 * provides a mapping for quick look up and an {@link ObservableList} for event management.
 * @param <KeyTypeT> the key type.
 * @param <ObjectTypeT> the object type.
 */
public class MappedObservableStoreManagerImpl< KeyTypeT, ObjectTypeT > implements ObjectStoreManager< KeyTypeT, ObjectTypeT > {

   private final Function< ObjectTypeT, KeyTypeT > keyExtractor;
   private final Map< KeyTypeT, ObjectTypeT > objectMap;
   private final ObservableList< ObjectTypeT > objectList;
   private final PrivatelyModifiableObservableListImpl< ObjectTypeT > publiclyAvailableObjectList;
   
   /**
    * Constructs a new {@link MappedObservableStoreManagerImpl}.
    */
   public MappedObservableStoreManagerImpl( Function< ObjectTypeT, KeyTypeT > keyExtractor ) {
      this.keyExtractor = keyExtractor;
      this.objectMap = new HashMap<>();
      this.objectList = new SynchronizedObservableList<>();
      this.publiclyAvailableObjectList = new PrivatelyModifiableObservableListImpl<>( objectList );
   }//End Constructor
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean isEmpty() {
      return objectMap.isEmpty();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void store( ObjectTypeT object ) {
      KeyTypeT key = keyExtractor.apply( object );
      if ( key == null ) {
         return;
      }

      if ( objectMap.containsKey( key ) ) {
         ObjectTypeT currentObject = objectMap.remove( key );
         objectList.remove( currentObject );
      }
      
      objectMap.put( key, object );
      objectList.add( object );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public ObjectTypeT get( KeyTypeT key ) {
      if ( key == null ) return null;
      
      return objectMap.get( key );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public boolean hasKey( KeyTypeT key ) {
      if ( key == null ) return false;
      
      return objectMap.containsKey( key );
   }//End Method
   
   /**
    * {@inheritDoc}
    */
   @Override public boolean hasObject( ObjectTypeT object ) {
      if ( object == null ) return false;
      
      return objectMap.containsKey( keyExtractor.apply( object ) );
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public ObjectTypeT remove( KeyTypeT key ) {
      if ( key == null ) return null;
      
      ObjectTypeT object = objectMap.remove( key );
      objectList.remove( object );
      return object;
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public ObservableList< ObjectTypeT > objectList() {
      return publiclyAvailableObjectList;
   }//End Method

}//End Class
