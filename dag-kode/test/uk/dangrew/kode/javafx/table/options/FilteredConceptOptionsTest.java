package uk.dangrew.kode.javafx.table.options;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import uk.dangrew.kode.comparator.Comparators;
import uk.dangrew.kode.testutilities.TestConcept;
import uk.dangrew.kode.testutilities.TestConceptStore;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class FilteredConceptOptionsTest {

   private TestConcept concept1;
   private TestConcept concept2;
   private TestConcept concept3;

   private TestConceptStore store;
   private ConceptOptions< TestConcept > options;
   private FilteredConceptOptions< TestConcept > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );

      store = new TestConceptStore();
      options = new ConceptOptionsImpl<>( store );
      systemUnderTest = new FilteredConceptOptions<>( options );

      concept1 = store.createConcept( "Chicken" );
      concept2 = store.createConcept( "Beans" );
      concept3 = store.createConcept( "Sausages" );
   }//End Method

   @Test public void shouldProvideOptions() {
      assertThat( systemUnderTest.options(), is( options.options() ) );

      store.createConcept( "Almonds" );
      assertThat( systemUnderTest.options(), is( options.options() ) );

      store.removeConcept(concept3);
      assertThat( systemUnderTest.options(), is( options.options() ) );
   }//End Method

   @Test public void shouldProvideReversedSorting() {
      assertThat( systemUnderTest.options().get( 0 ), is(concept2) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept1) );
      assertThat( systemUnderTest.options().get( 2 ), is(concept3) );

      systemUnderTest.invertedSorting().set( true );

      assertThat( systemUnderTest.options().get( 0 ), is(concept3) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept1) );
      assertThat( systemUnderTest.options().get( 2 ), is(concept2) );

      systemUnderTest.invertedSorting().set( false );

      assertThat( systemUnderTest.options().get( 0 ), is(concept2) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept1) );
      assertThat( systemUnderTest.options().get( 2 ), is(concept3) );
   }//End Method

   @Test public void shouldNotFilerByDefault() {
      systemUnderTest.filterString().set( "chi" );
      assertThat( systemUnderTest.options().get( 0 ), is(concept2) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept1) );
      assertThat( systemUnderTest.options().get( 2 ), is(concept3) );

      systemUnderTest.filterString().set( "s" );
      assertThat( systemUnderTest.options().get( 0 ), is(concept2) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept1) );
      assertThat( systemUnderTest.options().get( 2 ), is(concept3) );
   }//End Method

   @Test public void shouldReverseSortAndfilter() {
      systemUnderTest.applyFilter( f -> {
         String filter = systemUnderTest.filterString().get();
         if ( filter == null ) {
            return false;
         }
         filter = filter.toLowerCase();
         String name = f.properties().nameProperty().get().toLowerCase();
         return !name.contains( filter );
      } );

      systemUnderTest.filterString().set( "s" );
      assertThat( systemUnderTest.options().get( 0 ), is(concept2) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept3) );

      systemUnderTest.invertedSorting().set( true );
      assertThat( systemUnderTest.options().get( 0 ), is(concept3) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept2) );

      systemUnderTest.filterString().set( "chick" );
      assertThat( systemUnderTest.options().get( 0 ), is(concept1) );

      systemUnderTest.filterString().set( "s" );
      assertThat( systemUnderTest.options().get( 0 ), is(concept3) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept2) );
   }//End Method

   @Test public void shouldProvideSortingProperty(){
      assertThat( systemUnderTest.invertedSorting().get(), is( false ) );
      systemUnderTest.invertedSorting().set( true );
      assertThat( systemUnderTest.invertedSorting().get(), is( true ) );
      systemUnderTest.invertedSorting().set( false );
      assertThat( systemUnderTest.invertedSorting().get(), is( false ) );
   }//End Method

   @Test public void shouldProvidefilterProperty(){
      assertThat( systemUnderTest.filterString().get(), is( nullValue() ) );
      systemUnderTest.filterString().set( "anything" );
      assertThat( systemUnderTest.filterString().get(), is( "anything" ) );
      systemUnderTest.filterString().set( null );
      assertThat( systemUnderTest.filterString().get(), is( nullValue() ) );
   }//End Method

   @Test public void shouldRefreshWhenSorted(){
      options.customSort( Comparators.reverseComparator( options.comparator() ) );
      assertThat( systemUnderTest.options(), is( options.options() ) );
   }//End Method

   @Test public void shouldApplyFilter(){
      systemUnderTest.applyFilter( f -> f == concept1);
      assertThat( systemUnderTest.options(), is( Arrays.asList(concept2, concept3) ) );

      systemUnderTest.clearFilters();
      systemUnderTest.applyFilter( f -> f.properties().nameProperty().get().contains( "s" ) );
      assertThat( systemUnderTest.options(), is( Arrays.asList(concept1) ) );
   }//End Method

   @Test public void shouldApplyMultipleFilters(){
      systemUnderTest.applyFilter( f -> f == concept1);
      assertThat( systemUnderTest.options(), is( Arrays.asList(concept2, concept3) ) );

      systemUnderTest.applyFilter( f -> f.properties().nameProperty().get().contains( "g" ) );
      assertThat( systemUnderTest.options(), is( Arrays.asList(concept2) ) );
   }//End Method

   @Test public void shouldIgnoreEmptyStringForFilter(){
      systemUnderTest.filterString().set( " " );
      assertThat( systemUnderTest.options().get( 0 ), is(concept2) );
      assertThat( systemUnderTest.options().get( 1 ), is(concept1) );
   }//End Method
}//End Class
