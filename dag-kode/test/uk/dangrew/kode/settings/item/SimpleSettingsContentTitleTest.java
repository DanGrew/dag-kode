package uk.dangrew.kode.settings.item;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import uk.dangrew.kode.launch.TestApplication;

public class SimpleSettingsContentTitleTest {

   private static final String TITLE = "title";
   private static final String DESCRIPTION = "description";
   
   private SimpleSettingsContentTitle systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new SimpleSettingsContentTitle( TITLE, DESCRIPTION );
   }//End Method

   @Test public void shouldProvideTitle() {
      assertThat( systemUnderTest.getTitle().getText(), is( TITLE ) );
      assertThat( systemUnderTest.getChildren().contains( systemUnderTest.getTitle() ), is( true ) );
   }//End Method
   
   @Test public void shouldProvideDescription() {
      assertThat( systemUnderTest.getDescription().getText(), is( DESCRIPTION ) );
      assertThat( systemUnderTest.getChildren().contains( systemUnderTest.getDescription() ), is( true ) );
   }//End Method
   
   @Test public void shouldIgnoreEmptyDescription() {
      systemUnderTest = new SimpleSettingsContentTitle( TITLE, null );
      assertThat( systemUnderTest.getDescription(), is( nullValue() ) );
   }//End Method

}//End Class
