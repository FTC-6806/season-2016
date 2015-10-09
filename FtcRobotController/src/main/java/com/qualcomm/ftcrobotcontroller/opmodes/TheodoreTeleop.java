package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ratchetrobotics.algorithms.ai.ResponseCurve;
import org.ratchetrobotics.utilities.JoystickScaler;
// everything before this line IntelliJ writes out for you automatically as you use stuff.

/**
 * Created by liam on 9/19/2015.
 */
public class TheodoreTeleop extends OpMode { // declares a new opmode. IntelliJ does this automatically for you.
  // these are /kind of/ like the #pragmas in RobotC
  private DcMotorController drive_motor_controller;
  private DcMotorController mechanism_motor_controller;

  private DcMotor drive_left;
  private DcMotor drive_right;

  private DcMotor mechanism_a;
  private DcMotor mechanism_b;

  private ResponseCurve joystick_scaler;

  @Override
  public void init() {
    // set up the joystick scaler
    joystick_scaler = JoystickScaler.buildResponseCurve();

    // here we get a reference to the motor controller called "drive_controller" in the config file
    drive_motor_controller = hardwareMap.dcMotorController.get("drive_controller");
    // do the same for the auxiliary motor controller
    mechanism_motor_controller = hardwareMap.dcMotorController.get("mechanism_controller");

    // then let's get references to the motors "left_drive" and "right_drive"
    drive_left = hardwareMap.dcMotor.get("left_drive");
    drive_right = hardwareMap.dcMotor.get("right_drive");

    // also get references to "mech_a" and "mech_b"
    mechanism_a = hardwareMap.dcMotor.get("mech_a");
    mechanism_b = hardwareMap.dcMotor.get("mech_b");

    // reverse one of the motors (because it's a tank drive)
    drive_right.setDirection(DcMotor.Direction.REVERSE);

    // reverse one of the mechanism motors (because of the chain configuration on Alec's testing rig).
    // Valid as of Oct 6, 2015. DON'T RUN THIS OPMODE WITHOUT CHECKING THIS! YOU'LL PROBABLY BREAK SOMETHING EXPENSIVE!
    mechanism_b.setDirection(DcMotor.Direction.REVERSE);

    // set both of the drive motors to use a bit of PID
    drive_left.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    drive_right.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    // same with mechanism
    mechanism_a.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    mechanism_b.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
  }

  @Override
  public void loop() { // this gets run again and again and again
    // and every time around, we grab the stick value, negate them (because they come in with the wrong sign),
    // and then set the power of the drivetrain motors.
    drive_left.setPower(joystick_scaler.respond(gamepad1.left_stick_y));
    drive_right.setPower(joystick_scaler.respond(gamepad1.right_stick_y));

    mechanism_a.setPower(joystick_scaler.respond(gamepad2.left_stick_y));
    mechanism_b.setPower(joystick_scaler.respond(gamepad2.left_stick_y));
  }
}
