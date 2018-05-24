package uk.dangrew.kode.javafx.hacks;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import uk.dangrew.kode.launch.TestApplication;

public class JavaFxHacksTest {

   @Mock private TableView< Object > view;
   private Set< Node > returned;
   private JavaFxHacks systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );

      returned = new LinkedHashSet<>();
      systemUnderTest = new JavaFxHacks();
   }//End Method

   @Test public void shouldLookupTableRow() {
      when( view.lookupAll( JavaFxHacks.LOOKUP_TABLE_ROW_CELL ) ).thenReturn( returned );
      assertThat( systemUnderTest.lookupTableRow( view, 0 ).isPresent(), is( false ) );
      
      returned.add( new BorderPane() );
      assertThat( systemUnderTest.lookupTableRow( view, 0 ).get(), is( notNullValue() ) );
      assertThat( systemUnderTest.lookupTableRow( view, 1 ).isPresent(), is( false ) );
   }//End Method

}//End Class
