package uk.dangrew.kode.friendly.javafx;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class FriendlyDesktopTest {

   @Test public void shouldDirectHyperlinkToDesktop() throws IOException, URISyntaxException {
      FriendlyDesktop systemUnderTest = spy( new FriendlyDesktop() );
      Desktop actualDesktop = mock( Desktop.class );
      when( systemUnderTest.getDesktop() ).thenReturn( actualDesktop );
      
      systemUnderTest.browseToIconWebsite( "www.google.co.uk" );
      verify( actualDesktop ).browse( new URI( "www.google.co.uk" ) );
   }//End Method

}//End Class
