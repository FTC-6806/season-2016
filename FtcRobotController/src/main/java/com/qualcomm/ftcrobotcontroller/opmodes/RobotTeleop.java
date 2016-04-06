package com.qualcomm.ftcrobotcontroller.opmodes;

import com.lasarobotics.library.controller.ButtonState;
import com.lasarobotics.library.controller.Controller;

import org.ratchetrobotics.algorithms.utilities.JoystickScaler;
import org.ratchetrobotics.logic.RobotAbstractionLayer;

/**
 * Created by liam on 12/14/15.
 */
public class RobotTeleop extends RobotAbstractionLayer {
  Controller controller1, controller2;
  JoystickScaler joystickScaler = new JoystickScaler();

  boolean drivetrain_direction = true;

  @Override
  public void init() {
    initializeHardware();

    controller1 = new Controller(gamepad1);
    controller2 = new Controller(gamepad2);
  }

  @Override
  public void loop() {
    controller1.update(gamepad1); controller2.update(gamepad2);

    if (controller1.x == ButtonState.PRESSED || controller2.x == ButtonState.PRESSED) {
      drivetrain_direction = !drivetrain_direction;
    }

    drivetrain.setPower(joystickScaler.in(gamepad1.left_stick_y), joystickScaler.in(gamepad1.right_stick_y), drivetrain_direction);
  }
}
