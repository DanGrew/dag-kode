/*
 * ----------------------------------------
 *          Jenkins Test Tracker
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2016
 * ----------------------------------------
 */
package uk.dangrew.kode.settings.window;

import uk.dangrew.kode.settings.item.SettingsItemType;

/**
 * {@link SettingsBehaviour} defines the parameters for the behavioural response of
 * the {@link SettingsController} to the event.
 */
public class SettingsBehaviour {

   private final WindowPolicy policy;
   private final SettingsItemType selection;
   
   public SettingsBehaviour( WindowPolicy policy, SettingsItemType selection ) {
      if ( policy == null ) {
         throw new IllegalArgumentException( "Must not provide null WindowPolicy." );
      }
      this.policy = policy;
      this.selection = selection;
   }//End Constructor

   public WindowPolicy getWindowPolicy() {
      return policy;
   }//End Method

   public SettingsItemType getSelection() {
      return selection;
   }//End Method

   @Override public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ( ( policy == null ) ? 0 : policy.hashCode() );
      result = prime * result + ( ( selection == null ) ? 0 : selection.hashCode() );
      return result;
   }//End Method

   @Override public boolean equals( Object obj ) {
      if ( this == obj ) {
         return true;
      }
      if ( obj == null ) {
         return false;
      }
      if ( !( obj instanceof SettingsBehaviour ) ) {
         return false;
      }
      SettingsBehaviour other = ( SettingsBehaviour ) obj;
      if ( policy != other.policy ) {
         return false;
      }
      if ( selection != other.selection ) {
         return false;
      }
      return true;
   }//End Method
   
}//End Class
