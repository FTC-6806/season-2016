package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.ratchetrobotics.algorithms.ai.ResponseCurve;
import org.ratchetrobotics.algorithms.utilities.JoystickScaler;

/**
 * Created by liam on 9/19/2015.
 */
public class TheodoreTeleop extends OpMode {
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

    drive_left = hardwareMap.dcMotor.get("left_drive");
    drive_right = hardwareMap.dcMotor.get("right_drive");

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

    drive_left.setPower(joystick_scaler.in(gamepad1.left_stick_y) * drive_direction_scaling);
    drive_right.setPower(joystick_scaler.in(gamepad1.right_stick_y) * drive_direction_scaling);

    mechanism_a.setPower(joystick_scaler.in(gamepad2.left_stick_y));
    mechanism_b.setPower(joystick_scaler.in(gamepad2.left_stick_y));

    telemetry.addData("01", "mech_a pf: " + mechanism_a.getPowerFloat() + "mech_b pf:" + mechanism_b.getPowerFloat());
  }
}
