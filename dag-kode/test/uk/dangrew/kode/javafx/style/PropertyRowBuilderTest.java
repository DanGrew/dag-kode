package uk.dangrew.kode.javafx.style;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import uk.dangrew.kode.javafx.custom.ResponsiveRegionProperty;

public class PropertyRowBuilderTest {

   private PropertyRowBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new PropertyRowBuilder();
   }//End Method
   
   @Test public void shouldProvideDefaults(){
      assertThat( systemUnderTest.labelName(), is( "" ) );
      assertThat( systemUnderTest.binding(), is( nullValue() ) );
   }//End Method

   @Test public void shouldProvideLabelName() {
      assertThat( systemUnderTest.withLabelName( "anything" ), is( systemUnderTest ) );
      assertThat( systemUnderTest.labelName(), is( "anything" ) );
   }//End Method
   
   @Test public void shouldProvideProperty() {
      ResponsiveRegionProperty property = mock( ResponsiveRegionProperty.class );
      assertThat( systemUnderTest.withBinding( property ), is( systemUnderTest ) );
      assertThat( systemUnderTest.binding(), is( property ) );
   }//End Method
   
}//End Class
