package uk.dangrew.kode.settings.item;

import java.util.Optional;

import javafx.scene.control.TreeItem;
import uk.dangrew.kode.javafx.tree.TreeStreamer;
import uk.dangrew.kode.settings.tree.SettingsTree;

public interface SettingsItemType {

   public default Optional< TreeItem< SettingsItem > > find( SettingsTree tree ) {
      if ( tree.getRoot().getValue().type() == this ) {
         return Optional.of( tree.getRoot() );
      }
      return new TreeStreamer()
               .flatten( tree.getRoot() )
               .filter( i -> i.getValue().type() == this )
               .findFirst();
   }//End Method
   
}//End Interface

