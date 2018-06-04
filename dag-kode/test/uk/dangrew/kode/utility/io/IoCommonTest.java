/*
 * ----------------------------------------
 *           Json Upgrading and 
 *        Persistence Architecture
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.utility.io;

import java.io.StringReader;
import java.util.Scanner;

import org.junit.Test;

public class IoCommonTest {
   
   @Test( expected = IllegalStateException.class ) public void readingFromScannerShouldCloseScannerAferwards(){
      Scanner scanner = new Scanner( new StringReader( "anything" ) );
      new IoCommon().readScannerContentAndClose( scanner );
      scanner.next();
   }//End Method

}//End Class
