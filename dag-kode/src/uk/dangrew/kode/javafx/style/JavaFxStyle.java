/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.style;

import java.io.File;

import de.jensd.fx.glyphs.GlyphIcon;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import uk.dangrew.kode.friendly.controlsfx.FriendlyFileChooser;
import uk.dangrew.kode.javafx.spinner.DefensiveDoubleSpinnerValueFactory;
import uk.dangrew.kode.javafx.spinner.DefensiveIntegerSpinnerValueFactory;
import uk.dangrew.kode.javafx.spinner.DoublePropertySpinner;
import uk.dangrew.kode.javafx.spinner.IntegerPropertySpinner;
import uk.dangrew.kode.javafx.spinner.PropertySpinner;

/**
 * The {@link JavaFxStyle} provides the common styling options for JavaFx elements.
 */
public class JavaFxStyle {

   static final int MAXIMUM_FONT_SIZE = 500;
   static final int MINIMUM_FONT_SIZE = 1;
   static final double FULL_WIDTH_COLUMN = 100.0;
   static final double HALF_WIDTH_COLUMN = 50.0;
   static final int TITLE_FONT_SIZE = 30;
   static final double PADDING = 10;
   
   static final File USER_HOME_FILE = new File( System.getProperty( "user.home" ) );
   
   /**
    * Method to get the column percentage for half of the width.
    * @return the percentage for a half width column.
    */
   public double halfColumnWidth(){
      return HALF_WIDTH_COLUMN;
   }//End Method
   
   /**
    * Method to apply a standard padding across {@link Region}s.
    * @param region the {@link Region} to apply to.
    */
   public void applyBasicPadding( Region region ) {
      region.setPadding( new Insets( PADDING ) );
   }//End Method
   
   /**
    * Method to create a bold {@link Label}.
    * @param title the text in the {@link Label}.
    * @param fontSize the size of the {@link Font}.
    * @return the constructed {@link Label}.
    */
   public Label createBoldLabel( String title, double fontSize ) {
      Label label = new Label( title );
      Font existingFont = label.getFont();
      label.setFont( Font.font( existingFont.getFamily(), FontWeight.BOLD, FontPosture.REGULAR, fontSize ) );
      return label;
   }//End Method
   
   /**
    * Method to create a bold {@link Label}.
    * @param title the text in the {@link Label}.
    * @return the constructed {@link Label}.
    */
   public Label createBoldLabel( String title ) {
      return createBoldLabel( title, Font.getDefault().getSize() );
   }//End Method
   
   /**
    * Method to create a new {@link Label} with the given text that is wrapped
    * across lines.
    * @param text the {@link String} text on the {@link Label}.
    * @return the {@link Label}.
    */
   public Label createWrappedTextLabel( String text ) {
      Label label = new Label( text );
      label.setWrapText( true );
      return label;
   }//End Method
   
   /**
    * Method to configure an {@link IntegerPropertySpinner} for the given property and range.
    * @param spinner the {@link IntegerPropertySpinner} to configure.
    * @param property the {@link IntegerProperty} to bind to.
    * @param min the min range.
    * @param max the max range.
    * @param step the step of increments.
    */
   public void configureIntegerSpinner( 
            IntegerPropertySpinner spinner, 
            IntegerProperty property, 
            int min, 
            int max,
            int step
   ){
      DefensiveIntegerSpinnerValueFactory factory = new DefensiveIntegerSpinnerValueFactory( min, max );
      factory.setAmountToStepBy( step );
      
      spinner.setValueFactory( factory );
      spinner.bindProperty( property );
      spinner.setMaxWidth( Double.MAX_VALUE );
      spinner.setEditable( true );
   }//End Method
   
   /**
    * Method to configure an {@link DoublePropertySpinner} for the given property and range.
    * @param spinner the {@link DoublePropertySpinner} to configure.
    * @param property the {@link DoubleProperty} to bind to.
    * @param min the min range.
    * @param max the max range.
    * @param step the step of increments.
    */
   public void configureDoubleSpinner( 
            DoublePropertySpinner spinner, 
            DoubleProperty property, 
            double min, 
            double max,
            double step
   ){
      DefensiveDoubleSpinnerValueFactory factory = new DefensiveDoubleSpinnerValueFactory( min, max );
      factory.setAmountToStepBy( step );
      
      spinner.setValueFactory( factory );
      spinner.bindProperty( property );
      spinner.setMaxWidth( Double.MAX_VALUE );
      spinner.setEditable( true );
   }//End Method
   
   /**
    * Method to configure a {@link ColorPicker} to synchronise with the given property.
    * @param colorPicker the {@link ColorPicker} for changing the {@link Color}.
    * @param colorProperty the property being configured.
    */
   public void configureColorPicker( ColorPicker colorPicker, ObjectProperty< Color > colorProperty ){
      configureColorPicker( colorPicker, colorProperty.get() );
      colorPicker.valueProperty().addListener( ( source, old, updated ) -> colorProperty.set( updated ) );
      colorProperty.addListener( ( source, old, updated ) -> colorPicker.valueProperty().set( updated ) );
   }//End Method
   
   /**
    * Method to configure a {@link ColorPicker}.
    * @param colorPicker the {@link ColorPicker} for changing the {@link Color}.
    * @param initialValue the initial value.
    */
   public void configureColorPicker( ColorPicker colorPicker, Color initialValue ) {
      colorPicker.setMaxWidth( Double.MAX_VALUE );
      colorPicker.valueProperty().set( initialValue );
   }//End Method
   
   /**
    * Method to construct a title for the configuration panel.
    * @param title the string title to create a {@link Label} for.
    * @return the {@link Label} constructed.
    */
   public Label constructTitle( String title ) {
      Label titleLabel = createBoldLabel( title, TITLE_FONT_SIZE );
      titleLabel.setAlignment( Pos.CENTER );
      GridPane.setConstraints( titleLabel, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER );
      return titleLabel;
   }//End Method
   
   /**
    * Method to configure the {@link ColumnConstraints} for the {@link GridPane} given.
    * @param pane the {@link GridPane} to configure.
    * @param percentages the percentages to add as constraints, {@link ColumnConstraints} per
    * value provided.
    */
   public void configureConstraintsForColumnPercentages( GridPane pane, double... percentages ){
      for ( double value : percentages ) {
         ColumnConstraints column = new ColumnConstraints();
         column.setPercentWidth( value );
         pane.getColumnConstraints().add( column );
      }
   }//End Method
   
   /**
    * Method to configure an even number of columns, fairly sharing the width.
    * @param pane the {@link GridPane} to configure.
    * @param numberOfColumns the number of columns to divide the width in to.
    */
   public void configureConstraintsForEvenColumns( GridPane pane, int numberOfColumns ){
      double percentage = 100.0 / numberOfColumns;
      for ( int i = 0; i < numberOfColumns; i++ ) {
         ColumnConstraints column = new ColumnConstraints();
         column.setPercentWidth( percentage );
         pane.getColumnConstraints().add( column );
      }
   }//End Method
   
   /**
    * Method to configure an even number of columns, fairly sharing the width.
    * @param pane the {@link GridPane} to configure.
    * @param numberOfColumns the number of columns to divide the width in to.
    */
   public void configureConstraintsForEvenRows( GridPane pane, int numberOfRows ){
      double percentage = 100.0 / numberOfRows;
      for ( int i = 0; i < numberOfRows; i++ ) {
         RowConstraints column = new RowConstraints();
         column.setPercentHeight( percentage );
         pane.getRowConstraints().add( column );
      }
   }//End Method
   
   /**
    * Method to configure the {@link RowConstraints} for the {@link GridPane} given.
    * @param pane the {@link GridPane} to configure.
    * @param percentages the percentages to add as constraints, {@link RowConstraints} per
    * value provided.
    */
   public void configureConstraintsForRowPercentages( GridPane pane, double... percentages ){
      for ( double value : percentages ) {
         RowConstraints row = new RowConstraints();
         row.setPercentHeight( value );
         pane.getRowConstraints().add( row );
      }
   }//End Method
   
   /**
    * Method to configure the {@link GridPane} to have a single column spreading the width.
    * @param pane the {@link GridPane} to configure.
    */
   public void configureFullWidthConstraints( GridPane pane ) {
      configureConstraintsForColumnPercentages( pane, FULL_WIDTH_COLUMN );
   }//End Method
   
   /**
    * Method to configure the {@link GridPane} to have two columns equally sharing the width.
    * @param pane the {@link GridPane} to configure.
    */
   public void configureHalfWidthConstraints( GridPane pane ) {
      configureConstraintsForColumnPercentages( pane, HALF_WIDTH_COLUMN, HALF_WIDTH_COLUMN );
   }//End Method
   
   /**
    * Method to configure the {@link FriendlyFileChooser}.
    * @param fileChooser the {@link FriendlyFileChooser} to configure.
    * @param title the title to set.
    */
   public void configureFileChooser( FriendlyFileChooser fileChooser, String title ) {
      fileChooser.setTitle( title );
      fileChooser.setInitialDirectory( USER_HOME_FILE );
   }//End Method
   
   /**
    * Method to configure a {@link Font} {@link PropertySpinner}.
    * @param spinner the {@link PropertySpinner} to configure.
    * @param property the {@link ObjectProperty} to bind to.
    */
   public void configureFontSizeSpinner( 
            PropertySpinner< Integer, Font> spinner, 
            ObjectProperty< Font > property
   ){
      spinner.setValueFactory( new DefensiveIntegerSpinnerValueFactory( MINIMUM_FONT_SIZE, MAXIMUM_FONT_SIZE ) );
      spinner.bindProperty( 
               property,
               size -> Font.font( property.get().getFamily(), size ),
               font -> ( int )font.getSize()
      );
      spinner.setMaxWidth( Double.MAX_VALUE );
      spinner.setEditable( true );
   }//End Method
   
   /**
    * Method to apply a style change where there is no background but instead a {@link Color} placed
    * when pressed and removed when released.
    * @param button the {@link ButtonBase} to apply the style to.
    * @param backgroundWhenPressed the {@link Color} to use for the background.
    */
   public void removeBackgroundAndColourOnClick( ButtonBase button, Color backgroundWhenPressed ) {
      button.setBackground( null );
      button.setOnMousePressed( event -> button.setBackground( new Background( 
               new BackgroundFill( backgroundWhenPressed, null, null ) 
      ) ) );
      button.setOnMouseReleased( event -> button.setBackground( null ) );
   }//End Method

   /**
    * Method to create a {@link Button} with the given {@link GlyphIcon} on it that resizes with the {@link Button}.
    * @param glyph the {@link GlyphIcon} to place on the {@link Button}. 
    * @return the {@link Button}.
    */
   public Button createGlyphButton( GlyphIcon< ? > glyph ) {
      Button button = new Button( "", glyph );
      
      ChangeListener< Number > glyphSizeUpdater = ( s, o, n ) -> {
         glyph.glyphSizeProperty().set( Math.min( 
                  button.getWidth(), 
                  button.getHeight()
         ) - 20 );
      };
      
      glyphSizeUpdater.changed( null, button.getWidth(), button.getWidth() );
      
      button.widthProperty().addListener( glyphSizeUpdater );
      button.heightProperty().addListener( glyphSizeUpdater );
      return button;
   }//End Method
   
}//End Class
