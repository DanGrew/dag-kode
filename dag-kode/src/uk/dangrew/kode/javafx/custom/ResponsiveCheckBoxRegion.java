package uk.dangrew.kode.javafx.custom;

import java.util.Optional;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Region;
import uk.dangrew.kode.javafx.registrations.ChangeListenerBindingImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

public class ResponsiveCheckBoxRegion implements ResponsiveRegionProperty {

   private final CheckBox region;
   private final RegistrationImpl binding;
   
   public ResponsiveCheckBoxRegion( CheckBox checkBox, ObjectProperty< Boolean > property ) {
      this.region = checkBox;
      this.region.selectedProperty().set( Optional.ofNullable( property.get() ).orElse( false ) );
      this.binding = new ChangeListenerBindingImpl<>( region.selectedProperty().asObject(), property );
   }//End Constructor
   
   @Override public Region region() {
      return region;
   }//End Method

   @Override public RegistrationImpl registration() {
      return binding;
   }//End Method

}//End Class
