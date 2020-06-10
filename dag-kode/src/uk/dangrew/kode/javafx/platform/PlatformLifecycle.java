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

/**
 * {@link PlatformLifecycle} provides a static interface for controlling the JavaFx thread
 * lifecycle, namely shutting down.
 */
public class PlatformLifecycle {

   private static PlatformLifecycleImpl INSTANCE;
   
   static { reset(); }
   
   /**
    * Setter for the current instance of the {@link PlatformLifecycleImpl}.
    * @param lifecycle the {@link PlatformLifecycleImpl} to use.
    */
   public static void setInstance( PlatformLifecycleImpl lifecycle ) {
      INSTANCE = lifecycle;
   }//End Method
   
   /**
    * Method to reset the {@link PlatformLifecycleImpl} to the default implementation.
    */
   static void reset(){
      INSTANCE = new PlatformLifecycleImpl();
   }//End Method

   /**
    * Method to shutdown the current system. Be careful and sure that this is what you want to do.
    */
   public static void shutdown() {
      INSTANCE.shutdownPlatform();
   }//End Method

}//End Class
