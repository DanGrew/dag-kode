package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class ItalicTextParser extends TextFormParser {

   public ItalicTextParser( TextFlowFormat format ) {
      super( format, TextFlowSchema.Italic, TextFlowBuilder::italic );
   }//End Constructor

}//End Class
