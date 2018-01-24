package uk.dangrew.kode.javafx.style;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uk.dangrew.kode.styling.FontFamilies;

public class TextFlowBuilder {

   private static final String CALIBRI = "Calibri";
   
   private final String prefferedFontFamily;
   private final double defaultFontSize;
   private final TextFlow flow;
   
   public TextFlowBuilder() {
      this.flow = new TextFlow();
      this.defaultFontSize = Font.getDefault().getSize();
      if ( FontFamilies.getUsableFontFamilies().contains( CALIBRI ) ) {
         this.prefferedFontFamily = CALIBRI;
      } else {
         this.prefferedFontFamily = Font.getDefault().getFamily();
      }
   }//End Constructor
   
   private Text createText( String string ) {
      Text text = new Text( string );
      flow.getChildren().add( text );
      return text;
   }//End Method
   
   public TextFlowBuilder normal( String string ) {
      createText( string );
      return this;
   }//End Method
   
   public TextFlowBuilder bold( String string ) {
      Text text = createText( string );
      text.setFont( Font.font( prefferedFontFamily, FontWeight.BOLD, defaultFontSize ) );
      return this;
   }//End Method
   
   public TextFlow build() {
      return flow;
   }//End Method

}//End Constructor