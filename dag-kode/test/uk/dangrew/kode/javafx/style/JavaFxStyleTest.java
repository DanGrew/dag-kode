/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.style;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.jensd.fx.glyphs.GlyphIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uk.dangrew.kode.TestCommon;
import uk.dangrew.kode.friendly.controlsfx.FriendlyFileChooser;
import uk.dangrew.kode.javafx.spinner.DefensiveDoubleSpinnerValueFactory;
import uk.dangrew.kode.javafx.spinner.DefensiveIntegerSpinnerValueFactory;
import uk.dangrew.kode.javafx.spinner.DoublePropertySpinner;
import uk.dangrew.kode.javafx.spinner.IntegerPropertySpinner;
import uk.dangrew.kode.javafx.spinner.PropertySpinner;
import uk.dangrew.kode.launch.TestApplication;

/**
 * {@link JavaFxStyle} test.
 */
public class JavaFxStyleTest {

   public static final int TITLE_FONT_SIZE = JavaFxStyle.TITLE_FONT_SIZE;
   private JavaFxStyle systemUnderTest;
   
   @BeforeClass public static void initialisePlatform(){
      TestApplication.startPlatform();
   }//End Method
   
   @Before public void initialiseSystemUnderTest(){
      systemUnderTest = new JavaFxStyle();
   }//End Method
   
   @Test public void shouldProvideLabelWithBoldFont() {
      final String text = "anything";
      Label label = systemUnderTest.createBoldLabel( text );
      
      assertThat( label.getText(), is( text ) );
      assertThat( FontWeight.findByName( label.getFont().getStyle() ), is( FontWeight.BOLD ) );
   }//End Method
   
   @Test public void shouldProvideLabel() {
      final String text = "anything";
      Label label = systemUnderTest.createBoldLabel( text, 34, Color.ANTIQUEWHITE );
      
      assertThat( label.getText(), is( text ) );
      assertThat( FontWeight.findByName( label.getFont().getStyle() ), is( FontWeight.BOLD ) );
      assertThat( label.getTextFill(), is( Color.ANTIQUEWHITE ) );
   }//End Method
   
   @Test public void shouldProvideLabelWithBoldFontAndSize() {
      final String text = "anything";
      final double size = 34;
      Label label = systemUnderTest.createBoldLabel( text, size );
      
      assertThat( label.getText(), is( text ) );
      assertThat( FontWeight.findByName( label.getFont().getStyle() ), is( FontWeight.BOLD ) );
      assertThat( label.getFont().getSize(), closeTo( size, TestCommon.precision() ) );
   }//End Method
   
   @Test public void shouldConfigureIntegerPropertySpinner(){
      IntegerProperty property = new SimpleIntegerProperty();
      IntegerPropertySpinner spinner = new IntegerPropertySpinner();
      final int min = 20;
      final int max = 40;
      final int step = 5;
      
      systemUnderTest.configureIntegerSpinner( spinner, property, min, max, step );
      
      assertThat( spinner.getValueFactory(), instanceOf( DefensiveIntegerSpinnerValueFactory.class ) );
      
      spinner.getValueFactory().setValue( 50 );
      assertThat( spinner.getValueFactory().getValue(), is( max ) );
      
      spinner.getValueFactory().setValue( 10 );
      assertThat( spinner.getValueFactory().getValue(), is( min ) );
      
      spinner.increment();
      assertThat( spinner.getValueFactory().getValue(), is( min + step ) );
      assertThat( property.intValue(), is( min + step ) );
      
      final int newValue = 35;
      property.setValue( newValue );
      assertThat( spinner.getValueFactory().getValue(), is( newValue ) );
      
      assertThat( spinner.isEditable(), is( true ) );
      assertThat( spinner.getMaxWidth(), is( Double.MAX_VALUE ) );
   }//End Method
   
   @Test public void shouldConfigureDoublePropertySpinner(){
      DoubleProperty property = new SimpleDoubleProperty();
      DoublePropertySpinner spinner = new DoublePropertySpinner();
      final double min = 20.5;
      final double max = 40.1;
      final double step = 5.6;
      
      systemUnderTest.configureDoubleSpinner( spinner, property, min, max, step );
      
      assertThat( spinner.getValueFactory(), instanceOf( DefensiveDoubleSpinnerValueFactory.class ) );
      
      spinner.getValueFactory().setValue( 50.0 );
      assertThat( spinner.getValueFactory().getValue(), is( max ) );
      
      spinner.getValueFactory().setValue( 10.4 );
      assertThat( spinner.getValueFactory().getValue(), is( min ) );
      
      spinner.increment();
      assertThat( spinner.getValueFactory().getValue(), is( min + step ) );
      assertThat( property.doubleValue(), is( min + step ) );
      
      final double newValue = 35.7;
      property.setValue( newValue );
      assertThat( spinner.getValueFactory().getValue(), is( newValue ) );
      
      assertThat( spinner.isEditable(), is( true ) );
      assertThat( spinner.getMaxWidth(), is( Double.MAX_VALUE ) );
   }//End Method
   
   @Test public void shouldConstructTitle(){
      final String title = "some title";
      Label constructed = systemUnderTest.constructTitle( title );
      
      assertThat( constructed.getText(), is( title ) );
      TestCommon.assertThatFontIsBold( constructed.getFont() );
      assertThat( constructed.getAlignment(), is( Pos.CENTER ) );
      
      assertThat( GridPane.getColumnIndex( constructed ), is( 0 ) );
      assertThat( GridPane.getRowIndex( constructed ), is( 0 ) );
      assertThat( GridPane.getColumnSpan( constructed ), is( 2 ) );
      assertThat( GridPane.getRowSpan( constructed ), is( 1 ) );
      assertThat( GridPane.getHalignment( constructed ), is( HPos.CENTER ) );
      assertThat( GridPane.getValignment( constructed ), is( VPos.CENTER ) );
   }//End Method
   
   @Test public void shouldProvideUniqueLabelWithWrappedText(){
      final String anyText = "anything that should be wrapped";
      Label label = systemUnderTest.createWrappedTextLabel( anyText );
      
      assertThat( label.getText(), is( anyText ) );
      assertThat( label.isWrapText(), is( true ) );
      assertThat( systemUnderTest.createWrappedTextLabel( anyText ), is( not( label ) ) );
   }//End Method
   
   @Test public void shouldRemoveBackgroundAndColourOnClick(){
      Button button = new Button();
      systemUnderTest.removeBackgroundAndColourOnClick( button, Color.AQUAMARINE );
      
      assertThat( button.getBackground(), is( nullValue() ) );
      
      button.getOnMousePressed().handle( mock( MouseEvent.class ) );
      assertThat( button.getBackground().getFills().get( 0 ).getFill(), is( Color.AQUAMARINE ) );
      
      button.getOnMouseReleased().handle( mock( MouseEvent.class ) );
      assertThat( button.getBackground(), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldConfigureColorPickerWithProperty(){
      ObjectProperty< Color > property = new SimpleObjectProperty<>( Color.BLACK );
      ColorPicker picker = new ColorPicker( Color.RED );
      
      systemUnderTest.configureColorPicker( picker, property );
      assertThat( picker.getMaxWidth(), is( Double.MAX_VALUE ) );
      assertThat( picker.getValue(), is( Color.BLACK ) );
      
      property.set( Color.YELLOW );
      assertThat( picker.getValue(), is( Color.YELLOW ) );
      
      picker.setValue( Color.PURPLE );
      assertThat( property.get(), is( Color.PURPLE ) );
   }//End Method
   
   @Test public void shouldConfigureColorPicker(){
      ColorPicker picker = new ColorPicker( Color.RED );
      
      systemUnderTest.configureColorPicker( picker, Color.BLACK );
      assertThat( picker.getMaxWidth(), is( Double.MAX_VALUE ) );
      assertThat( picker.getValue(), is( Color.BLACK ) );
   }//End Method
   
   @Test public void shouldConfigureColumnConstraints(){
      GridPane pane = new GridPane();
      systemUnderTest.configureConstraintsForColumnPercentages( pane, 10, 20, 70 );
      
      assertThat( pane.getColumnConstraints(), hasSize( 3 ) );
      assertThat( pane.getColumnConstraints().get( 0 ).getPercentWidth(), is( 10.0 ) );
      assertThat( pane.getColumnConstraints().get( 1 ).getPercentWidth(), is( 20.0 ) );
      assertThat( pane.getColumnConstraints().get( 2 ).getPercentWidth(), is( 70.0 ) );
   }//End Method
   
   @Test public void shouldConfigureHalfWidthColumnConstraints(){
      GridPane pane = new GridPane();
      systemUnderTest.configureHalfWidthConstraints( pane );
      
      assertThat( pane.getColumnConstraints(), hasSize( 2 ) );
      assertThat( pane.getColumnConstraints().get( 0 ).getPercentWidth(), is( JavaFxStyle.HALF_WIDTH_COLUMN ) );
      assertThat( pane.getColumnConstraints().get( 1 ).getPercentWidth(), is( JavaFxStyle.HALF_WIDTH_COLUMN ) );
   }//End Method
   
   @Test public void shouldConfigureEvenWidthColumnConstraints(){
      GridPane pane = new GridPane();
      systemUnderTest.configureConstraintsForEvenColumns( pane, 5 );
      
      assertThat( pane.getColumnConstraints(), hasSize( 5 ) );
      for ( int i = 0; i < 5; i++ ) {
         assertThat( pane.getColumnConstraints().get( i ).getPercentWidth(), is( 20.0 ) );
      }
   }//End Method
   
   @Test public void shouldConfigureEvenHeightRowConstraints(){
      GridPane pane = new GridPane();
      systemUnderTest.configureConstraintsForEvenRows( pane, 5 );
      
      assertThat( pane.getRowConstraints(), hasSize( 5 ) );
      for ( int i = 0; i < 5; i++ ) {
         assertThat( pane.getRowConstraints().get( i ).getPercentHeight(), is( 20.0 ) );
      }
   }//End Method
   
   @Test public void shouldConfigureFullWithColumnConstraints(){
      GridPane pane = new GridPane();
      systemUnderTest.configureFullWidthConstraints( pane );
      
      assertThat( pane.getColumnConstraints(), hasSize( 1 ) );
      assertThat( pane.getColumnConstraints().get( 0 ).getPercentWidth(), is( JavaFxStyle.FULL_WIDTH_COLUMN ) );
   }//End Method
   
   @Test public void shouldProvideHaldWidthColumn(){
      assertThat( systemUnderTest.halfColumnWidth(), is( JavaFxStyle.HALF_WIDTH_COLUMN ) );
   }//End Method
   
   @Test public void shouldConfigureRowConstraints(){
      GridPane pane = new GridPane();
      systemUnderTest.configureConstraintsForRowPercentages( pane, 10, 20, 70 );
      
      assertThat( pane.getRowConstraints(), hasSize( 3 ) );
      assertThat( pane.getRowConstraints().get( 0 ).getPercentHeight(), is( 10.0 ) );
      assertThat( pane.getRowConstraints().get( 1 ).getPercentHeight(), is( 20.0 ) );
      assertThat( pane.getRowConstraints().get( 2 ).getPercentHeight(), is( 70.0 ) );
   }//End Method
   
   @Test public void shouldApplyBasicPaddingToRegion(){
      BorderPane region = new BorderPane();
      systemUnderTest.applyBasicPadding( region );
      assertThat( region.getInsets().getBottom(), is( JavaFxStyle.PADDING ) );
      assertThat( region.getInsets().getTop(), is( JavaFxStyle.PADDING ) );
      assertThat( region.getInsets().getLeft(), is( JavaFxStyle.PADDING ) );
      assertThat( region.getInsets().getRight(), is( JavaFxStyle.PADDING ) );
   }//End Method
   
   @Test public void shouldConfigureFriendlyFileChooser(){
      FriendlyFileChooser chooser = mock( FriendlyFileChooser.class );
      systemUnderTest.configureFileChooser( chooser, "anything" );
      verify( chooser ).setTitle( "anything" );
      verify( chooser ).setInitialDirectory( JavaFxStyle.USER_HOME_FILE );
   }//End Method
   
   @Test public void shouldConfigureFontSpinner(){
      Font initial = new Font( "SansSerif", 13 );
      
      PropertySpinner< Integer, Font > spinner = new PropertySpinner<>();
      ObjectProperty< Font > property = new SimpleObjectProperty<>( initial );
      
      systemUnderTest.configureFontSizeSpinner( spinner, property );
      assertThat( spinner.getMaxWidth(), is( Double.MAX_VALUE ) );
      assertThat( spinner.isEditable(), is( true ) );
      
      property.set( new Font( 45 ) );
      assertThat( spinner.getValue(), is( 45 ) );
      property.set( new Font( 34.5 ) );
      assertThat( spinner.getValue(), is( 34 ) );
      
      spinner.getValueFactory().setValue( 45 );
      assertThat( property.get().getFamily(), is( initial.getFamily() ) );
      assertThat( property.get().getSize(), is( 45.0 ) );
      
      assertThat( spinner.getValueFactory(), is( instanceOf( DefensiveIntegerSpinnerValueFactory.class ) ) );
      DefensiveIntegerSpinnerValueFactory factory = ( DefensiveIntegerSpinnerValueFactory ) spinner.getValueFactory();
      assertThat( factory.getMax(), is( JavaFxStyle.MAXIMUM_FONT_SIZE ) );
      assertThat( factory.getMin(), is( JavaFxStyle.MINIMUM_FONT_SIZE ) );
   }//End Method

   @Test public void shouldCreateButtonWithGlyph(){
      GlyphIcon< ? > glyph = new FontAwesomeIconView( FontAwesomeIcon.ADJUST );
      Button button = systemUnderTest.createGlyphButton( glyph );
      assertThat( button.getGraphic(), is( glyph ) );
   }//End Method
   
   @Test public void shouldResizeGlyphInGlyphButton() throws InterruptedException{
      GlyphIcon< ? > glyph = new FontAwesomeIconView( FontAwesomeIcon.ADJUST );
      Button button = systemUnderTest.createGlyphButton( glyph );
      button.setMaxSize( Double.MAX_VALUE, Double.MAX_VALUE );
      TestApplication.launch( () -> new BorderPane( button ), false );
      
      assertThat( glyph.getGlyphSize(), is( 80.0 ) );
      button.resize( 50, 50 );
      assertThat( glyph.getGlyphSize(), is( 30.0 ) );
      
      button.resize( 60, 70 );
      assertThat( glyph.getGlyphSize(), is( 40.0 ) );
      
      button.resize( 80, 55 );
      assertThat( glyph.getGlyphSize(), is( 35.0 ) );
   }//End Method
   
   @Test public void shouldProvideBackgroundFor() {
      Background background = systemUnderTest.backgroundFor( Color.RED );
      assertThat( background.getFills().get( 0 ).getFill(), is( Color.RED ) );
   }//End Method
   
   @Test public void shouldNotProvideBackgroundForNullColour() {
      assertThat( systemUnderTest.backgroundFor( null ), is( nullValue() ) );
   }//End Method
   
   @Test public void shouldProvideBorderFor() {
      Border border = systemUnderTest.borderFor( Color.RED );
      assertBorderIs( Color.RED, border );
   }//End Method
   
   private void assertBorderIs( Color colour, Border border ) {
      assertThat( border.getStrokes().get( 0 ).getBottomStroke(), is( Color.RED ) );
      assertThat( border.getStrokes().get( 0 ).getTopStroke(), is( Color.RED ) );
      assertThat( border.getStrokes().get( 0 ).getRightStroke(), is( Color.RED ) );
      assertThat( border.getStrokes().get( 0 ).getLeftStroke(), is( Color.RED ) );
      
      assertThat( border.getStrokes().get( 0 ).getBottomStyle(), is( BorderStrokeStyle.SOLID ) );
      assertThat( border.getStrokes().get( 0 ).getTopStyle(), is( BorderStrokeStyle.SOLID ) );
      assertThat( border.getStrokes().get( 0 ).getRightStyle(), is( BorderStrokeStyle.SOLID ) );
      assertThat( border.getStrokes().get( 0 ).getLeftStyle(), is( BorderStrokeStyle.SOLID ) );
   }//End Method
   
   @Test public void shouldCreateWrappedTextLabel(){
      Text text = systemUnderTest.createWrappedTextLabel( "anything", 50 );
      assertThat( text.getText(), is( "anything" ) );
      assertThat( text.getWrappingWidth(), is( 50.0 ) );
   }//End Method
   
}//End Class
