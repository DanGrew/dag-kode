package uk.dangrew.kode.textflow.model.tags;

import javafx.scene.paint.Color;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;
import uk.dangrew.kode.utility.conversion.ColorConverter;
import uk.dangrew.kode.utility.core.Strings;

public abstract class ColourParser implements TextFlowTagParser {

   private final TextFlowFormat format;
   private final Strings strings;
   private final ColorConverter colours;
   private final String token;
   
   protected ColourParser( TextFlowFormat format, TextFlowSchema schema ) {
      this.format = format;
      this.colours = new ColorConverter();
      this.token = schema.token( format );
      this.strings = new Strings();
   }//End Constructor
   
   protected TextFlowFormat format(){
      return format;
   }//End Method
   
   protected String token(){
      return token;
   }//End Method
   
   @Override public String parse( 
            String input, 
            Integer positionOfNext, 
            TextFlowBuilder builder 
   ) {
      String textValue = input;
      textValue = strings.removeAtStart( textValue, token );
      
      String colourValue = strings.findAtStart( format.prefix(), format.suffix(), textValue );
      Color colour = colours.nameToColor( colourValue );
      if ( colour == null ) {
         colour = colours.hexToColor( colourValue );
      }
      return build( builder, colourValue, colour );
   }//End Method
   
   protected abstract String build( 
            TextFlowBuilder builder,
            String parsedValue,
            Color colour
   );
   
}//End Class
