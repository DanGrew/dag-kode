package uk.dangrew.kode.utility.event;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TestKeyEvent {

   public KeyEvent build( KeyCode code ) {
      return new KeyEvent( 
               KeyEvent.KEY_PRESSED, 
               "", 
               "", 
               code, 
               false, 
               false, 
               false, 
               false 
      );
   }//End Method
}//End Class
