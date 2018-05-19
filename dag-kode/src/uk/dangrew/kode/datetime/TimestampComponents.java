package uk.dangrew.kode.datetime;

import java.time.LocalDateTime;
import java.util.OptionalInt;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Stream;

public enum TimestampComponents {

   Day(
            t -> t.plusDays( 1 ),
            t -> t.minusDays( 1 ),
            0, 1, 2 
   ),
   Month( 
            t -> t.plusMonths( 1 ),
            t -> t.minusMonths( 1 ),
            3, 4, 5 
   ),
   Year( 
            t -> t.plusYears( 1 ),
            t -> t.minusYears( 1 ),
            6, 7, 8, 9, 10 ),
   Separator( 
            null,
            null
   ),
   Hour( 
            t -> t.plusHours( 1 ),
            t -> t.minusHours( 1 ),
            11, 12, 13 
   ),
   Minute( 
            t -> t.plusMinutes( 1 ),
            t -> t.minusMinutes( 1 ),
            14, 15, 16 
   ),
   Second( 
            t -> t.plusSeconds( 1 ),
            t -> t.minusSeconds( 1 ),
            17, 18, 19 
   );
   
   private final TreeSet< Integer > caretPositions;
   private final Function< LocalDateTime, LocalDateTime > incrementFunction;
   private final Function< LocalDateTime, LocalDateTime > decrementFunction;
   
   private TimestampComponents( 
            Function< LocalDateTime, LocalDateTime > incrementFunction,
            Function< LocalDateTime, LocalDateTime > decrementFunction,
            Integer... caretPositions 
   ) {
      this.caretPositions = new TreeSet<>();
      this.incrementFunction = incrementFunction;
      this.decrementFunction = decrementFunction;
      Stream.of( caretPositions ).forEach( this.caretPositions::add );
   }//End Constructor
   
   public boolean caretHighlightsComponent( int caret ) {
      return caretPositions.contains( caret );
   }//End Method
   
   public LocalDateTime increment( LocalDateTime timestamp ) {
      if ( incrementFunction == null ) {
         return timestamp;
      }
      return incrementFunction.apply( timestamp );
   }//End Method
   
   public LocalDateTime decrement( LocalDateTime timestamp ) {
      if ( decrementFunction == null ) {
         return timestamp;
      }
      return decrementFunction.apply( timestamp );
   }//End Method
   
   public OptionalInt startingCaretPosition(){
      if ( caretPositions.isEmpty() ) {
         return OptionalInt.empty();
      }
      return OptionalInt.of( caretPositions.first() );
   }//End Method
   
   public static TimestampComponents findComponent( int caretPosition ) {
      return Stream.of( TimestampComponents.values() )
               .filter( c -> c.caretHighlightsComponent( caretPosition )  )
               .findFirst()
               .orElse( null );
   }//End Method
   
}//End Enum
