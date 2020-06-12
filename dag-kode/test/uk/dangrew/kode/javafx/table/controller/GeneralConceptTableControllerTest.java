package uk.dangrew.kode.javafx.table.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import uk.dangrew.kode.concept.ConceptStore;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.base.ConceptTableRow;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.testutilities.TestColumnsPopulator;
import uk.dangrew.kode.testutilities.TestConcept;
import uk.dangrew.kode.testutilities.TestConceptStore;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GeneralConceptTableControllerTest {

   private ConceptStore<TestConcept> items;
   private TestConcept exampleConcept;

   private ConceptTable< TestConcept > table;
   private GeneralConceptTableController< TestConcept > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();

      exampleConcept = new TestConcept( "Anything" );

      items = new TestConceptStore();
      items.store(exampleConcept);
      systemUnderTest = new GeneralConceptTableController<>( items );
      table = new ConceptTable<>(
              new TestColumnsPopulator(),
              systemUnderTest
      );
   }//End Method

   @Test public void shouldCreateFoodInStore() {
      TestConcept concept = systemUnderTest.createConcept();
      assertThat( concept, is(notNullValue() ) );
      assertThat( items.get( concept.properties().id() ), is( concept ) );

      TestConcept concept2 = systemUnderTest.createConcept();
      assertThat( concept, is( Matchers.not( concept2 ) ) );
      assertThat( concept.properties().id(), is( Matchers.not( concept2.properties().id() ) ) );
      assertThat( concept2, is( notNullValue() ) );
      assertThat( items.get( concept2.properties().id() ), is( concept2 ) );
   }//End Method

   @Test public void shouldRemoveSelectedFoodFromStoreAndTable() {
      assertThat( items.get( exampleConcept.properties().id() ), is(exampleConcept) );
      table.getSelectionModel().select( 0 );
      systemUnderTest.removeSelectedConcept();
      assertThat( items.get( exampleConcept.properties().id() ), is( nullValue() ) );
      assertThat( table.getItems(), is( empty() ) );
   }//End Method

   @Test public void shouldDuplicateSelection() {
      assertThat( items.get( exampleConcept.properties().id() ), is(exampleConcept) );
      table.getSelectionModel().select( 0 );
      systemUnderTest.copySelectedConcept();
      assertThat( items.objectList().get( 0 ), is(exampleConcept) );
      assertThat( items.objectList().get( 1 ).properties().nameProperty().get(), is( "Anything" + TestConcept.COPY_SUFFIX ) );
      assertThat( table.getItems().get( 0 ).concept(), is(exampleConcept) );
      assertThat( table.getItems().get( 1 ).concept(), is( items.objectList().get( 1 ) ) );
   }//End Method

   @Test public void shouldIgnoreRemovalWithNoSelection() {
      assertThat( items.get( exampleConcept.properties().id() ), is(exampleConcept) );
      systemUnderTest.removeSelectedConcept();
      ConceptTableRow< TestConcept > row = table.getItems().get( 0 );
      assertThat( row.concept().properties().nameProperty().get(), is( exampleConcept.properties().nameProperty().get() ) );
   }//End Method

   @Test public void shouldPopulateTableWithStoreItems(){
      assertThat( table.getItems(), hasSize( 1 ) );
      ConceptTableRow< TestConcept > row = table.getItems().get( 0 );
      assertThat( row.concept().properties().nameProperty().get(), is( exampleConcept.properties().nameProperty().get() ) );
   }//End Method

   @Test public void shouldRemoveFromTableWhenRemovedFromStore(){
      assertThat( items.get( exampleConcept.properties().id() ), is(exampleConcept) );

      items.remove( exampleConcept.properties().id() );
      assertThat( items.get( exampleConcept.properties().id() ), is( nullValue() ) );
      assertThat( table.getItems(), is( empty() ) );
   }//End Method

   @Test public void shouldAddToTableWhenAddedToStore(){
      TestConcept bacon = new TestConcept( "Another" );
      items.store( bacon );
      assertThat( table.getItems(), hasSize( 2 ) );
      assertThat( table.getItems().get( 1 ).concept().properties().nameProperty().get(), is( bacon.properties().nameProperty().get() ) );
   }//End Method

}//End Class
