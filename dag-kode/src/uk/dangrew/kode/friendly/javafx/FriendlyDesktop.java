/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.friendly.javafx;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * The {@link FriendlyDesktop} provides a non static friendlier versions of {@link Desktop}.
 */
public class FriendlyDesktop {
   
   /**
    * Getter for the {@link Desktop}.
    * @return {@link Desktop#getDesktop()}.
    */
   public Desktop getDesktop(){
      return Desktop.getDesktop();
   }//End Method
   
   /**
    * Method to browse to the icon website.
    * @param uri the address uri to follow.
    */
   public void browseToIconWebsite( String uri ){
      try {
         getDesktop().browse( new URI( uri ) );
      } catch ( IOException e ) {
         throw new IllegalStateException( "Unable to find default web browser, or not able to launch web browser." );
      } catch ( URISyntaxException e ) {
         throw new IllegalArgumentException( "Invalid website location provided." );
      }
   }//End Method

}//End Method
