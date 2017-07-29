package uk.dangrew.kode.javafx.style;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.ObjectProperty;

public class PropertyRowBuilderTest {

   private PropertyRowBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new PropertyRowBuilder();
   }//End Method
   
   @Test public void shouldProvideDefaults(){
      assertThat( systemUnderTest.labelName(), is( "" ) );
      assertThat( systemUnderTest.property(), is( nullValue() ) );
      assertThat( systemUnderTest.isEditable(), is( true ) );
   }//End Method

   @Test public void shouldProvideLabelName() {
      assertThat( systemUnderTest.withLabelName( "anything" ), is( systemUnderTest ) );
      assertThat( systemUnderTest.labelName(), is( "anything" ) );
   }//End Method
   
   @Test public void shouldProvideProperty() {
      ObjectProperty< Double > property = mock( ObjectProperty.class );
      assertThat( systemUnderTest.withProperty( property ), is( systemUnderTest ) );
      assertThat( systemUnderTest.property(), is( property ) );
   }//End Method
   
   @Test public void shouldProvideEditable() {
      assertThat( systemUnderTest.allowEditing( true ), is( systemUnderTest ) );
      assertThat( systemUnderTest.isEditable(), is( true ) );
   }//End Method

}//End Class
