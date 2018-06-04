package uk.dangrew.kode.textflow.model;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

import javafx.scene.text.TextFlow;
import javafx.util.Pair;
import uk.dangrew.kode.javafx.style.TextFlowBuilder;
import uk.dangrew.kode.utility.core.Strings;

public class TextFlowParser {

   private final Strings strings;
   
   private TextFlowFormat format;
   private TreeMap< Integer, TextFlowSchema > orderedItems;
   
   private TextFlowBuilder builder;
   private int parsingProgress;
   
   public TextFlowParser() {
      this.strings = new Strings();
      this.reset();
   }//End Constructor
   
   private void reset(){
      this.parsingProgress = 0;
      this.format = new TextFlowFormat();
      this.orderedItems = new TreeMap<>();
      this.builder = new TextFlowBuilder();
   }//End Method
   
   public Optional< TextFlow > parse( String text ) {
      this.reset();
      
      for ( TextFlowSchema schema : TextFlowSchema.values() ) {
         findNext( text, schema );
      }
      
      if ( orderedItems.isEmpty() ) {
         return Optional.empty();
      }
      
      while( !orderedItems.isEmpty() ) {
         text = parseNext( text );
         if ( text == null ) {
            return Optional.empty();
         }
      }
      return Optional.of( builder.build() );
   }//End Method
   
   private String parseNext( String text ) {
      String parsingText = text;
      
      Entry< Integer, TextFlowSchema > current = orderedItems.pollFirstEntry();
      if ( current == null ) {
         //nothing to parse, complete
         return null;
      }
      Entry< Integer, TextFlowSchema > next = orderedItems.firstEntry();
      
      parsingText = stripInvalidCharacters( parsingText, current.getKey() );
      Integer positionOfNext = next == null ? null : next.getKey() - parsingProgress;
      String removed = current.getValue().parser( format ).parse( 
               parsingText,
               positionOfNext,
               builder 
      );
      if ( !parsingText.contains( removed ) ) {
         //invalid parse, end process
         return null;
      }
      
      parsingText = progress( parsingText, removed );
      findNext( parsingText, current.getValue() );
      return parsingText;
   }//End Method
   
   private String stripInvalidCharacters( String input, Integer currentPosition ){
      String invalidCharacters = input.substring( 0, currentPosition - parsingProgress );
      return progress( input, invalidCharacters );
   }//End Method
   
   private String progress( String parseText, String valueToRemove ) {
      parsingProgress += valueToRemove.length();
      return strings.removeAtStart( parseText, valueToRemove );
   }//End Method
   
   private void findNext( String text, TextFlowSchema schema ) {
      String token = schema.token( format );
      if ( token == null ) {
         return;
      }
      int next = text.indexOf( token );
      if ( next == -1 ) {
         return;
      }
      orderedItems.put( next + parsingProgress, schema );
   }//End Method
   
}//End Class
