/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.utility.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Common test values and properties used.
 */
public class IoCommon {
   
   /**
    * Method to read the given {@link Scanner} and extract a {@link String}.
    * @param scanner the {@link Scanner} to read.
    * @return the {@link String} containing all text from the {@link Scanner}.
    */
   public String readScannerContentAndClose( Scanner scanner ) {
      String content = scanner.useDelimiter( "//Z" ).next();
      scanner.close();
      return content;
   }//End Method
   
   /**
    * Method to read a text file into a {@link String}.
    * @param locationClass the {@link Class} in the package to load the {@link File} from.
    * @param filename the name of the {@link File}.
    * @return the {@link String} containing all text from the {@link File}.
    */
   public String readFileIntoString( Class< ? > locationClass, String filename ) {
      InputStream stream = locationClass.getResourceAsStream( filename );
      Scanner scanner = new Scanner( stream );
      return readScannerContentAndClose( scanner );
   }//End Method

   /**
    * Method to read a text file into a {@link String}.
    * @param file the {@link File} to read from.
    * @return the {@link String} containing all text from the {@link File}.
    */
   public String readFileIntoString(File file){
      try {
         return new String( Files.readAllBytes( Paths.get(file.getAbsolutePath()) ) );
      } catch (IOException e) {
         return null;
      }
   }
   
}//End Class
