/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.content;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.Node;
import javafx.scene.control.Label;
import uk.dangrew.kode.launch.TestApplication;

public class SettingsTreeContentTest {

   private SettingsTreeContent systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      TestApplication.startPlatform();
      systemUnderTest = new SettingsTreeContent();
   }//End Method
   
   @Test public void shouldContainTopAnCenterSubPanels() {
      assertThat( systemUnderTest.getTop(), is( systemUnderTest.top() ) );
      assertThat( systemUnderTest.getCenter(), is( systemUnderTest.center() ) );
   }//End Method
   
   @Test public void shouldUseInnerInsetsForSubPanels(){
      assertThat( systemUnderTest.top().getInsets().getTop(), is( SettingsTreeContent.INNER_INSETS ) );
      assertThat( systemUnderTest.top().getInsets().getBottom(), is( SettingsTreeContent.INNER_INSETS ) );
      assertThat( systemUnderTest.top().getInsets().getLeft(), is( SettingsTreeContent.INNER_INSETS ) );
      assertThat( systemUnderTest.top().getInsets().getRight(), is( SettingsTreeContent.INNER_INSETS ) );
      
      assertThat( systemUnderTest.center().getInsets().getTop(), is( SettingsTreeContent.INNER_INSETS ) );
      assertThat( systemUnderTest.center().getInsets().getBottom(), is( SettingsTreeContent.INNER_INSETS ) );
      assertThat( systemUnderTest.center().getInsets().getLeft(), is( SettingsTreeContent.INNER_INSETS ) );
      assertThat( systemUnderTest.center().getInsets().getRight(), is( SettingsTreeContent.INNER_INSETS ) );
   }//End Method
   
   @Test public void shouldUseOuterInsetsForContent(){
      assertThat( systemUnderTest.getInsets().getTop(), is( SettingsTreeContent.OUTER_OFFSETS ) );
      assertThat( systemUnderTest.getInsets().getBottom(), is( SettingsTreeContent.OUTER_OFFSETS ) );
      assertThat( systemUnderTest.getInsets().getLeft(), is( SettingsTreeContent.OUTER_OFFSETS ) );
      assertThat( systemUnderTest.getInsets().getRight(), is( SettingsTreeContent.OUTER_OFFSETS ) );
   }//End Method
   
   @Test public void shouldReplaceContentWhenInstructed(){
      Node title = new Label();
      Node content = new Label();
      
      systemUnderTest.setContent( title, content );
      assertThat( systemUnderTest.top().getCenter(), is( title ) );
      assertThat( systemUnderTest.center().getCenter(), is( content ) );
   }//End Method

}//End Class
