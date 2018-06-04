package uk.dangrew.kode.textflow.model;

import java.util.function.Function;

import uk.dangrew.kode.textflow.model.tags.FlowBackgroundParser;
import uk.dangrew.kode.textflow.model.tags.BoldTextParser;
import uk.dangrew.kode.textflow.model.tags.FlowBorderParser;
import uk.dangrew.kode.textflow.model.tags.HyperlinkParser;
import uk.dangrew.kode.textflow.model.tags.ItalicTextParser;
import uk.dangrew.kode.textflow.model.tags.NewLineParser;
import uk.dangrew.kode.textflow.model.tags.NormalTextParser;
import uk.dangrew.kode.textflow.model.tags.FlowPaddingParser;
import uk.dangrew.kode.textflow.model.tags.FlowSizeParser;
import uk.dangrew.kode.textflow.model.tags.TextColourParser;
import uk.dangrew.kode.textflow.model.tags.TextFlowTagParser;

public enum TextFlowSchema {

   Normal( 
            TextFlowFormat::normalTag,
            NormalTextParser::new
   ),
   Bold( 
            TextFlowFormat::boldTag, 
            BoldTextParser::new 
   ),
   Italic(
            TextFlowFormat::italicTag,
            ItalicTextParser::new
   ),
   NewLine(
            TextFlowFormat::newLineTag,
            NewLineParser::new
   ),
   FlowBackground(
            TextFlowFormat::flowBackgroundTag,
            FlowBackgroundParser::new
   ),
   FlowBorder(
            TextFlowFormat::flowBorderTag,
            FlowBorderParser::new
   ),
   FlowPadding(
            TextFlowFormat::flowPaddingTag,
            FlowPaddingParser::new
   ),
   Hyperlink(
            TextFlowFormat::hyperlinkTag,
            HyperlinkParser::new
   ),
   TextColour(
            TextFlowFormat::textColourTag,
            TextColourParser::new
   ),
   FontSize(
            TextFlowFormat::flowSizeTag,
            FlowSizeParser::new
   );
   
   private final Function< TextFlowFormat, String > tokenRetriever;
   private final Function< TextFlowFormat, TextFlowTagParser > tagParserSupplier;
   
   private TextFlowSchema( 
            Function< TextFlowFormat, String > tokenRetriever,
            Function< TextFlowFormat, TextFlowTagParser > tagParserSupplier
   ) {
      this.tokenRetriever = tokenRetriever;
      this.tagParserSupplier = tagParserSupplier;;
   }//End Constructor

   public String token( TextFlowFormat format ) {
      if ( tokenRetriever == null ) return null;
      return tokenRetriever.apply( format );
   }//End Method
   
   public TextFlowTagParser parser( TextFlowFormat format ) {
      return tagParserSupplier.apply( format );
   }//End Method
   
}//End Enum
