package org.ratchetrobotics.utilities;

import org.ratchetrobotics.algorithms.ai.ResponseCurve;

/**
 * Created by liam on 10/8/15.
 */
public class JoystickScaler {
  public static ResponseCurve buildResponseCurve() {
    return new ResponseCurve(new double[]{0.0, 0.25, 0.75, 1.0},
      new double[]{0.0, 0.3, 0.8, 1});
  }
}
