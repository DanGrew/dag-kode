package uk.dangrew.kode.friendly.javafx;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;

public class FriendlyTooltip {

   public void friendly_install( Node node, Tooltip tooltip ) {
      Tooltip.install( node, tooltip );
   }//End Method
   
}//End Class
