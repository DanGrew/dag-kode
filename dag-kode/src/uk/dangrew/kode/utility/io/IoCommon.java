/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.utility.io;

import java.io.InputStream;
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
   public final String readScannerContentAndClose( Scanner scanner ) {
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
   public final String readFileIntoString( Class< ? > locationClass, String filename ) {
      InputStream stream = locationClass.getResourceAsStream( filename );
      Scanner scanner = new Scanner( stream );
      return readScannerContentAndClose( scanner );
   }//End Method
   
}//End Class
