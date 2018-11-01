package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.Conversions;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;
import uk.dangrew.kode.utility.core.Strings;

public abstract class NumberParser implements TextFlowTagParser {

   private final TextFlowFormat format;
   private final Conversions numberConverter;
   private final Strings strings;
   private final String token;
   
   protected NumberParser( 
            TextFlowFormat format,
            TextFlowSchema schema
   ) {
      this.format = format;
      this.numberConverter = new Conversions();
      this.token = schema.token( format );
      this.strings = new Strings();
   }//End Constructor
   
   protected String token(){
      return token;
   }//End Method
   
   protected TextFlowFormat format(){
      return format;
   }//End Method
   
   @Override public String parse( 
            String input, 
            Integer positionOfNext, 
            TextFlowBuilder builder 
   ) {
      String textValue = input;
      textValue = strings.removeAtStart( textValue, token );
      
      String numberValue = strings.findAtStart( format.prefix(), format.suffix(), textValue );
      Double number = numberConverter.nullableStringToDoubleFunction().apply( numberValue );
      return build( builder, numberValue, number );
   }//End Method
   
   protected abstract String build( 
            TextFlowBuilder builder, 
            String parsedValue, 
            Double number 
   );
   
}//End Class
