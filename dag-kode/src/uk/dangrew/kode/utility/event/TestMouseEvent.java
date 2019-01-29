package uk.dangrew.kode.utility.event;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class TestMouseEvent extends MouseEvent {

   private static final long serialVersionUID = 1L;

   public TestMouseEvent() {
      super( 
               null, null, null, 
               0, 0, 0, 0, 
               null, 0, 
               true, true, true, true, true, true, true, true, true, true, null 
      );
   }//End Constructor
   
   public TestMouseEvent( MouseButton button ) {
      super( 
               null, null, null, 
               0, 0, 0, 0, 
               button, 0, 
               true, true, true, true, true, true, true, true, true, true, null 
      );
   }//End Constructor
   
}//End Class
