/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.registrations;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ReadOnlyChangeListenerRegistrationImplTest {
   
   private ObjectProperty< String > property;
   private ObjectProperty< String > assertion;
   private ReadOnlyChangeListenerRegistrationImpl< String > systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      assertion = new SimpleObjectProperty<>();
      property = new SimpleObjectProperty<>();
      
      systemUnderTest = new ReadOnlyChangeListenerRegistrationImpl<>(
               property, 
               ( source, old, update ) -> assertion.set( update )
      );
   }//End Method
   
   @Test public void shouldAddListener() {
      systemUnderTest.register();
      
      assertThat( assertion.get(), nullValue() );

      String objectToSet = "aything";
      property.set( objectToSet );
      
      assertThat( assertion.get(), is( objectToSet ) );
   }//End Method
   
   @Test public void shouldRemoveListener() {
      systemUnderTest.register();
      assertThat( assertion.get(), nullValue() );

      String objectToSet = "aything";
      property.set( objectToSet );
      assertThat( assertion.get(), is( objectToSet ) );
      
      systemUnderTest.unregister();
      
      objectToSet = "next value";
      property.set( objectToSet );
      assertThat( assertion.get(), not( objectToSet ) );
   }//End Method
   
   @Test( expected = IllegalStateException.class ) public void shouldNotRegisterMultipleTimes(){
      systemUnderTest.register();
      systemUnderTest.register();
   }//End Method

}//End Class
