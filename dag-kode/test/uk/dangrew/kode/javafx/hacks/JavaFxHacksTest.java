package uk.dangrew.kode.javafx.hacks;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javafx.scene.Node;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import uk.dangrew.kode.launch.TestApplication;

public class JavaFxHacksTest {

   @Spy private TableView< String > view;
   private Set< Node > returned;
   private JavaFxHacks systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );

      returned = new LinkedHashSet<>();
      systemUnderTest = new JavaFxHacks();
      when( view.lookupAll( JavaFxHacks.LOOKUP_TABLE_ROW_CELL ) ).thenReturn( returned );
   }//End Method

   @Test public void shouldLookupTableRow() {
      assertThat( systemUnderTest.lookupTableRows( view ), is( empty() ) );
      
      TableRow< String > row1 = new TableRow<>();
      row1.setItem( new Object().toString() );
      
      TableRow< String > row2 = new TableRow<>();

      TableRow< String > row3 = new TableRow<>();
      row3.setItem( new Object().toString() );
      
      TableRow< String > row4 = new TableRow<>();
      row4.setItem( new Object().toString() );
      
      returned.add( row1 );
      returned.add( row2 );
      returned.add( row3 );
      returned.add( row4 );
      view.getItems().add( row3.getItem() );
      view.getItems().add( row1.getItem() );
      view.getItems().add( row4.getItem() );
      
      assertThat( systemUnderTest.lookupTableRows( view ), contains( row3, row1, row4 ) );
   }//End Method
   
   @Test public void shouldIgnoreIncompatible() {
      assertThat( systemUnderTest.lookupTableRows( view ), is( empty() ) );
      
      TableRow< Object > row1 = new TableRow<>();
      row1.setItem( new Object() );
      
      returned.add( row1 );
      assertThat( systemUnderTest.lookupTableRows( view ), is( empty() ) );
      
      returned.clear();
      returned.add( new BorderPane() );
      assertThat( systemUnderTest.lookupTableRows( view ), is( empty() ) );
   }//End Method

}//End Class
