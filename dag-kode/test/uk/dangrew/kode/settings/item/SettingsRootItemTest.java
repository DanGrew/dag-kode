package uk.dangrew.kode.settings.item;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.launch.TestApplication;

public class SettingsRootItemTest {

   private SettingsRootItem systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new SettingsRootItem();
   }//End Method

   @Test public void shouldProvideValues() {
      assertThat( systemUnderTest.treeItem().getValue(), is( systemUnderTest ) );
      assertThat( systemUnderTest.type(), is( nullValue() ) );
   }//End Method

}//End Class
