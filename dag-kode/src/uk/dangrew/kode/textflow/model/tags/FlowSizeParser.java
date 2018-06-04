package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class FlowSizeParser extends NumberParser {

   public FlowSizeParser( TextFlowFormat format ){
      super( format, TextFlowSchema.FontSize );
   }//End Constructor
   
   @Override protected String build( 
            TextFlowBuilder builder,
            String parsedValue,
            Double number 
   ){
      if ( number == null ) {
         builder.resetTextSize();
         return token();
      } else {
         builder.withFontSize( number.doubleValue() );
         return token() + format().wrap( parsedValue );
      }
   }//End Method

}//End Classs
