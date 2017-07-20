package uk.dangrew.kode.javafx.spinner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.function.Function;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StringExtractConverterTest {

   private Object object;
   @Mock private Function< Object, String > extractor;
   @Mock private Function< String, Object> resolver;
   private StringExtractConverter< Object > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      object = new Object();
      systemUnderTest = new StringExtractConverter<>(
               extractor, resolver, "Default"
      );
   }//End Method

   @Test public void shouldToStringWithFunction() {
      when( extractor.apply( object ) ).thenReturn( "anything" );
      assertThat( systemUnderTest.toString( object ), is( "anything" ) );
   }//End Method
   
   @Test public void shouldFromStringWithFunction() {
      when( resolver.apply( "anything" ) ).thenReturn( object );
      assertThat( systemUnderTest.fromString( "anything" ), is( object ) );
   }//End Method
   
   @Test public void shouldProvideDefaultString() {
      assertThat( systemUnderTest.toString( null ), is( "Default" ) );
      verifyNoMoreInteractions( extractor );
   }//End Method
   
   @Test public void shouldProvideUnresolved() {
      assertThat( systemUnderTest.fromString( null ), is( nullValue() ) );
      verifyNoMoreInteractions( resolver );
   }//End Method

}//End Class
