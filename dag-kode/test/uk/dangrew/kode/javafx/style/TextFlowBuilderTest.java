package uk.dangrew.kode.javafx.style;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uk.dangrew.kode.TestCommon;
import uk.dangrew.kode.friendly.javafx.FriendlyDesktop;
import uk.dangrew.kode.launch.TestApplication;

public class TextFlowBuilderTest {

   private TextFlowBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      TestApplication.startPlatform();
      systemUnderTest = new TextFlowBuilder();
   }//End Method

   @Test public void shouldProvideUsableInterface() throws InterruptedException {
      TextFlow flow = systemUnderTest
         .normal( "Starting a sentence " )
         .bold( "with bold in the middle" )
         .italic( "something italic" )
         .withHyperlink( "hyperlink", mock( FriendlyDesktop.class ) )
         .build();
      
      assertThat( flow.getChildren(), hasSize( 4 ) );
      
      Text text1 = ( Text ) flow.getChildren().get( 0 );
      TestCommon.assertThatFontHasWeight( text1.getFont(), FontWeight.NORMAL );
      assertThat( text1.getText(), is( "Starting a sentence " ) );
      assertThat( text1.getFont().getSize(), is( Font.getDefault().getSize() ) );
      assertThat( FontWeight.findByName( text1.getFont().getStyle() ), is( not( FontWeight.BOLD ) ) );
      
      Text text2 = ( Text ) flow.getChildren().get( 1 );
      assertThat( text2.getText(), is( "with bold in the middle" ) );
      assertThat( FontWeight.findByName( text2.getFont().getStyle() ), is( FontWeight.BOLD ) );
      
      Text text3 = ( Text ) flow.getChildren().get( 2 );
      assertThat( text3.getText(), is( "something italic" ) );
      TestCommon.assertThatFontHasPosture( text3.getFont(), FontPosture.ITALIC );
      
      Hyperlink text4 = ( Hyperlink ) flow.getChildren().get( 3 );
      assertThat( text4.getText(), is( "hyperlink" ) );
   }//End Method
   
   @Test public void shouldProvideDefaultSize(){
      TextFlow flow = new TextFlowBuilder( 34 )
               .normal( "Starting a sentence " )
               .build();
      Text text1 = ( Text ) flow.getChildren().get( 0 );
      assertThat( text1.getFont().getSize(), is( 34.0 ) );
   }//End Method
   
   @Test public void shouldMaintainColourAndSize(){
      TextFlow flow = systemUnderTest
               .withColour( Color.RED )
               .withSize( 40 )
               .normal( "Starting a sentence " )
               .resetColour()
               .resetFontSize()
               .normal( "ending a sentence." )
               .build();
      
      Text text1 = ( Text ) flow.getChildren().get( 0 );
      assertThat( text1.getFill(), is( Color.RED ) );
      assertThat( text1.getFont().getSize(), is( 40.0 ) );
      
      Text text2 = ( Text ) flow.getChildren().get( 1 );
      assertThat( text2.getFill(), is( Color.BLACK ) );
      assertThat( text2.getFont().getSize(), is( Font.getDefault().getSize() ) );
   }//End Method

}//End Class
