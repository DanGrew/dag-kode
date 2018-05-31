package uk.dangrew.kode.javafx.style;

import org.dom4j.tree.BackedList;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import uk.dangrew.kode.friendly.javafx.FriendlyDesktop;
import uk.dangrew.kode.styling.FontFamilies;

public class TextFlowBuilder {

   private static final JavaFxStyle styling = new JavaFxStyle();
   private static final String CALIBRI = "Calibri";
   
   private final String prefferedFontFamily;
   private final double defaultFontSize;
   private final TextFlow flow;
   
   private Color colour;
   private double currentFontSize;
   
   public TextFlowBuilder() {
      this( Font.getDefault().getSize() );
   }//End Constructor
   
   public TextFlowBuilder( double size ) {
      this.flow = new TextFlow();
      this.defaultFontSize = size;
      this.currentFontSize = defaultFontSize;
      if ( FontFamilies.getUsableFontFamilies().contains( CALIBRI ) ) {
         this.prefferedFontFamily = CALIBRI;
      } else {
         this.prefferedFontFamily = Font.getDefault().getFamily();
      }
      this.resetColour();
   }//End Constructor
   
   private Text createText( String string ) {
      Text text = new Text( string );
      text.setFill( colour );
      flow.getChildren().add( text );
      return text;
   }//End Method
   
   public TextFlowBuilder normal( String string ) {
      Text text = createText( string );
      text.setFont( Font.font( prefferedFontFamily, FontWeight.NORMAL, currentFontSize ) );
      return this;
   }//End Method
   
   public TextFlowBuilder bold( String string ) {
      Text text = createText( string );
      text.setFont( Font.font( prefferedFontFamily, FontWeight.BOLD, currentFontSize ) );
      return this;
   }//End Method
   
   public TextFlowBuilder italic( String string ) {
      Text text = createText( string );
      text.setFont( Font.font( prefferedFontFamily, FontPosture.ITALIC, currentFontSize ) );
      return this;
   }//End Method
   
   public TextFlowBuilder newLine() {
      return normal( "\n" );
   }//End Method
   
   public TextFlowBuilder withBackground( Color colour ) {
      flow.setBackground( styling.backgroundFor( colour ) );
      return this;
   }//End Method
   
   public TextFlowBuilder withBorder( Color colour, double thickness ) {
      flow.setBorder( styling.borderFor( colour, thickness ) );
      return this;
   }//End Method
   
   public TextFlowBuilder withPadding( int padding ) {
      flow.setPadding( new Insets( padding ) );
      return this;
   }//End Method
   
   public TextFlowBuilder withHyperlink( String address, FriendlyDesktop desktop ) {
      Hyperlink hyperlink = new Hyperlink( address );
      hyperlink.setOnAction( e -> desktop.browseToIconWebsite( address ) );
      flow.getChildren().add( hyperlink );
      return this;
   }//End Method
   
   public TextFlowBuilder withColour( Color colour ) {
      this.colour = colour;
      return this;
   }//End Method
   
   public TextFlowBuilder resetColour(){
      this.colour = Color.BLACK;
      return this;
   }//End Method
   
   public TextFlowBuilder withSize( double size ) {
      this.currentFontSize = size;
      return this;
   }//End Method
   
   public TextFlowBuilder resetFontSize(){
      this.currentFontSize = defaultFontSize;
      return this;
   }//End Method
   
   public TextFlow build() {
      return flow;
   }//End Method

}//End Constructor
