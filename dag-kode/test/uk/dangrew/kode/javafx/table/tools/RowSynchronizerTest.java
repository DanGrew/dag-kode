package uk.dangrew.kode.javafx.table.tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import uk.dangrew.kode.concept.ConceptStore;
import uk.dangrew.kode.javafx.platform.JavaFxThreading;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.column.ReadOnlyTableColumns;
import uk.dangrew.kode.javafx.table.controller.GeneralConceptTableController;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.testutilities.TestColumnsPopulator;
import uk.dangrew.kode.testutilities.TestConcept;
import uk.dangrew.kode.testutilities.TestConceptStore;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class RowSynchronizerTest {

    private ConceptTable<TestConcept> table;
    private ConceptStore<TestConcept> store;
    private ObservableList<TestConcept> items;
    private RowSynchronizer<TestConcept> systemUnderTest;

    @Before public void initialiseSystemUnderTest() {
        TestApplication.startPlatform();

        store = new TestConceptStore();
        JavaFxThreading.runAndWait(() -> table = new ConceptTable<>(
                new TestColumnsPopulator(),
                new GeneralConceptTableController<>(store))
        );
        items = FXCollections.observableArrayList();
        systemUnderTest = new RowSynchronizer<>(table, items);
    }//End Method

    @Test public void shouldInitiallyPopulate() {
        items.add(new TestConcept("Concept1"));
        items.add(new TestConcept("Concept2"));

        systemUnderTest = new RowSynchronizer<>(table, items);
        assertThat(table.getRows(), hasSize(2));
        assertThat(table.getRows().get(0).concept(), is(items.get(0)));
        assertThat(table.getRows().get(1).concept(), is(items.get(1)));
    }//End Method

    @Test public void shouldUpdateRowWhenAddedToList() {
        assertThat(table.getRows(), is(empty()));

        items.add(new TestConcept("Concept1"));
        assertThat(table.getRows(), hasSize(1));
        assertThat(table.getRows().get(0).concept(), is(items.get(0)));

        items.add(new TestConcept("Concept2"));
        assertThat(table.getRows(), hasSize(2));
        assertThat(table.getRows().get(0).concept(), is(items.get(0)));
        assertThat(table.getRows().get(1).concept(), is(items.get(1)));
    }//End Method

    @Test public void shouldUpdateRowWhenRemovedFromList() {
        items.add(new TestConcept("Concept1"));
        items.add(new TestConcept("Concept2"));

        assertThat(table.getRows(), hasSize(2));
        items.remove(0);
        assertThat(table.getRows(), hasSize(1));
        assertThat(table.getRows().get(0).concept(), is(items.get(0)));

        items.remove(0);
        assertThat(table.getRows(), is(empty()));
    }//End Method

    @Test public void shouldDetach() {
        assertThat(table.getRows(), is(empty()));

        items.add(new TestConcept("Concept1"));
        assertThat(table.getRows(), hasSize(1));
        assertThat(table.getRows().get(0).concept(), is(items.get(0)));

        systemUnderTest.detach();
        items.add(new TestConcept("Concept2"));
        assertThat(table.getRows(), hasSize(1));
        assertThat(table.getRows().get(0).concept(), is(items.get(0)));
    }//End Method

}//End Class
