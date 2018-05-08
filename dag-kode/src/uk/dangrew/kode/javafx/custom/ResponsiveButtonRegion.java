package uk.dangrew.kode.javafx.custom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import uk.dangrew.kode.javafx.registrations.ButtonActionBindingImpl;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

public class ResponsiveButtonRegion implements ResponsiveRegionProperty {

   private final Button region;
   private final ButtonActionBindingImpl binding;
   
   public ResponsiveButtonRegion( Button button, EventHandler< ActionEvent > action ) {
      this.region = button;
      this.binding = new ButtonActionBindingImpl( button, action );
   }//End Constructor
   
   @Override public Region region() {
      return region;
   }//End Method

   @Override public RegistrationImpl registration() {
      return binding;
   }//End Method

}//End Class
