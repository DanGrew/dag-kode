package uk.dangrew.kode.javafx.table.tools;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.BiConsumer;

import static org.mockito.Mockito.*;

public class TreeTableEditCommitHandlerTest {

   @Mock private BiConsumer< String, Double > consumer;
   private TreeTableEditCommitHandler< String, Double > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new TreeTableEditCommitHandler<>( consumer );
   }//End Method

   @Test public void shouldForwardCall() {
      CellEditEvent< String, Double > event = mock( CellEditEvent.class );
      String rowValue = "anything";
      Double newValue = 34587.0;
      
      when( event.getRowValue() ).thenReturn( new TreeItem<>( rowValue ) );
      when( event.getNewValue() ).thenReturn( newValue );
      systemUnderTest.handle( event );
      verify( consumer ).accept( rowValue, newValue );
   }//End Method

}//End Class
