/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.storage.structure;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import uk.dangrew.kode.observable.PrivatelyModifiableObservableListImpl;
import uk.dangrew.sd.utility.TestCommon;

/**
 * {@link MappedObservableStoreManagerImpl} test.
 */
public class MappedObservableStoreManagerImplTest {

   private Object object;
   private ObjectStoreManager< String, Object > systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      object = new Object();
      systemUnderTest = new MappedObservableStoreManagerImpl<>(
               Object::toString
      );
   }//End Method
   
   @Test public void shouldStoreObject() {
      Assert.assertTrue( systemUnderTest.isEmpty() ); 
      systemUnderTest.store( object );
      Assert.assertFalse( systemUnderTest.isEmpty() );
      Assert.assertTrue( systemUnderTest.hasKey( object.toString() ) );
      Assert.assertTrue( systemUnderTest.hasObject( object ) );
   }//End Method
   
   @Test public void shouldRetrieveObjectWithKey() {
      shouldStoreObject();
      Assert.assertEquals( object, systemUnderTest.get( object.toString() ) );
   }//End Method
   
   @Test public void shouldRetrieveNothingWithUnrecognisedKey() {
      Assert.assertNull( systemUnderTest.get( "somethingElse" ) );
   }//End Method
   
   @Test public void shouldRetrieveNothingWithNullKey() {
      Assert.assertNull( systemUnderTest.get( null ) );
   }//End Method
   
   @Test public void shouldRemoveObjectWithKey() {
      shouldStoreObject();
      Assert.assertEquals( object, systemUnderTest.remove( object.toString() ) );
      Assert.assertTrue( systemUnderTest.isEmpty() );
      Assert.assertFalse( systemUnderTest.hasKey( object.toString() ) );
   }//End Method
   
   @Test public void shouldProvideObservableList() {
      Assert.assertNotNull( systemUnderTest.objectList() );
      Assert.assertTrue( systemUnderTest.objectList().isEmpty() );
   }//End Method
   
   @Test public void shouldKeepStoreAddAndListInSync() {
      shouldStoreObject();
      Assert.assertTrue( systemUnderTest.objectList().contains( object ) );
   }//End Method
   
   @Test public void shouldKeepStoreRemoveAndListInSync() {
      shouldStoreObject();
      Assert.assertTrue( systemUnderTest.objectList().contains( object ) );
      systemUnderTest.remove( object.toString() );
      Assert.assertFalse( systemUnderTest.objectList().contains( object ) );
   }//End Method
   
   @Test public void shouldNotHaveNullKey(){
      Assert.assertFalse( systemUnderTest.hasKey( null ) );
   }//End Method
   
   @Test public void shouldNotStoreNull(){
      Object mocked = mock( Object.class );
      when( mocked.toString() ).thenReturn( null );
      systemUnderTest.store( mocked );
      Assert.assertTrue( systemUnderTest.isEmpty() );
   }//End Method

   @Test public void shouldNotGetNull(){
      Assert.assertNull( systemUnderTest.get( null ) );
   }//End Method
   
   @Test public void shouldNotRemoveNull(){
      Assert.assertNull( systemUnderTest.remove( null ) );
   }//End Method
   
   @Test public void shouldOverwriteKeyMapping(){
      shouldStoreObject();

      Assert.assertEquals( object, systemUnderTest.get( object.toString() ) );
      final Object object2 = new Object();
      systemUnderTest.store( object2 );
      Assert.assertEquals( object2, systemUnderTest.get( object2.toString() ) );
   }//End Method
   
   @Test public void shouldAddToListOnceOnly(){
      systemUnderTest.store( object );
      Assert.assertTrue( systemUnderTest.hasKey( object.toString() ) );
      systemUnderTest.store( object );
      Assert.assertEquals( 1, systemUnderTest.objectList().size() );
   }//End Method
   
   @Test public void shouldUseAppropriateClassToRestrictModifications(){
      Assert.assertTrue( systemUnderTest.objectList() instanceof PrivatelyModifiableObservableListImpl );
   }//End Method
   
   @Test( expected = UnsupportedOperationException.class ) public void shouldNotAllowModificationsToList(){
      systemUnderTest.objectList().add( object );
   }//End Method

   @Test public void addAndForeachShouldBeSynchronized() {
      TestCommon.assertConcurrencyIsNotAnIssue( 
               count -> systemUnderTest.store( count ), 
               count -> systemUnderTest.objectList().forEach( item -> item.toString() ), 
               1000 
      );
   }//End Method
   
}//End Class
