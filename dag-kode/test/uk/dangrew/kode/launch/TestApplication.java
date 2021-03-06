/*
 * ----------------------------------------
 *             System Digest
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
 package uk.dangrew.kode.launch;

import static org.junit.Assert.fail;

import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;

/**
 * The {@link TestApplication} provides a JavaFx {@link Application} that can be used to launch
 * graphics in a test environment.
 */
public class TestApplication extends Application {

   private Parent center;

   /**
    * Constructs a new {@link TestApplication} with a {@link Parent} to display.
    * @param center the {@link Parent}.
    */
   public TestApplication( Parent center ) {
      this.center = center;
   }//End Constructor

   /**
    * {@inheritDoc}
    */
   @Override public void start( Stage primaryStage ) throws Exception {
      Scene scene = new Scene( center, 100, 100 );
      primaryStage.setOnCloseRequest( e -> System.exit( 0 ) );
      primaryStage.setScene( scene );
      primaryStage.show();
   }//End Method

   /**
    * Method to launch a {@link TestApplication} in a simple way, using a {@link Supplier} so that
    * the graphical {@link Parent} is constructed on the JavaFx {@link Thread}.
    * @param supplier the {@link Supplier} of the {@link Parent}.
    */
   public static void launch( Supplier< Parent > supplier ) throws InterruptedException {
      launch( supplier, true );
   }//End Method

   /**
    * Method to launch a {@link TestApplication} in a simple way, using a {@link Supplier} so that
    * the graphical {@link Parent} is constructed on the JavaFx {@link Thread}.
    * @param supplier the {@link Supplier} of the {@link Parent}.
    * @param max whether to launch maximized.
    */
   public static void launch( Supplier< Parent > supplier, boolean max ) throws InterruptedException {
      JavaFxThreading.startup();
      CountDownLatch latch = new CountDownLatch( 1 );
      JavaFxThreading.runAndWait( () -> {
         try {
            TestApplication application = new TestApplication( supplier.get() );
            Stage primaryStage = new Stage();
            application.start( primaryStage );
            if ( max ) {
               primaryStage.setMaximized( true );
            }
            latch.countDown();
         } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
         }
      } );
      latch.await();
   }//End Method

   /**
    * Method to start the {@link JavaFxThreading} if not already started.
    * @deprecated use {@link JavaFxThreading#startup()}.
    */
   @Deprecated
   public static void startPlatform(){
      JavaFxThreading.startup();
   }//End Method

}//End Class
