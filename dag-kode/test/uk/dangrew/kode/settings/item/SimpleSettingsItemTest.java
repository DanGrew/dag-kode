package uk.dangrew.kode.settings.item;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.Node;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.settings.tree.SettingsController;

public class SimpleSettingsItemTest {

   @Mock private SettingsController controller;
   @Mock private SettingsItemType type;
   private String itemName;
   @Mock private Node contentTitle;
   @Mock private Node content;
   private SimpleSettingsItem systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      itemName = "Name";
      systemUnderTest = new SimpleSettingsItem( 
               controller, 
               type, 
               itemName,
               contentTitle,
               content
      );
   }//End Method

   @Test public void shouldProvideValues() {
      assertThat( systemUnderTest.type(), is( type ) );
      assertThat( systemUnderTest.treeItem().getValue(), is( systemUnderTest ) );
      assertThat( systemUnderTest.toString(), is( itemName ) );
   }//End Method
   
   @Test public void shouldSelectAndDisplayContent(){
      systemUnderTest.select();
      verify( controller ).displayContent( contentTitle, content );
   }//End Method

}//End Class
