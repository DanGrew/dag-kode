/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.observable;

import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.collections.ListChangeListener;

/**
 * The {@link FunctionListChangeListenerImpl} provides a {@link ListChangeListener} that accepts
 * two {@link Consumer}s to delete actions to when the {@link List} is changed.
 * @param <TypeT> the type listened for.
 */
public class FunctionListChangeListenerImpl< TypeT > implements ListChangeListener< TypeT > {
   
   private final Consumer< TypeT > addFunction;
   private final Consumer< TypeT > removeFunction;
   private final Runnable permutationFunction;

   /**
    * Constructs a new {@link FunctionListChangeListenerImpl}.
    * @param addFunction the {@link Consumer} to invoke when something is added.
    * @param removeFunction the {@link Consumer} to invoke when something is removed.
    */
   public FunctionListChangeListenerImpl( Consumer< TypeT > addFunction, Consumer< TypeT > removeFunction ) {
      this( addFunction, removeFunction, null );
   }//End Constructor
   
   public FunctionListChangeListenerImpl( 
            Consumer< TypeT > addFunction, 
            Consumer< TypeT > removeFunction,
            Runnable permuationFunction
   ) {
      this.addFunction = addFunction;
      this.removeFunction = removeFunction;
      this.permutationFunction = permuationFunction;
   }//End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void onChanged( Change< ? extends TypeT > change ) {
      while ( change.next() ) {
         if ( change.wasAdded() && addFunction != null ) {
            new ArrayList<>( change.getAddedSubList() ).forEach( addFunction::accept );
         }
         if ( change.wasRemoved() && removeFunction != null ) {
            change.getRemoved().forEach( removeFunction::accept );
         }
         if ( change.wasPermutated() && permutationFunction != null )  {
            permutationFunction.run();
         }
      }
   }//End Method

}//End Class
