package uk.dangrew.kode.javafx.style;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
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
   private final Background defaultBackground;
   private final Border defaultBorder;
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
      this.defaultBackground = flow.getBackground();
      this.defaultBorder = flow.getBorder();
      if ( FontFamilies.getUsableFontFamilies().contains( CALIBRI ) ) {
         this.prefferedFontFamily = CALIBRI;
      } else {
         this.prefferedFontFamily = Font.getDefault().getFamily();
      }
      this.resetTextColour();
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
   
   public TextFlowBuilder withFlowBackground( Color colour ) {
      flow.setBackground( styling.backgroundFor( colour ) );
      return this;
   }//End Method
   
   public TextFlowBuilder resetFlowBackground() {
      flow.setBackground( defaultBackground );
      return this;
   }//End Method
   
   public TextFlowBuilder withFlowBorder( Color colour, double thickness ) {
      flow.setBorder( styling.borderFor( colour, thickness ) );
      return this;
   }//End Method
   
   public TextFlowBuilder withFlowBorder( double thickness ) {
      return withFlowBorder( Color.BLACK, thickness );
   }//End Method
   
   public TextFlowBuilder withFlowBorder( Color colour ) {
      flow.setBorder( styling.borderFor( colour, 2 ) );
      return this;
   }//End Method
   
   public TextFlowBuilder resetFlowBorder() {
      flow.setBorder( defaultBorder );
      return this;
   }//End Method
   
   public TextFlowBuilder withFlowPadding( double padding ) {
      flow.setPadding( new Insets( padding ) );
      return this;
   }//End Method
   
   public TextFlowBuilder resetFlowPadding(){
      return withFlowPadding( 0 );
   }//End Method
   
   public TextFlowBuilder withHyperlink( String address, FriendlyDesktop desktop ) {
      Hyperlink hyperlink = new Hyperlink( address.trim() );
      hyperlink.setFont( Font.font( prefferedFontFamily, FontWeight.NORMAL, currentFontSize ) );
      hyperlink.setOnAction( e -> desktop.browseToIconWebsite( address ) );
      flow.getChildren().add( hyperlink );
      return this;
   }//End Method
   
   public TextFlowBuilder withTextColour( Color colour ) {
      this.colour = colour;
      return this;
   }//End Method
   
   public TextFlowBuilder resetTextColour(){
      this.colour = Color.BLACK;
      return this;
   }//End Method
   
   public TextFlowBuilder withFontSize( double size ) {
      this.currentFontSize = size;
      return this;
   }//End Method
   
   public TextFlowBuilder resetTextSize(){
      this.currentFontSize = defaultFontSize;
      return this;
   }//End Method
   
   public TextFlow build() {
      return flow;
   }//End Method

}//End Constructor
