/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.tree;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.Node;
import javafx.scene.control.Label;
import uk.dangrew.kode.event.structure.Event;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.settings.content.SettingsTreeContent;
import uk.dangrew.kode.settings.item.SettingsItemType;
import uk.dangrew.kode.settings.tree.SettingsController;
import uk.dangrew.kode.settings.tree.SettingsTree;
import uk.dangrew.kode.settings.window.SettingsBehaviour;
import uk.dangrew.kode.settings.window.SettingsOpenEvent;
import uk.dangrew.kode.settings.window.SettingsWindowController;
import uk.dangrew.kode.settings.window.WindowPolicy;

public class SettingsControllerTest {

   @Mock private SettingsTree tree;
   @Mock private SettingsWindowController controller;
   @Mock private SettingsTreeContent content;
   
   @Mock private SettingsItemType type;
   private SettingsController systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new SettingsController( 
               tree, content, controller 
      );
   }//End Method

   @Test public void shouldTriggerOpeningOfWindowWhenEventReceived() {
      new SettingsOpenEvent().fire( new Event<>( new SettingsBehaviour( WindowPolicy.Open, null ) ) );
      verify( controller ).showSettingsWindow();
   }//End Method
   
   @Test public void shouldTriggerClosingOfWindowWhenEventReceived() {
      new SettingsOpenEvent().fire( new Event<>( new SettingsBehaviour( WindowPolicy.Close, null ) ) );
      verify( controller ).hideSettingsWindow();
   }//End Method
   
   @Test public void shouldIgnoreEventWithNoPolicy() {
      new SettingsOpenEvent().fire( new Event<>( null ) );
      verify( controller, never() ).showSettingsWindow();
      verify( controller, never() ).hideSettingsWindow();
   }//End Method
   
   @Test public void shouldTriggerOpeningOfWindowWhenEventReceivedMultipleTimes() {
      SettingsOpenEvent event = new SettingsOpenEvent();
      event.fire( new Event<>( new SettingsBehaviour( WindowPolicy.Open, null ) ) );
      event.fire( new Event<>( new SettingsBehaviour( WindowPolicy.Open, null ) ) );
      verify( controller, times( 2 ) ).showSettingsWindow();
   }//End Method
   
   @Test public void shouldTriggerOpeningOfWindowFromMultipleEventSources() {
      new SettingsOpenEvent().fire( new Event<>( new SettingsBehaviour( WindowPolicy.Open, null ) ) );
      new SettingsOpenEvent().fire( new Event<>( new SettingsBehaviour( WindowPolicy.Open, null ) ) );
      verify( controller, times( 2 ) ).showSettingsWindow();
   }//End Method

   @Test public void shouldInstructContentToShowNewNodes() {
      Node titleNode = new Label();
      Node contentNode = new Label();
      
      systemUnderTest.displayContent( titleNode, contentNode );
      verify( content ).setContent( titleNode, contentNode );
   }//End Method   
   
   @Test public void shouldSelectInTreeWhenProvided(){
      new SettingsOpenEvent().fire( new Event<>( new SettingsBehaviour( WindowPolicy.Open, type ) ) );
      verify( tree ).select( type );
   }//End Method
   
}//End Class
