package uk.dangrew.kode.javafx.table.options;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import uk.dangrew.kode.comparator.Comparators;
import uk.dangrew.kode.concept.Concept;
import uk.dangrew.kode.concept.ConceptStore;
import uk.dangrew.kode.testutilities.TestConcept;
import uk.dangrew.kode.testutilities.TestConceptAlternate;
import uk.dangrew.kode.testutilities.TestConceptAlternateStore;
import uk.dangrew.kode.testutilities.TestConceptStore;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;

public class ConceptOptionsTest {

   private TestConcept concept1;
   private TestConcept concept2;
   private TestConceptAlternate alternate1;
   private TestConceptAlternate alternate2;
   
   private ConceptStore<TestConcept> conceptStore;
   private ConceptStore<TestConceptAlternate> alternateStore;

   private ConceptOptions<Concept> systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      conceptStore = new TestConceptStore();
      alternateStore = new TestConceptAlternateStore();
      concept1 = new TestConcept( "Item1" );
      concept2 = new TestConcept( "Item2" );
      alternate1 = new TestConceptAlternate( "TestConceptAlternate1" );
      alternate2 = new TestConceptAlternate( "TestConceptAlternate2" );

      systemUnderTest = new ConceptOptionsImpl<>( Arrays.asList( conceptStore, alternateStore ) );
   }//End Method

   @Test public void shouldProvideCombinedFoods() {
      assertThat( systemUnderTest.options(), is( notNullValue() ) );
      assertThat( systemUnderTest.options(), is( systemUnderTest.options() ) );
   }//End Method

   @Test public void shouldIncludedAddedTestConcepts() {
      conceptStore.store( concept1 );
      assertThat( systemUnderTest.options(), Matchers.contains( concept1 ) );
      conceptStore.store( concept2 );
      assertThat( systemUnderTest.options(), Matchers.contains( concept1, concept2 ) );
   }//End Method

   @Test public void shouldNotDuplicateTestConcepts() {
      conceptStore.store( concept1 );
      conceptStore.store( concept1 );
      conceptStore.store( concept1 );
      assertThat( systemUnderTest.options(), Matchers.contains( concept1 ) );
   }//End Method

   @Test public void shouldExcludeRemovedTestConcept() {
      conceptStore.store( concept1 );
      conceptStore.store( concept2 );
      assertThat( systemUnderTest.options(), Matchers.contains( concept1, concept2 ) );
      conceptStore.removeConcept( concept1 );
      assertThat( systemUnderTest.options(), Matchers.contains( concept2 ) );
      conceptStore.removeConcept( concept2 );
      assertThat( systemUnderTest.options(), is( empty() ) );
   }//End Method

   @Test public void shouldIncludedAddedTestConceptAlternates() {
      alternateStore.store( alternate1 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate1 ) );
      alternateStore.store( alternate2 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate1, alternate2 ) );
   }//End Method

   @Test public void shouldNotDuplicateTestConceptAlternates() {
      alternateStore.store( alternate1 );
      alternateStore.store( alternate1 );
      alternateStore.store( alternate1 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate1 ) );
   }//End Method

   @Test public void shouldExcludeRemovedTestConceptAlternates() {
      alternateStore.store( alternate1 );
      alternateStore.store( alternate2 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate1, alternate2 ) );
      alternateStore.removeConcept( alternate1 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate2 ) );
      alternateStore.removeConcept( alternate2 );
      assertThat( systemUnderTest.options(), is( empty() ) );
   }//End Method

   @Test public void shouldSortFoodAlphabeticallyByName(){
      TestConcept item1 = new TestConcept( "Mine" );
      TestConcept item2 = new TestConcept( "Yours" );
      TestConcept item3 = new TestConcept( "His" );

      TestConceptAlternate alternate1 = new TestConceptAlternate( "Hers" );
      TestConceptAlternate alternate2 = new TestConceptAlternate( "Ours" );
      TestConceptAlternate alternate3 = new TestConceptAlternate( "Worlds" );

      conceptStore.store( item1 );
      assertThat( systemUnderTest.options(), Matchers.contains( item1 ) );
      conceptStore.store( item2 );
      assertThat( systemUnderTest.options(), Matchers.contains( item1, item2 ) );
      conceptStore.store( item3 );
      assertThat( systemUnderTest.options(), Matchers.contains( item3, item1, item2 ) );

      alternateStore.store( alternate1 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate1, item3, item1, item2 ) );
      alternateStore.store( alternate2 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate1, item3, item1, alternate2, item2 ) );
      alternateStore.store( alternate3 );
      assertThat( systemUnderTest.options(), Matchers.contains( alternate1, item3, item1, alternate2, alternate3, item2 ) );
   }//End Method

   @Test public void shouldCustomSortFood(){
      systemUnderTest.customSort( ( a, b ) ->
         Comparators.reverseComparator( Comparators.STRING_ALPHABETICAL ).compare(
                     a.properties().nameProperty().get(),
                     b.properties().nameProperty().get()
      ) );

      TestConcept item1 = new TestConcept( "Mine" );
      TestConcept item2 = new TestConcept( "Yours" );
      TestConcept item3 = new TestConcept( "His" );

      TestConceptAlternate alternate1 = new TestConceptAlternate( "Hers" );
      TestConceptAlternate alternate2 = new TestConceptAlternate( "Ours" );
      TestConceptAlternate alternate3 = new TestConceptAlternate( "Worlds" );

      conceptStore.store( item1 );
      assertThat( systemUnderTest.options(), Matchers.contains( item1 ) );
      conceptStore.store( item2 );
      assertThat( systemUnderTest.options(), Matchers.contains( item2, item1 ) );
      conceptStore.store( item3 );
      assertThat( systemUnderTest.options(), Matchers.contains( item2, item1, item3  ) );

      alternateStore.store( alternate1 );
      assertThat( systemUnderTest.options(), Matchers.contains( item2, item1, item3, alternate1 ) );
      alternateStore.store( alternate2 );
      assertThat( systemUnderTest.options(), Matchers.contains( item2, alternate2, item1, item3, alternate1 ) );
      alternateStore.store( alternate3 );
      assertThat( systemUnderTest.options(), Matchers.contains( item2, alternate3, alternate2, item1, item3, alternate1 ) );
   }//End Method

   @Test public void shouldFindFirstMatch(){
      TestConcept item1 = new TestConcept( "One" );
      TestConcept item2 = new TestConcept( "Two" );
      TestConcept item3 = new TestConcept( "Three" );

      TestConceptAlternate alternate1 = new TestConceptAlternate( "Four" );
      TestConceptAlternate alternate2 = new TestConceptAlternate( "Five" );
      TestConceptAlternate alternate3 = new TestConceptAlternate( "Three" );

      conceptStore.store( item1 );
      conceptStore.store( item2 );
      conceptStore.store( item3 );
      alternateStore.store( alternate1 );
      alternateStore.store( alternate2 );
      alternateStore.store( alternate3 );

      assertThat( systemUnderTest.find( "One" ), Matchers.is( item1 ) );
      assertThat( systemUnderTest.find( "Four" ), Matchers.is( alternate1 ) );
      assertThat( systemUnderTest.find( "Three" ), Matchers.is( item3 ) );
      assertThat( systemUnderTest.find( "something else" ), is( nullValue() ) );
   }//End Method

   @Test public void shouldPopulateFoodsInitially(){
      conceptStore.store( concept1 );
      alternateStore.store( alternate1 );
      systemUnderTest = new ConceptOptionsImpl<>( Arrays.asList( conceptStore, alternateStore ) );
      assertThat( systemUnderTest.options(), Matchers.contains( concept1, alternate1 ) );
   }//End Method

   @Test public void shouldProvideFirst(){
      assertThat( systemUnderTest.first(), is( nullValue() ) );

      conceptStore.store( concept1 );
      alternateStore.store( alternate1 );
      systemUnderTest = new ConceptOptionsImpl<>( Arrays.asList( conceptStore, alternateStore ) );
      assertThat( systemUnderTest.first(), Matchers.is( concept1 ) );
   }//End Method

   @Test public void shouldRespondToNameChange(){
      conceptStore.store( concept1 );
      conceptStore.store( concept2 );

      TestConcept alphaItem = new TestConcept( "zzz" );
      conceptStore.store( alphaItem );

      assertThat( systemUnderTest.options().get( 2 ), Matchers.is( alphaItem ) );
      alphaItem.properties().nameProperty().set( "AAA" );
      assertThat( systemUnderTest.options().get( 0 ), Matchers.is( alphaItem ) );
   }//End Method

   @Test public void shouldNotRespondToNameChangeWhenRemoved(){
      conceptStore.store( concept1 );
      conceptStore.store( concept2 );

      TestConcept alphaItem = new TestConcept( "zzz" );
      conceptStore.store( alphaItem );

      assertThat( systemUnderTest.options().get( 2 ), Matchers.is( alphaItem ) );
      conceptStore.remove( alphaItem.properties().id() );
      alphaItem.properties().nameProperty().set( "AAA" );
      assertThat( systemUnderTest.options().contains( alphaItem ), is( false ) );
   }//End Method

}//End Class
