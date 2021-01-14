

package uk.dangrew.kode.javafx.controls;

import javafx.scene.input.KeyCode;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DirectionControlTypeTest {
   
   @Test
   public void shouldMapDirectionKeys() {
      assertThat(DirectionControlType.of(KeyCode.RIGHT).get(), equalTo(DirectionControlType.Right));
      assertThat(DirectionControlType.of(KeyCode.LEFT).get(), equalTo(DirectionControlType.Left));
      assertThat(DirectionControlType.of(KeyCode.UP).get(), equalTo(DirectionControlType.Up));
      assertThat(DirectionControlType.of(KeyCode.DOWN).get(), equalTo(DirectionControlType.Down));
   }
}
