package uk.dangrew.kode.settings.item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.control.TreeItem;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.settings.tree.SettingsTree;

public class SettingsItemTypeTest {

   private class TestSettingsItemType implements SettingsItemType {}
   
   private SettingsTree tree;
   @Mock private SettingsItem item;
   private TestSettingsItemType systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      tree = new SettingsTree();
      systemUnderTest = new TestSettingsItemType();
      when( item.type() ).thenReturn( systemUnderTest );
   }//End Method

   @Test public void shouldIdentifyRoot() {
      TreeItem< SettingsItem > root = new TreeItem<>( item );
      tree.setRoot( root );
      assertThat( systemUnderTest.find( tree ).get(), is( root ) );
   }//End Method
   
   @Test public void shouldIdentifyItem() {
      TreeItem< SettingsItem > root = new TreeItem<>( mock( SettingsItem.class ) );
      tree.setRoot( root );
      
      TreeItem< SettingsItem > treeItem = new TreeItem<>( item );
      root.getChildren().add( treeItem );
      assertThat( systemUnderTest.find( tree ).get(), is( treeItem ) );
   }//End Method
   
   @Test public void shouldNotIdentify() {
      TreeItem< SettingsItem > root = new TreeItem<>( mock( SettingsItem.class ) );
      tree.setRoot( root );
      
      TreeItem< SettingsItem > treeItem = new TreeItem<>( mock( SettingsItem.class ) );
      root.getChildren().add( treeItem );
      assertThat( systemUnderTest.find( tree ).isPresent(), is( false ) );
   }//End Method

}//End Class
