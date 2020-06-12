/*
 * ----------------------------------------
 *      Nutrient Usage Tracking System
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.table.tools;

import java.util.function.BiConsumer;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;

/**
 * The {@link EditCommitHandler} provides an intermediate {@link EventHandler} for redirecting events to a mapper function.
 */
public class TableViewEditCommitHandler< RowTypeT, TypeT > implements EventHandler< CellEditEvent< RowTypeT, TypeT > > {
   
   private final BiConsumer< RowTypeT, TypeT > valueSetter;

   /**
    * Constructs a new {@link EditCommitHandler}.
    * @param valueSetter the {@link BiConsumer} mapper.
    */
   public TableViewEditCommitHandler( BiConsumer< RowTypeT, TypeT > valueSetter ) {
      this.valueSetter = valueSetter;
   }// End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void handle( CellEditEvent< RowTypeT, TypeT > t ) {
      valueSetter.accept( t.getRowValue(), t.getNewValue() );
   }// End Method

}//End Class
