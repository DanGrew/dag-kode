/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.Assert;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Common test values and properties used.
 */
public final class TestCommon {
   
   private static final double PRECISION = 0.001;
   
   private TestCommon(){}
   
   /**
    * Gets the precision to use for double comparisons.
    * @return the error to compare to.
    */
   public static final double precision(){
      return PRECISION;
   }//End Method
   
   /**
    * Method to assert that all values of the enum map using {@link Enum#valueOf(Class, String)} to {@link Enum#name()}.
    * @param enumClass the {@link Enum} {@link Class} to prove.
    * @param <E> the enum type. 
    */
   public static < E extends Enum< E > > void assertEnumNameWithValueOf( Class< E > enumClass ) {
      for ( Enum< E > value : enumClass.getEnumConstants() ) {
         Assert.assertEquals( value, Enum.valueOf( enumClass, value.name() ) );
      }
   }//End Method
   
   /**
    * Method to assert that all values of the enum map using {@link Enum#valueOf(Class, String)} to {@link Enum#toString()}.
    * @param enumClass the {@link Enum} {@link Class} to prove.
    * @param <E> the enum type.
    */
   public static < E extends Enum< E > > void assertEnumToStringWithValueOf( Class< E > enumClass ) {
      for ( Enum< E > value : enumClass.getEnumConstants() ) {
         Assert.assertEquals( value, Enum.valueOf( enumClass, value.toString() ) );
      }
   }//End Method
   
   /**
    * Method to assert that the given {@link Font} is bold.
    * @param font the {@link Font} in question.
    */
   public static void assertThatFontIsBold( Font font ) {
      assertThat( FontWeight.findByName( font.getStyle() ), is( FontWeight.BOLD ) );
   }//End Method
   
   public static void assertThatFontHasWeight( Font font, FontWeight weight ) {
      assertThat( FontWeight.findByName( font.getStyle() ), is( weight ) );
   }//End Method
   
   public static void assertThatFontHasPosture( Font font, FontPosture posture ) {
      assertThat( FontPosture.findByName( font.getStyle() ), is( posture ) );
   }//End Method
   
   public static void assertThatRegionHasBackground( Region node, Paint colour ){
      assertThat( node.getBackground().getFills().get( 0 ).getFill(), is( colour ) );
   }//End Method
   
   public static void assertThatRegionHasBorder( Region node, Paint colour, Double thickness ){
      if ( colour != null ) {
         assertThat( node.getBorder().getStrokes().get( 0 ).getBottomStroke(), is( colour ) );
         assertThat( node.getBorder().getStrokes().get( 0 ).getTopStroke(), is( colour ) );
         assertThat( node.getBorder().getStrokes().get( 0 ).getRightStroke(), is( colour ) );
         assertThat( node.getBorder().getStrokes().get( 0 ).getLeftStroke(), is( colour ) );
      }
      if ( thickness != null ) {
         assertThat( node.getBorder().getStrokes().get( 0 ).getWidths().getBottom(), is( thickness ) );
         assertThat( node.getBorder().getStrokes().get( 0 ).getWidths().getTop(), is( thickness ) );
         assertThat( node.getBorder().getStrokes().get( 0 ).getWidths().getRight(), is( thickness ) );
         assertThat( node.getBorder().getStrokes().get( 0 ).getWidths().getLeft(), is( thickness ) );
      }
   }//End Method
   
   public static void assertThatRegionHasBackground( Region node, Background background ){
      if ( background == null ) {
         assertThat( node.getBackground(), is( nullValue() ) );
      } else {
         assertThatRegionHasBackground( node, background.getFills().get( 0 ).getFill() );
      }
   }//End Method
   
   public static void assertThatRegionHasBorder( Region node, Border border ){
      if ( border == null ) {
         assertThat( node.getBorder(), is( nullValue() ) );
      } else {
         assertThatRegionHasBorder( 
                  node, 
                  border.getStrokes().get( 0 ).getBottomStroke(),
                  border.getStrokes().get( 0 ).getWidths().getBottom()
         );
      }
   }//End Method
   
   public static void assertThatRegionHasPadding( Region node, double padding ){
      assertThat( node.getPadding().getBottom(), is( padding ) );
      assertThat( node.getPadding().getTop(), is( padding ) );
      assertThat( node.getPadding().getRight(), is( padding ) );
      assertThat( node.getPadding().getLeft(), is( padding ) );
   }//End Method
   
   /**
    * Method to assert that two instructions do not concurrently interfere with each other.
    * @param threadOneInstruction the {@link Consumer} to accept the loop count and execute the first instruction.
    * @param threadTwoInstruction the {@link Consumer} to accept the loop count and execute the second instruction.
    * @param loopCounts the number of loops of each instruction to run.
    */
   public static final void assertConcurrencyIsNotAnIssue( 
            Consumer< Integer > threadOneInstruction, 
            Consumer< Integer > threadTwoInstruction,
            final int loopCounts
   ){
      CountDownLatch latch = new CountDownLatch( 2 );
      
      IntegerProperty firstCount = new SimpleIntegerProperty( 0 );
      IntegerProperty secondCount = new SimpleIntegerProperty( 0 );
      
      new Thread( () -> {
         for ( int i = 0; i < loopCounts; i++ ) {
            threadOneInstruction.accept( i );
            firstCount.set( firstCount.get() + 1 );
         }
         latch.countDown();
      } ).start();
      
      new Thread( () -> {
         for ( int i = 0; i < loopCounts; i++ ) {
            threadTwoInstruction.accept( i );
            secondCount.set( secondCount.get() + 1 );
         }
         latch.countDown();
      } ).start();
      
      try {
         latch.await( 10000, TimeUnit.MILLISECONDS );
      } catch ( InterruptedException e ) {
         fail( "Waiting on latch has failed." );
      }
      assertThat( firstCount.get(), is( loopCounts ) );
      assertThat( secondCount.get(), is( loopCounts ) );
   }//End Method
   
   public static void assertThatInputIsInRangeOf( LocalDateTime subject, LocalDateTime expected, boolean insideRange ) {
      assertThat( subject.isAfter( expected.minusMinutes( 5 ) ) && subject.isBefore( expected.plusMinutes( 5 ) ), is( insideRange ) );
   }//End Method
   
}//End Class
