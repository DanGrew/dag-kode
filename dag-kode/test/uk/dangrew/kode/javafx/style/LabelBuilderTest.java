package uk.dangrew.kode.javafx.style;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import uk.dangrew.kode.TestCommon;
import uk.dangrew.kode.launch.TestApplication;

public class LabelBuilderTest {

   private LabelBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      systemUnderTest = new LabelBuilder();
   }//End Method

   @Test public void shouldSetText() {
      Label label = systemUnderTest.withText( "anything" ).build();
      assertThat( label.getText(), is( "anything" ) );
   }//End Method
   
   @Test public void shouldSetBackground() {
      Label label = systemUnderTest.withBackgroundColour( Color.RED ).build();
      assertThat( label.getBackground().getFills().get( 0 ).getFill(), is( Color.RED ) );
   }//End Method
   
   @Test public void shouldSetMaxWidth() {
      Label label = systemUnderTest.withMaxWidth().build();
      assertThat( label.getMaxWidth(), is( Double.MAX_VALUE ) );
   }//End Method
   
   @Test public void shouldSetMaxHeight() {
      Label label = systemUnderTest.withMaxHeight().build();
      assertThat( label.getMaxHeight(), is( Double.MAX_VALUE ) );
   }//End Method
   
   @Test public void shouldSetBold() {
      double sizeBefore = new Label().getFont().getSize();
      Label label = systemUnderTest.asBold().build();
      assertThat( label.getFont().getSize(), is( sizeBefore ) );
      TestCommon.assertThatFontIsBold( label.getFont() );
   }//End Method
   
   @Test public void shouldSetFontSize() {
      Label label = systemUnderTest.withFont( 23, true ).build();
      assertThat( label.getFont().getSize(), is( 23.0 ) );
      TestCommon.assertThatFontIsBold( label.getFont() );
      
      label = systemUnderTest.withFont( 23, false ).build();
      assertThat( label.getFont().getSize(), is( 23.0 ) );
      assertThat( FontWeight.findByName( label.getFont().getStyle() ), is( FontWeight.NORMAL ) );
   }//End Method
   
   @Test public void shouldWrapText() {
      Label label = systemUnderTest.withWrappedText().build();
      assertThat( label.isWrapText(), is( true ) );
   }//End Method
   
   @Test public void shouldTextColour() {
      Label label = systemUnderTest.withTextColour( Color.SKYBLUE ).build();
      assertThat( label.getTextFill(), is( Color.SKYBLUE ) );
   }//End Method
   
   @Test public void shouldAlign() {
      Label label = systemUnderTest.positioned( Pos.CENTER ).build();
      assertThat( label.getAlignment(), is( Pos.CENTER ) );
   }//End Method
   
}//End Class
