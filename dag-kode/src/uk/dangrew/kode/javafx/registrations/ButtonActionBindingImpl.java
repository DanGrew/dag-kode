/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.registrations;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonActionBindingImpl extends RegistrationImpl {
   
   private final Button button;
   private final EventHandler< ActionEvent > action;
   
   public ButtonActionBindingImpl( Button button, EventHandler< ActionEvent > action ) {
      this.action = action;
      this.button = button;
   }//End Constructor
   
   @Override protected void register() {
      button.setOnAction( action );
   }//End Method

   @Override protected void unregister() {
      button.setOnAction( null );
   }//End Method

}//End Class
