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

import javafx.collections.SetChangeListener;

/**
 * The {@link FunctionSetChangeListenerImpl} provides a {@link SetChangeListener} that accepts
 * two {@link Consumer}s to delete actions to when the {@link java.util.Set} is changed.
 * @param <TypeT> the type listened for.
 */
public class FunctionSetChangeListenerImpl< TypeT > implements SetChangeListener< TypeT > {
   
   private final Consumer< TypeT > addFunction;
   private final Consumer< TypeT > removeFunction;

   /**
    * Constructs a new {@link FunctionSetChangeListenerImpl}.
    * @param addFunction the {@link Consumer} to invoke when something is added.
    * @param removeFunction the {@link Consumer} to invoke when something is removed.
    */
   public FunctionSetChangeListenerImpl( Consumer< TypeT > addFunction, Consumer< TypeT > removeFunction ) {
      this.addFunction = addFunction;
      this.removeFunction = removeFunction;
   }//End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void onChanged( Change< ? extends TypeT > change ) {
      TypeT added = change.getElementAdded();
      if ( added != null ) {
         addFunction.accept( added );
      }
      
      TypeT removed = change.getElementRemoved();
      if ( removed != null ) {
         removeFunction.accept( removed );
      }
   }//End Method

}//End Class
