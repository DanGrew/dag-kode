package uk.dangrew.kode.model;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;
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
   
   public BuilderVerifier< SutT > build( 
            Runnable setter, 
            Supplier< Boolean > getter, 
            boolean expected
   ) {
      setter.run();
      assertThat( getter.get(), is( expected ) );
      return this;
   }//End Method
   
   public < VariableT > BuilderVerifier< SutT > buildWithOptional( 
            Consumer< VariableT > setter, 
            Supplier< Optional< VariableT > > getter, 
            VariableT sample 
   ) {
      setter.accept( sample );
      assertThat( getter.get().get(), is( sample ) );
      return this;
   }//End Method
   
}//End Class
