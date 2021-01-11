// *************************************************************
//                         H*E*R*M*E*S
//                   Holistic Environment for
//         Railway Modelling, Evaluation and Simulation
//        Built on Graffica System Development Kit: GSDK
//
//       Copyright: (c) Graffica Ltd (www.graffica.co.uk)
//
// This software is made available under binary licence. Holding 
// source code without the express permission of Graffica Ltd is 
//           not permitted under any circumstances. 
// *************************************************************

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
