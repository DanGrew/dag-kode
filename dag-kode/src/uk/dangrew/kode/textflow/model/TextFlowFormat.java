package uk.dangrew.kode.textflow.model;

public class TextFlowFormat {

   private static final String PREFIX = "[";
   private static final String SUFFIX = "]";
   
   public String normalTag(){
      return wrap( "n" );
   }//End Method
   
   public String boldTag(){
      return wrap( "b" );
   }//End Method
   
   public String italicTag(){
      return wrap( "i" );
   }//End Method
   
   public String newLineTag(){
      return wrap( "nl" );
   }//End Method
   
   public String textColourTag(){
      return wrap( "t-c" );
   }//End Method
   
   public String flowSizeTag(){
      return wrap( "s" );
   }//End Method
   
   public String flowPaddingTag(){
      return wrap( "f-p" );
   }//End Method
   
   public String flowBackgroundTag(){
      return wrap( "f-bk" );
   }//End Method
   
   public String flowBorderTag(){
      return wrap( "f-bd" );
   }//End Method
   
   public String hyperlinkTag(){
      return wrap( "h" );
   }//End Method
   
   public String prefix(){
      return PREFIX;
   }//End Method
   
   public String suffix(){
      return SUFFIX;
   }//End Method
   
   public String wrap( String tag ) {
      return PREFIX + tag + SUFFIX;
   }//End Method
   
   public String wrap( Double tag ) {
      return PREFIX + tag + SUFFIX;
   }//End Method
   
}//End Class
