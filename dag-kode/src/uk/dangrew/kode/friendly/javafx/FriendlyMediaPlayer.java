/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.friendly.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Friendly wrapper for the final {@link MediaPlayer}.
 */
public class FriendlyMediaPlayer {
   
   private final MediaPlayer mediaPlayer;

   /**
    * Constructs a new {@link FriendlyMediaPlayer}.
    * @param media the {@link Media}.
    */
   public FriendlyMediaPlayer( Media media ) {
      this.mediaPlayer = new MediaPlayer( media );
   }//End Constructor

   public MediaPlayer getMediaPlayer() {
      return mediaPlayer;
   }

   /**
    * {@link MediaPlayer#play()}
    */
   public void friendly_play(){
      mediaPlayer.play();
   }//End Method

   public void friendly_pause(){
      mediaPlayer.pause();
   }

   public void friendly_stop(){
      mediaPlayer.stop();
   }

   public void friendly_seek(Duration duration){
      mediaPlayer.seek(duration);
   }

   public void friendly_setRate(double value){
      mediaPlayer.setRate(value);
   }

   public ReadOnlyObjectProperty<Duration> friendly_currentTimeProperty(){
      return mediaPlayer.currentTimeProperty();
   }

   public ReadOnlyObjectProperty<Duration> friendly_durationProperty() {
      return mediaPlayer.totalDurationProperty();
   }

   public DoubleProperty friendly_rateProperty(){
      return mediaPlayer.rateProperty();
   }

   /**
    * {@link MediaPlayer#setOnEndOfMedia(Runnable)}
    */
   public void friendly_setOnEndOfMedia( Runnable runnable ) {
      mediaPlayer.setOnEndOfMedia( runnable );
   }//End Method
}//End Class
