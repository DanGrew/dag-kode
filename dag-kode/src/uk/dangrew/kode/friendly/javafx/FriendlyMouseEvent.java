package uk.dangrew.kode.friendly.javafx;

import javafx.scene.input.MouseEvent;

public class FriendlyMouseEvent {

    private final MouseEvent mouseEvent;

    public FriendlyMouseEvent(MouseEvent event){
        this.mouseEvent = event;
    }

    public double getX(){
        return mouseEvent.getX();
    }

    public double getY(){
        return mouseEvent.getY();
    }

}
