package uk.dangrew.kode.javafx.table.controls;

import javafx.scene.control.Label;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.controller.ConceptTableViewController;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.testutilities.TestColumnsPopulator;
import uk.dangrew.kode.testutilities.TestConcept;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ConceptTableWithControlsTest {

   @Spy private JavaFxStyle styling;
   @Mock private TableControls controls;
   private ConceptTable<TestConcept> table;
   private ConceptTableWithControls< TestConcept > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );

      JavaFxThreading.runAndWait( () -> table = new ConceptTable<TestConcept>(
              new TestColumnsPopulator(),
              mock(ConceptTableViewController.class)
      ));
      systemUnderTest = new ConceptTableWithControls<>( styling, "anything", table, controls );
   }//End Method

   @Test public void shouldProvideContent() {
      assertThat( systemUnderTest.content(), is( systemUnderTest.getContent() ) );
   }//End Method

   @Test public void shouldProvideTitle() {
      assertThat( systemUnderTest.getText(), is( "anything" ) );
   }//End Method

   @Test public void shouldNotBeCollapsible() {
      assertThat( systemUnderTest.isCollapsible(), is( false ) );
   }//End Method

   @Test public void shouldProvideTable() {
      assertThat( systemUnderTest.table(), is( notNullValue() ) );
      assertThat( systemUnderTest.content().getCenter(), is( systemUnderTest.table() ) );
   }//End Method

   @Test public void shouldProvideControls() {
      assertThat( systemUnderTest.controls(), is( notNullValue() ) );
      assertThat( systemUnderTest.content().getRight(), is( systemUnderTest.controls() ) );
   }//End Method

   @Test public void shouldProvideInstructionsWhenEmpty(){
      verify( styling ).createWrappedTextLabel( ConceptTableWithControls.NO_CONTENT_INFORMATION );
      Label node = ( Label ) systemUnderTest.table().getPlaceholder();
      assertThat( node.getText(), is( ConceptTableWithControls.NO_CONTENT_INFORMATION ) );
   }//End Method

   @Test public void shouldMaximizeSize(){
      assertThat( systemUnderTest.getMaxWidth(), is( Double.MAX_VALUE ) );
      assertThat( systemUnderTest.getMaxHeight(), is( Double.MAX_VALUE ) );
   }//End Method

}//End Class
