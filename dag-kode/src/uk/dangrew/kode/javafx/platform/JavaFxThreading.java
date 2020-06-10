package uk.dangrew.kode.javafx.platform;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

/**
 * {@link JavaFxThreading} provides convenience methods for manipulation code execution in line with the expectations
 * and availability of the JavaFx thread. This also serves to hide the api in case of change.
 */
public class JavaFxThreading {

    /**
     * Start JavaFx, with an empty {@link Runnable}.
     */
    public static void startup() {
        startup(() -> {
        });
    }

    /**
     * Start the JavaFx thread, running the given {@link Runnable}.
     * @param runnable the {@link Runnable}.
     */
    public static void startup(Runnable runnable) {
        try {
            Platform.startup(runnable);
        } catch (IllegalStateException exception) {
            //another delightful hack - only allowed to call this once but can't actually check whether it's been called...
            //just catch and carry on... PLatformImpl bombs out quickly anyway...
        }
    }

    /**
     * Run the given as soon as it can be, returning immediately.
     * @param runnable the {@link Runnable}.
     */
    public static void runLater(Runnable runnable) {
        Platform.runLater(runnable);
    }

    /**
     * Wait for the {@link Runnable} to be processed on the JavaFx thread, using an empty {@link Runnable}.
     */
    public static void runAndWait() {
        runAndWait(() -> {
        });
    }

    /**
     * TAKEN FROM JAVA as they've hidden the method.
     * <p>
     * Runs the specified {@link Runnable} on the JavaFX application thread and waits for completion.
     *
     * @param action the {@link Runnable} to run
     * @throws NullPointerException if {@code action} is {@code null}
     */
    public static void runAndWait(Runnable action) {
        if (action == null)
            throw new NullPointerException("action");

        // run synchronously on JavaFX thread
        if (Platform.isFxApplicationThread()) {
            action.run();
            return;
        }

        // queue on JavaFX thread and wait for completion
        final CountDownLatch doneLatch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                doneLatch.countDown();
            }
        });

        try {
            doneLatch.await();
        } catch (InterruptedException e) {
            // ignore exception
        }
    }

}
