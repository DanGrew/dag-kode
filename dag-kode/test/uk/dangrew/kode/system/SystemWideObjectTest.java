/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.system;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.kode.system.SystemWideObject;

public class SystemWideObjectTest {

   private Object object;
   private SystemWideObject< Object > systemUnderTest;

   @Before public void initialiseSystemUnderTestRedirect() {
      object = new Object();
      systemUnderTest = new SystemWideObject<>( object );
   }//End Method
   
   @Test public void shouldProvideObject(){
      assertThat( systemUnderTest.get(), is( object ) );
   }//End Method

}//End Class
