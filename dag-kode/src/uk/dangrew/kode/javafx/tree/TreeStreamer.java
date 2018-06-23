package uk.dangrew.kode.javafx.tree;

import java.util.stream.Stream;

import javafx.scene.control.TreeItem;

public class TreeStreamer {
   
   public < ItemTypeT > Stream< TreeItem< ItemTypeT > > flatten( TreeItem< ItemTypeT > root ) {
      return Stream.concat(
              Stream.of( root ),
              root.getChildren().stream().flatMap( this::flatten ) 
     );
   }//End Method
   
}//End Class
