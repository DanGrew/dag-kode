/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.utility.conversion;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javafx.scene.paint.Color;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

/**
 * {@link ColorConverter} test.
 */
@RunWith( JUnitParamsRunner.class )
public class ColorConverterTest {

   private ColorConverter systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      systemUnderTest = new ColorConverter();
   }//End Method
   
   /**
    * Method to provide the {@link Color} parameters to {@link #shouldConvertStandardColorToHexAndBack(Color)}.
    * @return the parameters.
    */
   public static final Object[] provideColours(){
      return new Object[]{
               new Object[]{ Color.RED },
               new Object[]{ Color.ANTIQUEWHITE },
               new Object[]{ Color.BLACK },
               new Object[]{ Color.BLUE },
               new Object[]{ Color.WHITE },
               new Object[]{ Color.BEIGE },
               new Object[]{ Color.DARKTURQUOISE }
      };
   }//End Method
   
   @Parameters( method = "provideColours" )
   @Test public void shouldConvertStandardColorToHexAndBack( Color colour ) {
      String hex = systemUnderTest.colorToHex( colour );
      Color converted = systemUnderTest.hexToColor( hex );
      
      assertThat( converted, is( colour ) );
   }//End Method
   
   @Test public void shouldIgnoreNullColorToHex(){
      assertThat( systemUnderTest.colorToHex( null ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldIgnoreNullHexToColor(){
      assertThat( systemUnderTest.hexToColor( null ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldHandleInappropriateHexToColor(){
      assertThat( systemUnderTest.hexToColor( "anything" ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldParseStringName(){
      assertThat( systemUnderTest.nameToColor( "ANTIQUEWHITE" ), is( Color.ANTIQUEWHITE ) );
      assertThat( systemUnderTest.nameToColor( "RED" ), is( Color.RED ) );
      assertThat( systemUnderTest.nameToColor( "Red" ), is( Color.RED ) );
      assertThat( systemUnderTest.nameToColor( "red" ), is( Color.RED ) );
      assertThat( systemUnderTest.nameToColor( "   red   " ), is( Color.RED ) );
   }//End Method
   
}//End Class
