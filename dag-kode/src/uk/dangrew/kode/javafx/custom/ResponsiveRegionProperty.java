/*
 * ----------------------------------------
 *       Dan Grew - Kit of Development
 * ----------------------------------------
 *          Produced by Dan Grew
 *                 2017
 * ----------------------------------------
 */
package uk.dangrew.kode.javafx.custom;

import javafx.scene.layout.Region;
import uk.dangrew.kode.javafx.registrations.RegistrationImpl;

/**
 * {@link ResponsiveNodeProperty} provides an interface for responding to a {@link Region}
 * where a {@link RegistrationImpl} is applied and managed using a builder type interface.
 */
public interface ResponsiveRegionProperty {

   /**
    * Provides the {@link Region} being applied and used for configuration.
    * @return the {@link Region}.
    */
   public Region region();

   /**
    * Provides the {@link RegistrationImpl} for the {@link Region} changes, not applied.
    * @return the {@link RegistrationImpl}.
    */
   public RegistrationImpl registration();

}//End Interface
