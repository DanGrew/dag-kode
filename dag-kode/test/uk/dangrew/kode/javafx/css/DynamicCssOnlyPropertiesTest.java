/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.css;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.utility.conversion.ColorConverter;

public class DynamicCssOnlyPropertiesTest {
   
   private static final String EMPTY_STYLE = "";
   
   private ColorConverter colorConverterForTesting;
   
   private Node trackLookup;
   private Node barLookup;
   @Mock private ProgressBar progressBar;
   @Spy private ColorConverter colorConverter;
   private CssOnlyProperties css;
   private DynamicCssOnlyProperties systemUnderTest;
   
   @Before public void initialiseSystemUnderTest(){
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      trackLookup = new BorderPane();
      barLookup = new BorderPane();
      
      colorConverterForTesting = new ColorConverter();
      css = new CssOnlyProperties();
      systemUnderTest = new DynamicCssOnlyProperties( colorConverter, css );
   }//End Method
   
   @Ignore
   @Test public void manual() throws InterruptedException{
      progressBar = new ProgressBar( 0.4 );
      TestApplication.launch( () -> new BorderPane( progressBar ) );
      systemUnderTest = new DynamicCssOnlyProperties();
      
      Thread.sleep( 2000 );
      systemUnderTest.applyCustomColours( Color.BLACK, Color.AQUAMARINE, progressBar );
      
      Thread.sleep( 2000 );
      systemUnderTest.applyCustomColours( Color.ORANGE, Color.SEASHELL, progressBar );
      
      Thread.sleep( 2000 );
      systemUnderTest.applyCustomColours( Color.INDIGO, Color.PEACHPUFF, progressBar );
      
      Thread.sleep( 1000000 );
   }//End Method

   @Test public void shouldApplyCustomBarAndTrackColour(){
      when( progressBar.lookup( DynamicCssOnlyProperties.BAR_LOOKUP ) ).thenReturn( barLookup );
      when( progressBar.lookup( DynamicCssOnlyProperties.TRACK_LOOKUP ) ).thenReturn( trackLookup );
      
      systemUnderTest.applyCustomColours( Color.RED, Color.GREEN, progressBar );
      assertThat( barLookup.getStyle(), is( systemUnderTest.formatBackgroundColourProperty( colorConverterForTesting.colorToHex( Color.RED ) ) ) );
      assertThat( trackLookup.getStyle(), is( systemUnderTest.formatBackgroundColourProperty( colorConverterForTesting.colorToHex( Color.GREEN ) ) ) );
   }//End Method
   
   @Test public void shouldIdentifyMissingBarLookupNodeAndHandle(){
      when( progressBar.lookup( DynamicCssOnlyProperties.BAR_LOOKUP ) ).thenReturn( null );
      when( progressBar.lookup( DynamicCssOnlyProperties.TRACK_LOOKUP ) ).thenReturn( trackLookup );
      
      systemUnderTest.applyCustomColours( Color.RED, Color.GREEN, progressBar );
      assertThat( barLookup.getStyle(), is( EMPTY_STYLE ) );
      assertThat( trackLookup.getStyle(), is( EMPTY_STYLE ) );
   }//End Method
   
   @Test public void shouldIdentifyMissingTrackLookupNodeAndHandle(){
      when( progressBar.lookup( DynamicCssOnlyProperties.BAR_LOOKUP ) ).thenReturn( barLookup );
      when( progressBar.lookup( DynamicCssOnlyProperties.TRACK_LOOKUP ) ).thenReturn( null );
      
      systemUnderTest.applyCustomColours( Color.RED, Color.GREEN, progressBar );
      assertThat( barLookup.getStyle(), is( EMPTY_STYLE ) );
      assertThat( trackLookup.getStyle(), is( EMPTY_STYLE ) );
   }//End Method
   
   @Test public void shouldFormatColourStringIntoFxProperty(){
      assertThat( 
               systemUnderTest.formatBackgroundColourProperty( "#120A8F" ),
               is( "-fx-background-color: #120A8F;" )
      );
   }//End Method
   
   @Test public void shouldApplyBackgroundToScrollPane(){
      ScrollPane scroller = new ScrollPane();
      systemUnderTest.applyBackgroundColour( scroller, Color.RED );
      assertThat( scroller.getStyle(), is( systemUnderTest.formatBackgroundColourProperty( colorConverterForTesting.colorToHex( Color.RED ) ) ) );
   }//End Method

   @Test public void shouldRemoveBackgroundFromScrollPane(){
      ScrollPane scroller = new ScrollPane();
      systemUnderTest.removeScrollPaneBorder( scroller );
      assertThat( scroller.getStyle(), is( systemUnderTest.formatBackgroundColourProperty( css.transparent() ) ) );
   }//End Method
}//End Class
