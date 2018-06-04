package uk.dangrew.kode.textflow.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import uk.dangrew.kode.javafx.style.JavaFxStyle;
import uk.dangrew.kode.textflow.model.TextFlowParser;

public class UiTextFlowParserWindow extends GridPane {
   
   private final TextFlowParser parser;
   
   private final TextArea inputArea;
   private final BorderPane textWrapper;
   
   public UiTextFlowParserWindow() {
      this( new TextFlowParser() );
   }//End Constructor
   
   UiTextFlowParserWindow( TextFlowParser parser ) {
      this.parser = parser;
      
      JavaFxStyle styling = new JavaFxStyle();
      styling.configureConstraintsForEvenColumns( this, 2 );
      styling.configureConstraintsForEvenRows( this, 1 );
      
      this.inputArea = new TextArea();
      this.inputArea.setWrapText( true );
      this.add( inputArea, 0, 0 );
      
      this.textWrapper = new BorderPane();
      ScrollPane scroller = styling.scrollPaneToFitFor( textWrapper );
      this.add( scroller, 1, 0 );
      
      this.inputArea.textProperty().addListener( ( s, o, n ) -> {
         this.parser.parse( n ).ifPresent( textWrapper::setCenter );
      } );
   }//End Constructor
   
   TextArea inputArea(){
      return inputArea;
   }//End Method
   
   BorderPane textWrapper(){
      return textWrapper;
   }//End Method

}//End Class
