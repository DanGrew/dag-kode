/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.event.structure;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@link EventManager} provides a mechanism for registering for events and notifying events
 * to a {@link Collection} of {@link EventSubscription}s.
 * @param <ValueT> the type of value changed.
 */
public class EventManager< ValueT > implements EventSubscription< ValueT >, EventNotification< ValueT >{

   private final ReentrantLock lock;
   private final Collection< EventSubscription< ValueT > > subscriptions;

   /**
    * Constructs a new {@link EventManager}.
    * @param subscriptions the {@link Collection} of {@link EventSubscription}s to manage. This accepted so that
    * static storage o subscriptions can be used across the system.
    * @param lock the {@link ReentrantLock} for synchronization, again allowing synchronizing across the system.
    */
   protected EventManager( Collection< EventSubscription< ValueT > > subscriptions, ReentrantLock lock ) {
      if ( subscriptions == null || lock == null ) {
         throw new IllegalArgumentException( "Must not supply null parameters." );
      }
      this.subscriptions = subscriptions;
      this.lock = lock;
   }//End Constructor
   
   /**
    * Method to clear all subscriptions, be careful of static subscriptions.
    */
   public void clearAllSubscriptions(){
      lock.lock();
      subscriptions.clear();
      lock.unlock();
   }//End Method

   /**
    * Method to register the given {@link EventSubscription} for the associated type of event.
    * @param subscriber the {@link EventSubscription} to register.
    */
   public void register( EventSubscription< ValueT > subscriber ) {
      lock.lock();
      subscriptions.add( subscriber );
      lock.unlock();
   }//End Method

   /**
    * Method to unregister the given {@link EventSubscription} to not receive the associated type of event.
    * @param subscriber the {@link EventSubscription} to unregister.
    */
   public void unregister( EventSubscription< ValueT > subscriber ) {
      lock.lock();
      subscriptions.remove( subscriber );
      lock.unlock();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void notify( Event< ValueT > event ) {
      lock.lock();
      for ( EventSubscription< ValueT > subscription : subscriptions ) {
         subscription.notify( event );
      }
      lock.unlock();
   }//End Method

   /**
    * {@inheritDoc}
    */
   @Override public void fire( Event< ValueT > event ) {
      notify( event );
   }//End Method

}//End Class
