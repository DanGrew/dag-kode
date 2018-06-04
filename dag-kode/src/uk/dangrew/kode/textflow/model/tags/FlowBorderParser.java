package uk.dangrew.kode.textflow.model.tags;

import javafx.scene.paint.Color;
import uk.dangrew.kode.javafx.style.Conversions;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.textflow.model.TextFlowFormat;
import uk.dangrew.kode.textflow.model.TextFlowSchema;
import uk.dangrew.kode.utility.conversion.ColorConverter;
import uk.dangrew.kode.utility.core.Strings;

public class FlowBorderParser implements TextFlowTagParser {

   private final TextFlowFormat format;
   private final Strings strings;
   private final Conversions numberConverter;
   private final ColorConverter colours;
   private final String token;
   
   public FlowBorderParser( TextFlowFormat format ) {
      this.format = format;
      this.colours = new ColorConverter();
      this.numberConverter = new Conversions();
      this.token = TextFlowSchema.FlowBorder.token( format );
      this.strings = new Strings();
   }//End Constructor
   
   public String parse( 
            String input, 
            Integer positionOfNext, 
            TextFlowBuilder builder 
   ) {
      String textValue = input;
      textValue = strings.removeAtStart( textValue, token );
      
      String firstValue = strings.findAtStart( format.prefix(), format.suffix(), textValue );
      if ( firstValue == null ) {
         return build( builder, null, null, null, null );
      }
      
      textValue = strings.removeAtStart( textValue, format.wrap( firstValue ) );
      String secondValue = strings.findAtStart( format.prefix(), format.suffix(), textValue );
      
      Color colour = colours.nameToColor( firstValue );
      if ( colour == null ) {
         colour = colours.hexToColor( firstValue );
      }
      
      Double number = null;
      if ( colour == null ) {
         number = numberConverter.nullableStringToDoubleFunction().apply( firstValue );
      }
      if ( number == null ) {
         number = numberConverter.nullableStringToDoubleFunction().apply( secondValue );
      }
      
      return build( builder, firstValue, secondValue, colour, number );
   }//End Method
   
   private String build( 
            TextFlowBuilder builder,
            String parsedFirst,
            String parsedSecond,
            Color colour,
            Double thickness
   ) {
      if ( colour == null && thickness == null ) {
         builder.resetFlowBorder();
         return token;
      } else if ( colour == null ) {
         builder.withFlowBorder( thickness );
         return token + format.wrap( parsedFirst );
      } else if ( thickness == null ) {
         builder.withFlowBorder( colour );
         return token + format.wrap( parsedFirst );
      } else {
         builder.withFlowBorder( colour, thickness );
         return token + format.wrap( parsedFirst ) + format.wrap( parsedSecond );
      }
   }//End Method
   
}//End Class
