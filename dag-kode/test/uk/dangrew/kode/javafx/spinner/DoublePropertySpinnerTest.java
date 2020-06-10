/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.spinner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.launch.TestApplication;

/**
 * {@link DoublePropertySpinner} test.
 */
public class DoublePropertySpinnerTest {

   private DoublePropertySpinner systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      TestApplication.startPlatform();
      JavaFxThreading.runAndWait( () -> {
         systemUnderTest = new DoublePropertySpinner();
         systemUnderTest.setValueFactory( new DoubleSpinnerValueFactory( 0, 100 ) );
      } );
   }//End Method
   
   @Test public void shouldBindWithSimpleFunction() {
      DoubleProperty property = new SimpleDoubleProperty( 0 );
      systemUnderTest.bindProperty( property );
      
      systemUnderTest.getValueFactory().setValue( 10.5 );
      assertThat( property.get(), is( 10.5 ) );
      
      property.set( 98.1 );
      assertThat( systemUnderTest.getValue(), is( 98.1 ) );
   }//End Method

}//End Class
