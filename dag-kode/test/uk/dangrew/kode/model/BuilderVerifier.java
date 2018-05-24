package uk.dangrew.kode.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class BuilderVerifier< SutT > {

   public < VariableT > BuilderVerifier< SutT > build( 
            Consumer< VariableT > setter, 
            Supplier< VariableT > getter, 
            VariableT sample 
   ) {
      setter.accept( sample );
      assertThat( getter.get(), is( sample ) );
      return this;
   }//End Method
   
}//End Class
