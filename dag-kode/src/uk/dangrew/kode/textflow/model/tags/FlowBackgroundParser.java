package uk.dangrew.kode.textflow.model.tags;

import javafx.scene.paint.Color;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class FlowBackgroundParser extends ColourParser {

   public FlowBackgroundParser( TextFlowFormat format ){
      super( format, TextFlowSchema.FlowBackground );
   }//End Constructor
   
   @Override protected String build( 
            TextFlowBuilder builder,
            String parsedValue,
            Color colour
   ){
      if ( colour == null ) {
         builder.resetFlowBackground();
         return token();
      } else {
         builder.withFlowBackground( colour );
         return token() + format().wrap( parsedValue );
      }
   }//End Method

}//End Classs
