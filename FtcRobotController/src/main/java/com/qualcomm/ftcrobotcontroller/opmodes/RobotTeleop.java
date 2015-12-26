package com.qualcomm.ftcrobotcontroller.opmodes;

import org.ratchetrobotics.logic.HardwareConstants;
import org.ratchetrobotics.logic.RobotAbstractionLayer;

/**
 * Created by liam on 12/14/15.
 */
public class RobotTeleop extends RobotAbstractionLayer {
  boolean drivetrain_direction = false;

  @Override
  public void init() {
    initializeHardware();

    onButtonPress(gamepad1, "x", () -> {
      drivetrain_direction = !drivetrain_direction;
    });
  }

  @Override
  public void loop() {
    tickWatchers();

    // control winch extension
    if (gamepad2.dpad_up) {
      winchMotor.setPower(HardwareConstants.WINCH_SPEED);
    } else if (gamepad2.dpad_down) {
      winchMotor.setPower(-HardwareConstants.WINCH_SPEED);
    } else {
      winchMotor.setPower(0);
    }
  }
}
