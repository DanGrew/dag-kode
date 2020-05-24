package uk.dangrew.kode.friendly.javafx;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

/**
 * Friengly wrapper for the {@link Image} calss.
 */
public class FriendlyImage {

    private final Image image;

    public FriendlyImage(Image image){
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public double friendly_getWidth(){
        return image.getWidth();
    }

    public double friendly_getHeight(){
        return image.getHeight();
    }

    public PixelReader friendly_getPixelReader(){
        return image.getPixelReader();
    }
}
