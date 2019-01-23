package uk.dangrew.kode.javafx.custom;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import uk.dangrew.kode.javafx.registrations.RegistrationManager;
import uk.dangrew.kode.javafx.style.Conversions;
import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.javafx.style.PropertyRowBuilder;
import uk.dangrew.kode.launch.TestApplication;

public class PropertiesPaneTest {

   private static final String TITLE = "The Title"; 
   
   private PropertyRowBuilder row1;
   private PropertyRowBuilder row2;
   private PropertyRowBuilder row3;
   private List< PropertyRowBuilder > rows;
   
   @Mock private RegistrationManager registrations;
   @Spy private JavaFxStyle styling;
   @Spy private Conversions conversions;
   private PropertiesPane systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      row1 = new PropertyRowBuilder()
               .withLabelName( "First" )
               .withBinding( new BoundDoubleAsTextProperty( new SimpleObjectProperty< Double >( 25.0 ), true ) );
      row2 = new PropertyRowBuilder()
               .withLabelName( "Second" )
               .withBinding( new BoundDoubleAsTextProperty( new SimpleObjectProperty< Double >( 3.0 ), false ) );
      row3 = new PropertyRowBuilder()
               .withLabelName( "Third" )
               .withBinding( new BoundDoubleAsTextProperty( new SimpleObjectProperty< Double >( 0.34857 ), true ) );
      rows = Arrays.asList( row1, row2, row3 );
      
      systemUnderTest = new PropertiesPane( 
               styling, 
               TITLE, 
               row1, row2, row3 
      );
   }//End Method

   @Test public void shouldProvideTitleAndContent() {
      assertThat( systemUnderTest.getText(), is( TITLE ) );
      assertThat( systemUnderTest.getContent(), is( systemUnderTest.content() ) );
   }//End Method
   
   @Test public void shouldConfigureRowsForNumberOfProperties() {
      verify( styling ).configureConstraintsForEvenRows( systemUnderTest.content(), rows.size() );
   }//End Method
   
   @Test public void shouldConfigureColumns() {
      verify( styling ).configureConstraintsForEvenColumns( systemUnderTest.content(), 2 );
   }//End Method
   
   @Test public void shouldHoldAllLabelsInPosition() {
      for ( int i = 0; i < rows.size(); i++ ) {
         Label label = systemUnderTest.labelForRow( i );
         assertThat( label.getText(), is( rows.get( i ).labelName() ) );
         assertThat( GridPane.getRowIndex( label ), is( i ) );
         assertThat( GridPane.getColumnIndex( label ), is( 0 ) );
      }
   }//End Method
   
   @Test public void shouldHoldTextFieldsForEachProperty() {
      for ( int i = 0; i < rows.size(); i++ ) {
         TextField field = systemUnderTest.textFieldForRow( i );
         BoundDoubleAsTextProperty binding = ( BoundDoubleAsTextProperty ) rows.get( i ).binding();
         assertThat( field.getText(), is( conversions.format( binding.property().get() ) ) );
         assertThat( GridPane.getRowIndex( field ), is( i ) );
         assertThat( GridPane.getColumnIndex( field ), is( 1 ) );
         assertThat( field.isEditable(), is( binding.isEditable() ) );
      }
   }//End Method
   
   @Test public void shouldBindPropertiesForTextFields() {
      for ( int i = 0; i < rows.size(); i++ ) {
         TextField field = systemUnderTest.textFieldForRow( i );
         ObjectProperty< Double > property = ( ( BoundDoubleAsTextProperty ) rows.get( i ).binding() ).property();
         
         assertThat( field.getText(), is( conversions.format( property.get() ) ) );
         field.setText( "35.0" );
         assertThat( field.getText(), is( conversions.format( property.get() ) ) );
         property.set( 789.0 );
         assertThat( field.getText(), is( conversions.format( property.get() ) ) );
      }
   }//End Method
   
   @Test public void shouldNotBeCollapsible() {
      assertThat( systemUnderTest.isCollapsible(), is( false ) );
   }//End Method
   
   @Test public void shouldSetMaxWidthOnRegion(){
      for ( int i = 0; i < rows.size(); i++ ) {
         TextField field = systemUnderTest.textFieldForRow( i );
         assertThat( field.getMaxWidth(), is( Double.MAX_VALUE ) );
      }
   }//End Method

}//End Class
