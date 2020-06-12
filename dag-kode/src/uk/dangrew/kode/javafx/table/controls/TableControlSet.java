package uk.dangrew.kode.javafx.table.controls;

import javafx.scene.control.Button;

public interface TableControlSet {

   public void addButtons( TableControls tableControls, double prefButtonWidth );

   public Button getButton( TableControlType type );

}//End Interface

