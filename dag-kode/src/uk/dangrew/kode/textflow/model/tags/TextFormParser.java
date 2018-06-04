package uk.dangrew.kode.textflow.model.tags;

import java.util.function.BiConsumer;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;
import uk.dangrew.kode.utility.core.Strings;

public class TextFormParser implements TextFlowTagParser {

   private final Strings strings;
   private final String token;
   private final BiConsumer< TextFlowBuilder, String > builderFunction;
   
   protected TextFormParser( 
            TextFlowFormat format,
            TextFlowSchema schema,
            BiConsumer< TextFlowBuilder, String > builderFunction
   ) {
      this.token = schema.token( format );
      this.strings = new Strings();
      this.builderFunction = builderFunction;
   }//End Constructor
   
   @Override public String parse( 
            String input, 
            Integer positionOfNext,
            TextFlowBuilder builder 
   ) {
      String textValue = input;
      textValue = strings.removeAtStart( textValue, token );

      if ( positionOfNext != null ) {
         textValue = textValue.substring( 0, positionOfNext - token.length() );
      }
      
      builderFunction.accept( builder, textValue );
      return token + textValue;
   }//End Method
   
}//End Class
