package uk.dangrew.kode.textflow.model.tags;

import uk.dangrew.kode.javafx.style.TextFlowBuilder;

public interface TextFlowTagParser {

   public String parse( 
            String textValue, 
            Integer positionOfNext, 
            TextFlowBuilder builder 
   );

}//End Interface

