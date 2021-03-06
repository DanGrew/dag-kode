package uk.dangrew.kode.javafx.table.tools;

import javafx.scene.control.TableColumn.CellEditEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.function.BiConsumer;

import static org.mockito.Mockito.*;

public class TableViewEditCommitHandlerTest {

   @Mock private BiConsumer< String, Double > consumer;
   private TableViewEditCommitHandler< String, Double > systemUnderTest;

   @Before public void initialiseSystemUnderTest() {
      MockitoAnnotations.initMocks( this );
      systemUnderTest = new TableViewEditCommitHandler<>( consumer );
   }//End Method

   @Test public void shouldForwardCall() {
      CellEditEvent< String, Double > event = mock( CellEditEvent.class );
      String rowValue = "anything";
      Double newValue = 34587.0;
      
      when( event.getRowValue() ).thenReturn( rowValue );
      when( event.getNewValue() ).thenReturn( newValue );
      systemUnderTest.handle( event );
      verify( consumer ).accept( rowValue, newValue );
   }//End Method

}//End Class
