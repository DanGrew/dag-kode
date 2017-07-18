/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.registrations;

import org.junit.Before;

import uk.dangrew.kode.javafx.registrations.PaintColorChangeListenerBindingImpl;

/**
 * {@link PaintColorChangeListenerBindingImpl} test.
 */
public class PaintColorChangeListenerBindingImplTest extends ChangeListenerMismatchBindingImplTest {

   @Before @Override public void initialiseSystemUnderTest(){
      super.initialiseSystemUnderTest();
      systemUnderTest = new PaintColorChangeListenerBindingImpl( colourProperty, paintProperty );
   }//End Method

}//End Class
