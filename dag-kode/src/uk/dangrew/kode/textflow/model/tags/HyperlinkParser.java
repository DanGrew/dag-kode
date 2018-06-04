package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.friendly.javafx.FriendlyDesktop;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;

public class HyperlinkParser extends TextFormParser {

   static final FriendlyDesktop desktop = new FriendlyDesktop();
   
   public HyperlinkParser( TextFlowFormat format ) {
      super( 
               format, 
               TextFlowSchema.Hyperlink, 
               ( b, v ) -> b.withHyperlink( v, desktop )
      );
   }//End Constructor

}//End Class
