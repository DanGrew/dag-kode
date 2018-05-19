package uk.dangrew.kode.javafx.style;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LabelBuilder {

   private static final JavaFxStyle styling = new JavaFxStyle();
   private final Label label;
   
   public LabelBuilder() {
      this.label = new Label();
   }//End Constructor
   
   public LabelBuilder withText( String text ) {
      label.setText( text );
      return this;
   }//End Method

   public LabelBuilder asBold() {
      return withFont( label.getFont().getSize(), true );
   }//End Method
   
   public LabelBuilder withFont( double size, boolean bold ) {
      return withFont( label.getFont().getFamily(), size, bold );
   }//End Method
   
   public LabelBuilder withFont( String family, double size, boolean bold ) {
      if ( bold ) {
         label.setFont( Font.font( 
                  family, FontWeight.BOLD, FontPosture.REGULAR, size 
         ) );
      } else {
         label.setFont( Font.font( 
                  family, size 
         ) );
      }
      return this;
   }//End Method

   public LabelBuilder withWrappedText() {
      label.setWrapText( true );
      return this;
   }//End Method

   public LabelBuilder withTextColour( Color colour ) {
      label.setTextFill( colour );
      return this;
   }//End Method
   
   public LabelBuilder withMaxWidth(){
      label.setMaxWidth( Double.MAX_VALUE );
      return this;
   }//End Method
   
   public LabelBuilder withMaxHeight(){
      label.setMaxHeight( Double.MAX_VALUE );
      return this;
   }//End Method
   
   public LabelBuilder positioned( Pos position ) {
      label.setAlignment( position );
      return this;
   }//End Method
   
   public LabelBuilder withBackgroundColour( Color colour ) {
      label.setBackground( styling.backgroundFor( colour ) );
      return this;
   }//End Method
   
   public LabelBuilder withBorder( Color colour, double thickness ) {
      label.setBorder( styling.borderFor( colour, thickness ) );
      return this;
   }//End Method
   
   public LabelBuilder withPadding( int padding ) {
      label.setPadding( new Insets( padding ) );
      return this;
   }//End Method

   public Label build() {
      return label;
   }//End Method
   
}//End Class
