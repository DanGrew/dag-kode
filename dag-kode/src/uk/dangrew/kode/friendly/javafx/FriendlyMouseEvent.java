package uk.dangrew.kode.friendly.javafx;

import javafx.scene.input.MouseEvent;

public class FriendlyMouseEvent {

    private final MouseEvent mouseEvent;

    public FriendlyMouseEvent(MouseEvent event){
        this.mouseEvent = event;
    }

    public double friendly_getX(){
        return mouseEvent.getX();
    }

    public double friendly_getY(){
        return mouseEvent.getY();
    }

    public double friendly_getSceneX(){
        return mouseEvent.getSceneX();
    }

    public double friendly_getSceneY(){
        return mouseEvent.getSceneY();
    }

}
