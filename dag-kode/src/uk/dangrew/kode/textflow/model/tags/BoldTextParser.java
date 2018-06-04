package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class BoldTextParser extends TextFormParser {

   public BoldTextParser( TextFlowFormat format ) {
      super( format, TextFlowSchema.Bold, TextFlowBuilder::bold );
   }//End Constructor

}//End Class
