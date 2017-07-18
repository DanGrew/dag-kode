/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.combobox;

import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.beans.property.ObjectProperty;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import uk.dangrew.kode.styling.FontFamilies;

/**
 * {@link FontFamilyPropertyBox} provides a {@link PropertyBox} that configures the
 * {@link Font} family of a {@link Font} {@link ObjectProperty}.
 */
public class FontFamilyPropertyBox extends PropertyBox< String, Font >{

   /** {@link BiFunction} for converting a family into a new {@link Font} accounting for the original size. **/
   private static final BiFunction< Font, String, Font > familyToModifiedFontFunction = ( originalFont, family ) -> {
            if ( originalFont == null ) return Font.font( family, FontWeight.BOLD, 13 );
            return Font.font( family, FontWeight.BOLD, originalFont.getSize() );
   };
   
   /** {@link Function} to convert a {@link Font} into the {@link Font} family to display.**/
   private static final Function< Font, String > fontToFamilyFunction = font -> {
      if ( font == null ) return Font.getDefault().getFamily();
      return font.getFamily(); 
   };
   
   /**
    * Constructs a new {@link FontFamilyPropertyBox}.
    * @param property the {@link ObjectProperty} containing the {@link Font}.
    */
   public FontFamilyPropertyBox( ObjectProperty< Font > property ) {
      getItems().addAll( FontFamilies.getUsableFontFamilies() );
      
      bindProperty( 
               property, 
               family -> familyToModifiedFontFunction.apply( property.get(), family ), 
               fontToFamilyFunction
      );
   }//End Constructor
   
}//End Class
