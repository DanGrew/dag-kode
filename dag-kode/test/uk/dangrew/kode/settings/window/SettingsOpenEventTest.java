/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.window;

import uk.dangrew.kode.event.structure.AbstractEventManagerTest;
import uk.dangrew.kode.event.structure.EventManager;

public class SettingsOpenEventTest extends AbstractEventManagerTest< SettingsBehaviour > {

   @Override protected EventManager< SettingsBehaviour > constructSut() {
      return new SettingsOpenEvent();
   }//End Method
}//End Class
