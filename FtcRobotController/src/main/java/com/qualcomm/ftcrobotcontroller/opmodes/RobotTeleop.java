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
  }

  @Override
  public void loop() {
    tickWatchers();
  }
}
