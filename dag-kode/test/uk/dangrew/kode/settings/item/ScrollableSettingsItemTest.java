/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.item;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import javafx.scene.layout.Pane;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.settings.tree.SettingsController;

public class ScrollableSettingsItemTest {

   private static final String NAME = "any name";
   
   @Mock private SettingsItemType type;
   private Node contentTitle;
   @Mock private SettingsController controller;
   private Node content;
   
   private ScrollableSettingsItem systemUnderTest;
   
   /** Testable concrete extension.**/
   private static class TestableScrollableItem extends ScrollableSettingsItem {

      protected TestableScrollableItem( SettingsItemType type, String itemName, Node contentTitle, SettingsController controller, Node content ) {
         super( type, itemName, contentTitle, controller, content );
      }//End Constructor
      
   }//End Class

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      contentTitle = new Pane();
      content = new Pane();
      systemUnderTest = new TestableScrollableItem( type, NAME, contentTitle, controller, content );
   }//End Method

   @Test public void shouldProvideToStringUsingName(){
      assertThat( systemUnderTest.toString(), is( NAME ) );
   }//End Method
   
   @Test public void shouldWrapInScroller(){
      ArgumentCaptor< Node > contentCaptor = ArgumentCaptor.forClass( Node.class );
      systemUnderTest.select();
      verify( controller ).displayContent( Mockito.eq( contentTitle ), contentCaptor.capture() );
      
      assertThat( contentCaptor.getValue(), is( instanceOf( ScrollPane.class ) ) );
      ScrollPane scroller = ( ScrollPane ) contentCaptor.getValue();
      assertThat( scroller.getContent(), is( content ) );
      assertThat( scroller.getHbarPolicy(), is( ScrollBarPolicy.NEVER ) );
      assertThat( scroller.isFitToWidth(), is( true ) );
   }//End Method

}//End Class
