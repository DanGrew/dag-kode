/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.observable;

import java.util.function.Consumer;

import javafx.collections.MapChangeListener;

/**
 * The {@link FunctionMapAnyKeyChangeListenerImpl} provides a {@link MapChangeListener} that accepts
 * a {@link Consumer}s to take action when the {@link Map} is changed.
 * @param <KeyTypeT> the key type of the map.
 * @param <ValueTypeT> the value type of the map.
 */
public class FunctionMapAnyKeyChangeListenerImpl< KeyTypeT, ValueTypeT > implements MapChangeListener< KeyTypeT, ValueTypeT > {
   
   private final Consumer< KeyTypeT > acceptFunction;

   /**
    * Constructs a new {@link FunctionMapAnyKeyChangeListenerImpl}.
    * @param acceptFunction the {@link Consumer} to be called with key whenever the map changes.
    */
   public FunctionMapAnyKeyChangeListenerImpl( 
            Consumer< KeyTypeT > acceptFunction 
   ) {
      this.acceptFunction = acceptFunction;
   }//End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void onChanged( MapChangeListener.Change< ? extends KeyTypeT, ? extends ValueTypeT > change ) {
      acceptFunction.accept( change.getKey() );
   }//End Method

}//End Class
