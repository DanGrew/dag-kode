package uk.dangrew.kode.utility.mouse;

import javafx.scene.input.MouseEvent;

public class TestMouseEvent extends MouseEvent {

   public TestMouseEvent() {
      super( 
               null, null, null, 
               0, 0, 0, 0, 
               null, 0, 
               true, true, true, true, true, true, true, true, true, true, null 
      );
   }//End Constructor
   
}//End Class
