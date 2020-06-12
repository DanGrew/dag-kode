package uk.dangrew.kode.javafx.table.base;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.javafx.table.base.ConceptTable;
import uk.dangrew.kode.javafx.table.base.ConceptTableRowImpl;
import uk.dangrew.kode.javafx.table.column.ConceptTableColumnsPopulator;
import uk.dangrew.kode.javafx.table.controller.ConceptTableViewController;
import uk.dangrew.kode.launch.TestApplication;

public class ConceptTableTest {

   @Mock private ConceptTableColumnsPopulator< Concept > populator;
   @Mock private ConceptTableViewController< Concept > controller;
   private ConceptTable< Concept > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new ConceptTable< Concept >( populator, controller );
   }//End Method

   @Test public void shouldPopulateColumns() {
      verify( populator ).populateColumns( systemUnderTest );
   }//End Method
   
   @Test public void shouldBeEditable(){
      assertThat( systemUnderTest.isEditable(), is( true ) );
   }//End Method
   
   @Test public void shouldAssoiatedWithController(){
      verify( controller ).associate( systemUnderTest );
   }//End Method
   
   @Test public void shouldProvideRows(){
      systemUnderTest.getItems().add( new ConceptTableRowImpl<>( mock( Concept.class ) ) );
      assertThat( systemUnderTest.getRows().get( 0 ), is( systemUnderTest.getItems().get( 0 ) ) );
      assertThat( systemUnderTest.getItems(), is( systemUnderTest.getRows() ) );
   }//End Method

}//End Class
