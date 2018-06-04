package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class FlowPaddingParser extends NumberParser {

   public FlowPaddingParser( TextFlowFormat format ){
      super( format, TextFlowSchema.FlowPadding );
   }//End Constructor
   
   @Override protected String build( 
            TextFlowBuilder builder,
            String parsedValue,
            Double number 
   ){
      if ( number == null ) {
         builder.resetFlowPadding();
         return token();
      } else {
         builder.withFlowPadding( number.doubleValue() );
         return token() + format().wrap( parsedValue );
      }
   }//End Method

}//End Classs
