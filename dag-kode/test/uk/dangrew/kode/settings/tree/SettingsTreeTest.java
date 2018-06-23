package uk.dangrew.kode.settings.tree;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.settings.item.SettingsItem;
import uk.dangrew.kode.settings.item.SettingsItemType;
import uk.dangrew.kode.settings.item.SettingsRootItem;
import uk.dangrew.kode.settings.item.SimpleSettingsItem;

public class SettingsTreeTest {

   @Mock private SettingsController controller;
   @Mock private SettingsTreeItems treeItems;
   private SettingsTree systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new SettingsTree();
   }//End Method

   @Test public void shouldManageSelection() {
      assertThat( systemUnderTest.getSelectionModel().getSelectionMode(), is( SelectionMode.SINGLE ) );
      
      SettingsItem selection = mock( SettingsItem.class );
      TreeItem< SettingsItem > selectionTreeItem = new TreeItem<>( selection );
      systemUnderTest.getSelectionModel().select( selectionTreeItem );
      verify( selection ).select();
   }//End Method
   
   @Test public void shouldSelectItem() {
      SettingsItem selection = new SimpleSettingsItem( 
               controller, 
               new SettingsItemType() {}, 
               "Selection" 
      );
      systemUnderTest.build( controller, treeItems );
      systemUnderTest.getRoot().getChildren().add( selection.treeItem() );
      
      assertThat( systemUnderTest.isSelected( selection ), is( false ) );
      systemUnderTest.select( selection.type() );
      assertThat( systemUnderTest.isSelected( selection ), is( true ) );
      
      verify( controller ).displayContent( any(), any() );
   }//End Method
   
   @Test public void shouldBuildTree(){
      systemUnderTest.build( controller, treeItems );
      assertThat( systemUnderTest.getRoot(), is( notNullValue() ) );
      assertThat( systemUnderTest.getRoot().getValue(), is( instanceOf( SettingsRootItem.class ) ) );
      assertThat( systemUnderTest.isShowRoot(), is( false ) );
      verify( treeItems ).build( controller, systemUnderTest.getRoot().getValue() );
   }//End Method

}//End Class
