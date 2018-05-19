package uk.dangrew.kode.javafx.custom;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import uk.dangrew.kode.javafx.registrations.ChangeListenerRegistrationImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

public class ResponsiveTextRegion implements ResponsiveRegionProperty {

   private final TextField region;
   private final ChangeListenerRegistrationImpl< String > binding;
   
   public ResponsiveTextRegion( TextField region, ChangeListener< String > listener ) {
      this.region = region;
      this.binding = new ChangeListenerRegistrationImpl<>( region.textProperty(), listener );
   }//End Constructor
   
   @Override public Region region() {
      return region;
   }//End Method

   @Override public RegistrationImpl registration() {
      return binding;
   }//End Method

}//End Class
