package uk.dangrew.kode.settings.window;

import uk.dangrew.kode.settings.item.SettingsItem;
import uk.dangrew.kode.settings.item.SettingsRootItem;
import uk.dangrew.kode.settings.tree.SettingsController;
import uk.dangrew.kode.settings.tree.SettingsTreeItems;

public class DemonstrationTreeItems implements SettingsTreeItems {

   public DemonstrationTreeItems() {
   }//End Constructor
   
   @Override public void build( SettingsController controller, SettingsItem root ) {
      SettingsRootItem item = new SettingsRootItem();
      root.appendChild( item );
      item.select();
   }//End Method

}//End Class
