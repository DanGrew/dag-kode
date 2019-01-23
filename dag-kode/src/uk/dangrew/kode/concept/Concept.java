package uk.dangrew.kode.concept;

public interface Concept {

   /**
    * Access to the {@link Properties} associated.
    * @return the {@link Properties}.
    */
   public Properties properties();
   
   /**
    * Method to duplicate the {@link Concept}.
    * @return the duplicated {@link Concept}.
    */
   public Concept duplicate();
   
}//End Interface

