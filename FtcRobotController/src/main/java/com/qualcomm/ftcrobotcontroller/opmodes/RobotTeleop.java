package com.qualcomm.ftcrobotcontroller.opmodes;

import org.ratchetrobotics.algorithms.utilities.JoystickScaler;
import org.ratchetrobotics.logic.RobotAbstractionLayer;

/**
 * Created by liam on 12/14/15.
 */
public class RobotTeleop extends RobotAbstractionLayer {
  boolean drivetrain_direction = true;
  JoystickScaler joystickScaler = new JoystickScaler();
  RePassTest rePassTest;

  @Override
  public void init() {
    initializeHardware();

//    onButtonPress(gamepad1, "x", () -> {
//      drivetrain_direction = !drivetrain_direction;
//    });
  }

  @Override
  public void start() {
    rePassTest = new RePassTest(gamepad1);
  }

  @Override
  public void loop() {
//    tickWatchers();
    rePassTest.tick();

//    Log.d("repasstest", "x = " + gamepad1.x);
//    driveLeftMotors.setPower(joystickScaler.in(gamepad1.left_stick_y));
//    driveRightMotors.setPower(joystickScaler.in(gamepad1.right_stick_y));
    drivetrain.setPower(joystickScaler.in(gamepad1.left_stick_y), joystickScaler.in(gamepad1.right_stick_y), drivetrain_direction);
  }
}
