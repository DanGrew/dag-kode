package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class NormalTextParser extends TextFormParser {

   public NormalTextParser( TextFlowFormat format ) {
      super( format, TextFlowSchema.Normal, TextFlowBuilder::normal );
   }//End Constructor

}//End Class
