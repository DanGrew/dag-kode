package uk.dangrew.kode.javafx.table.base;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.dangrew.kode.concept.Concept;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConceptTableRowImplTest {

   @Mock private Concept concept;
   private ConceptTableRow< Concept > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new ConceptTableRowImpl<>( concept );
   }//End Method

   @Test public void shouldProvideFood() {
      assertThat( systemUnderTest.concept(), is( concept ) );
   }//End Method

}//End Class
