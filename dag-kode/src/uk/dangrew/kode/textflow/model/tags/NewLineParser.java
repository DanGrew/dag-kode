package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class NewLineParser implements TextFlowTagParser {

   private final String token;
   
   public NewLineParser( TextFlowFormat format ) {
      this.token = TextFlowSchema.NewLine.token( format );
   }//End Constructor
   
   public String parse( 
            String input, 
            Integer positionOfNext, 
            TextFlowBuilder builder 
   ) {
      builder.newLine();
      return token;
   }//End Method
   
}//End Class
