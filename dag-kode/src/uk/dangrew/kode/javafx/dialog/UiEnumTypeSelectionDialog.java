package uk.dangrew.kode.javafx.dialog;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;

/**
 * {@link ChoiceDialog} for selecting a particular enum type.
 * @param <EnumTypeT> the enum type.
 */
public class UiEnumTypeSelectionDialog< EnumTypeT extends Enum< ? > > extends ChoiceDialog< EnumTypeT > {

   public UiEnumTypeSelectionDialog( Class< EnumTypeT > enumClass, EnumTypeT defaultSelection ) {
      this( FXCollections.observableArrayList( enumClass.getEnumConstants() ), defaultSelection );
   }//End Constructor
   
   public UiEnumTypeSelectionDialog( ObservableList< EnumTypeT > options, EnumTypeT defaultSelection ) {
      super( defaultSelection, options );
      setTitle( "Selection" );
      setContentText( "Choose your Type:" );
   }//End Constructor
   
   public Optional< EnumTypeT > friendly_showAndWait(){
      return showAndWait();
   }//End Method
   
   public void friendly_setHeaderText( String headerText ) {
      setHeaderText( headerText );
   }//End Method
   
}//End Class
