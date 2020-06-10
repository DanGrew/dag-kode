/*
 * ----------------------------------------
 *           Json Upgrading and 
 *        Persistence Architecture
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.platform;

import javafx.application.Platform;

/**
 * The {@link PlatformLifecycleImpl} provides the practically untestable JVM shutdown
 * the application needs to kill the process.
 */
public class PlatformLifecycleImpl {

   /**
    * Method to shutdown the JavaFx thread and system.
    */
   public void shutdownPlatform(){
      Platform.exit();
      System.exit( 0 );
   }//End Method
   
}//End Class
