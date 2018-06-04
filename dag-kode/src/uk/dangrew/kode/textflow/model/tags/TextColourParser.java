package uk.dangrew.kode.textflow.model.tags;

import javafx.scene.paint.Color;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class TextColourParser extends ColourParser {

   public TextColourParser( TextFlowFormat format ){
      super( format, TextFlowSchema.TextColour );
   }//End Constructor
   
   @Override protected String build( 
            TextFlowBuilder builder,
            String parsedValue,
            Color colour
   ){
      if ( colour == null ) {
         builder.resetTextColour();
         return token();
      } else {
         builder.withTextColour( colour );
         return token() + format().wrap( parsedValue );
      }
   }//End Method

}//End Classs
