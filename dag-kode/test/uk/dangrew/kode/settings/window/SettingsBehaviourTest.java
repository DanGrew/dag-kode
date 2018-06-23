/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.window;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.settings.item.SettingsItemType;

public class SettingsBehaviourTest {

   @Mock private SettingsItemType type;
   private SettingsBehaviour systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new SettingsBehaviour( WindowPolicy.Open, type );
   }//End Method
   
   @Test public void shouldProvideWindowPolicy() {
      assertThat( systemUnderTest.getWindowPolicy(), is( WindowPolicy.Open ) );
   }//End Method
   
   @Test public void shouldProvideSelection() {
      assertThat( systemUnderTest.getSelection(), is( type ) );
   }//End Method
   
   @Test( expected = IllegalArgumentException.class ) public void shouldNotAcceptNullPolicy(){
      new SettingsBehaviour( null, type );
   }//End Method
   
   @Test public void shouldOverrideEquals(){
      assertThat( 
               new SettingsBehaviour( WindowPolicy.Close, type ), 
               is( new SettingsBehaviour( WindowPolicy.Close, type ) )
      );
   }//End Method
   
   @Test public void shouldOverrideHashcode(){
      assertThat( 
               new SettingsBehaviour( WindowPolicy.Close, type ).hashCode(), 
               is( new SettingsBehaviour( WindowPolicy.Close, type ).hashCode() )
      );
   }//End Method

}//End Class
