package uk.dangrew.kode.utility.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

   private static final String EMPTY = "";
   
   public String replaceAtStart( String input, String toReplace, String replacement ) {
      if ( !input.startsWith( toReplace ) ) {
         return input;
      }
      return input.replaceFirst( Pattern.quote( toReplace ), replacement );
   }//End Method
   
   public String removeAtStart( String input, String toReplace ) {
      if ( !input.startsWith( toReplace ) ) {
         return input;
      }
      return input.replaceFirst( Pattern.quote( toReplace ), EMPTY );
   }//End Method
   
   public String findAtStart( String prefix, String suffix, String input ) {
      if ( !input.startsWith( prefix ) ) {
         return null;
      }
      String patternedPrefix = Pattern.quote( prefix );
      String patternedSuffix = Pattern.quote( suffix );
      
      Matcher matcher = Pattern.compile( 
               patternedPrefix + "[^\\" + prefix + "\\" + suffix + "]*" +  patternedSuffix
      ).matcher( input );
      if ( matcher.find() ) {
         return matcher.group()
                  .replaceFirst( patternedPrefix, EMPTY )
                  .replaceFirst( patternedSuffix, EMPTY );
      }
      
      return null;
   }//End Method
   
}//End Class
