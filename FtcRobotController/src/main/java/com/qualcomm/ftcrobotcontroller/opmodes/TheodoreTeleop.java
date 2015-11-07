package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ratchetrobotics.algorithms.ai.ResponseCurve;
import org.ratchetrobotics.algorithms.utilities.JoystickScaler;
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

  private JoystickScaler joystick_scaler;

  private boolean driveDirectionButtonCurrentState;
  private boolean driveDirectionButtonLastState;
  private int drive_direction_scaling = 1;

  @Override
  public void init() {
    // set up the joystick scaler
    joystick_scaler = new JoystickScaler();

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

    // set both of the drive motors to use a bit of PID
    drive_left.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
    drive_right.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    // same with mechanism
    mechanism_a.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
    mechanism_b.setChannelMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
  }

  @Override
  public void loop() {
    driveDirectionButtonCurrentState = gamepad1.x || gamepad2.x;
    if (driveDirectionButtonCurrentState == true && driveDirectionButtonCurrentState != driveDirectionButtonLastState) {
      drive_direction_scaling *= -1;
    }
    // this gets run again and again and again
    // and every time around, we grab the stick value,
    // and then set the power of the drivetrain motors.
    drive_left.setPower(joystick_scaler.in(gamepad1.left_stick_y) * drive_direction_scaling);
    drive_right.setPower(joystick_scaler.in(gamepad1.right_stick_y) * drive_direction_scaling);

    mechanism_a.setPower(joystick_scaler.in(gamepad2.left_stick_y));
    mechanism_b.setPower(joystick_scaler.in(gamepad2.left_stick_y));

    telemetry.addData("01", "mech_a pf: " + mechanism_a.getPowerFloat() + "mech_b pf:" + mechanism_b.getPowerFloat());
  }
}
