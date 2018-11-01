package uk.dangrew.kode.textflow.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uk.dangrew.kode.TestCommon;
import uk.dangrew.kode.launch.TestApplication;
import uk.dangrew.kode.utility.conversion.ColorConverter;

public class TextFlowParserTest {

   private static final String TEST_STRING = "something to parse";
   private static final String TEST_STRING_2 = "extra text";
   private static final String TEST_STRING_3 = "wow, more, really....";
   
   private ColorConverter colours;
   private TextFlowFormat format;
   private TextFlowParser systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      MockitoAnnotations.initMocks( this );
      
      colours = new ColorConverter();
      format = new TextFlowFormat();
      systemUnderTest = new TextFlowParser();
   }//End Method

   @Test public void shouldParseNormalText() {
      Optional< TextFlow > result = systemUnderTest.parse( format.normalTag() + TEST_STRING );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, FontPosture.REGULAR, null );
   }//End Method
   
   @Test public void shouldParseBoldText() {
      Optional< TextFlow > result = systemUnderTest.parse( format.boldTag() + TEST_STRING );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.BOLD, null, null );
   }//End Method
   
   @Test public void shouldParseColourText() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               format.textColourTag() + format.wrap( "RED" ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.RED, FontWeight.NORMAL, null, null );
      
      result = systemUnderTest.parse( 
               format.textColourTag() + format.wrap( colours.colorToHex( Color.BLUE ) ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLUE, FontWeight.NORMAL, null, null );
   }//End Method
   
   @Test public void shouldParseNewLine() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               format.normalTag() + TEST_STRING 
               + format.newLineTag() 
               + format.normalTag() + TEST_STRING );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextIsFound( result.get(), 1, "\n", Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextIsFound( result.get(), 2, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
   }//End Method
   
   @Test public void shouldParseSize() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               format.flowSizeTag() + format.wrap( 20.0 )
               + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, 20.0 );
      
      result = systemUnderTest.parse( 
               format.flowSizeTag()
               + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
   }//End Method
   
   @Test public void shouldParsePadding() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               format.flowPaddingTag() + format.wrap( 10.0 ) 
               + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), null, 10.0, null, null );
      
      result = systemUnderTest.parse( 
               format.flowPaddingTag()
               + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), null, null, null, null );
   }//End Method
   
   @Test public void shouldParseBackground() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               format.flowBackgroundTag() + format.wrap( "RED" ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), Color.RED, null, null, null );
      
      result = systemUnderTest.parse( 
               format.flowBackgroundTag() + format.wrap( colours.colorToHex( Color.BLUE ) ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), Color.BLUE, null, null, null );
   }//End Method
   
   @Test public void shouldParseBorder() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( "RED" ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), null, null, Color.RED, 2.0 );
      
      result = systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( 5.0 ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), null, null, Color.BLACK, 5.0 );
      
      result = systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( "RED" ) + format.wrap( 5.0 ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), null, null, Color.RED, 5.0 );
      
      result = systemUnderTest.parse( 
               format.flowBorderTag() + format.wrap( 5.0 ) + format.wrap( "RED" ) + format.normalTag() + TEST_STRING 
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.NORMAL, null, null );
      assertThatTextFlowIsConfigured( result.get(), null, null, Color.BLACK, 5.0 );
   }//End Method
   
   @Test public void shouldParseHyperlink() {
      Optional< TextFlow > result = systemUnderTest.parse( format.hyperlinkTag() + TEST_STRING );
      assertThatHyperlinkIsFound( result.get(), 0, TEST_STRING, null );
   }//End Method
   
   @Test public void shouldParseMultipleTextElements() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               format.flowPaddingTag() + format.wrap( 10.0 )
               + format.flowBorderTag() + format.wrap( 5.0 ) + format.wrap( "BLUE" )
               + format.boldTag() + TEST_STRING
               + format.textColourTag() + format.wrap( "RED" )
               + format.normalTag() + TEST_STRING_2
               + format.flowSizeTag() + format.wrap( 20.0 )
               + format.textColourTag()
               + format.boldTag() + TEST_STRING_3
               + format.flowBackgroundTag() + format.wrap( "RED" )
               + format.flowBorderTag()
      );
      assertThatTextIsFound( result.get(), 0, TEST_STRING, Color.BLACK, FontWeight.BOLD, null, null );
      assertThatTextIsFound( result.get(), 1, TEST_STRING_2, Color.RED, FontWeight.NORMAL, FontPosture.REGULAR, null );
      assertThatTextIsFound( result.get(), 2, TEST_STRING_3, Color.BLACK, FontWeight.BOLD, null, 20.0 );
      assertThatTextFlowIsConfigured( result.get(), Color.RED, 10.0, null, null );
   }//End Method
   
   @Test public void e2e1() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               "[t-c][RED][n]hello![t-c][n]here i am"
      );
      assertThatTextIsFound( result.get(), 0, "hello!", Color.RED, FontWeight.NORMAL, null, null );
      assertThatTextIsFound( result.get(), 1, "here i am", Color.BLACK, FontWeight.NORMAL, FontPosture.REGULAR, null );
   }//End Method
   
   @Test public void e2e2() {
      Optional< TextFlow > result = systemUnderTest.parse( 
               "[f-p][10]\n[s][40][b]Title"
      );
      assertThatTextIsFound( result.get(), 0, "Title", Color.BLACK, FontWeight.BOLD, null, 40.0 );
      assertThatTextFlowIsConfigured( result.get(), null, 10.0, null, null );
   }//End Method
   
   @Test public void shouldResetBetweenCalls(){
      shouldParseNormalText();
      shouldParseBoldText();
      shouldParseNormalText();
   }//End Method
   
   @Test public void shouldSafelyReturnIfNotParseable(){
      assertThat( systemUnderTest.parse( "anything" ), is( Optional.empty() ) );
   }//End Method
   
   private void assertThatTextIsFound( 
            TextFlow result, 
            int element, 
            String textValue,
            Color colour,
            FontWeight weight, 
            FontPosture posture,
            Double size
   ) {
      Text text = ( Text ) result.getChildren().get( element );
      assertThat( text.getText(), is( textValue ) );
      assertThat( text.getFill(), is( colour ) );
      assertThat( text.getFont().getSize(), is( Optional.ofNullable( size ).orElse( Font.getDefault().getSize() ) ) );
      Optional.ofNullable( weight ).ifPresent( w -> TestCommon.assertThatFontHasWeight( text.getFont(), w ) );
      Optional.ofNullable( posture ).ifPresent( p -> TestCommon.assertThatFontHasPosture( text.getFont(), p ) );
   }//End Method
   
   private void assertThatHyperlinkIsFound( 
            TextFlow result, 
            int element, 
            String textValue,
            Double size
   ) {
      Hyperlink text = ( Hyperlink ) result.getChildren().get( element );
      assertThat( text.getText(), is( textValue ) );
      assertThat( text.getFont().getSize(), is( Optional.ofNullable( size ).orElse( Font.getDefault().getSize() ) ) );
   }//End Method
   
   private void assertThatTextFlowIsConfigured( 
            TextFlow result, 
            Color background, 
            Double padding, 
            Color borderColour, 
            Double borderThickness 
   ){
      if ( background != null ) {
         assertThat( result.getBackground().getFills().get( 0 ).getFill(), is( background ) );
      }
      
      if ( padding != null ) {
         TestCommon.assertThatRegionHasPadding( result, padding );
      }
      
      if ( borderColour != null ) {
         TestCommon.assertThatRegionHasBorder( result, borderColour, null );
      }
      
      if ( borderThickness != null ) {
         TestCommon.assertThatRegionHasBorder( result, null, borderThickness );
      }
   }//End Method

}//End Class
