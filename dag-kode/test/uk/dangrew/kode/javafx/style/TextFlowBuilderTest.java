package uk.dangrew.kode.javafx.style;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TextFlowBuilderTest {

   private TextFlowBuilder systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      systemUnderTest = new TextFlowBuilder();
   }//End Method

   @Test public void shouldProvideUsableInterface() throws InterruptedException {
      TextFlow flow = systemUnderTest
         .normal( "Starting a sentence " )
         .bold( "with bold in the middle" )
         .build();
      
      assertThat( flow.getChildren(), hasSize( 2 ) );
      
      Text text1 = ( Text ) flow.getChildren().get( 0 );
      assertThat( text1.getText(), is( "Starting a sentence " ) );
      assertThat( FontWeight.findByName( text1.getFont().getStyle() ), is( not( FontWeight.BOLD ) ) );
      
      Text text2 = ( Text ) flow.getChildren().get( 1 );
      assertThat( text2.getText(), is( "with bold in the middle" ) );
      assertThat( FontWeight.findByName( text2.getFont().getStyle() ), is( FontWeight.BOLD ) );
   }//End Method

}//End Class
