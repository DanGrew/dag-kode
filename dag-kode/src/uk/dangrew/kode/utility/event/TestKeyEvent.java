package uk.dangrew.kode.utility.event;

import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uk.dangrew.kode.javafx.keyboard.KeyEventCreator;

/**
 * @deprecated use {@link KeyEventCreator}.
 */
@Deprecated
public class TestKeyEvent {

   public KeyEvent build(KeyCode code ) {
      return build(KeyEvent.KEY_PRESSED, code);
   }//End Method

   public KeyEvent build( EventType<KeyEvent> type, KeyCode code ) {
      return new KeyEvent(
            type,
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
