package com.qualcomm.ftcrobotcontroller.opmodes;


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

    onButtonPress(gamepad2, "dpad_up", () -> {
      // extend tape measure
    });

    onButtonPress(gamepad2, "dpad_down", () -> {
      // retract tape-measure
    });
  }

  @Override
  public void loop() {
    tickWatchers();
  }
}
