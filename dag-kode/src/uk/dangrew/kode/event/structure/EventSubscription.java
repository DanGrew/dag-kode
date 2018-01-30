/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.event.structure;

/**
 * The {@link EventSubscription} interface provides the definition of an object that can
 * subscribe for events.
 * @param <ValueT> the type of value changed.
 */
public interface EventSubscription< ValueT > {
   
   /**
    * Method to notify that the given {@link Event} has occurred.
    * @param event the {@link Event} fired.
    */
   public void notify( Event< ValueT > event );
}//End Interface
