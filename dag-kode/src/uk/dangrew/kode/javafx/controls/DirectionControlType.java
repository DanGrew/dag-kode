/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.controls;

import javafx.scene.input.KeyCode;

import java.util.Optional;

/**
 * Simple enum providing the directions for the {@link DirectionControls}.
 */
public enum DirectionControlType {
   Up,
   Down,
   Left, 
   Right;
   
   public static Optional<DirectionControlType> of(KeyCode code){
      switch(code){
         case DOWN:
            return Optional.ofNullable(Down);
         case LEFT:
            return Optional.ofNullable(Left);
         case RIGHT:
            return Optional.ofNullable(Right);
         case UP:
            return Optional.ofNullable(Up);
         default:
            return Optional.empty();
      }
   }
}//End Enum
