package org.ratchetrobotics.logic.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by liam on 12/29/15.
 */
public class TankDrivetrain {
  private enum TankMotorConfiguration {
    TWO_MOTOR,
    SIX_MOTOR
  }

  DcMotor leftMotor1, leftMotor2, rightMotor1, rightMotor2;
  TankMotorConfiguration motorConfiguration;

  public TankDrivetrain(DcMotor leftMotor, DcMotor rightMotor) {
    motorConfiguration = TankMotorConfiguration.TWO_MOTOR;
    this.leftMotor1 = leftMotor; this.rightMotor1 = rightMotor;
  }

  public TankDrivetrain(DcMotor leftMotor1, DcMotor leftMotor2, DcMotor rightMotor1, DcMotor rightMotor2) {
    motorConfiguration = TankMotorConfiguration.SIX_MOTOR;
    this.leftMotor1 = leftMotor1; this.leftMotor2 = leftMotor2;
    this.leftMotor2 = leftMotor2; this.rightMotor2 = leftMotor2;
  }

  public void setPower(double leftPower, double rightPower) {
    switch (motorConfiguration) {
      case TWO_MOTOR:
        this.leftMotor1.setPower(leftPower);
        this.rightMotor1.setPower(rightPower);
        break;
      case SIX_MOTOR:
        this.leftMotor1.setPower(leftPower);
        this.leftMotor2.setPower(leftPower);
        this.rightMotor1.setPower(rightPower);
        this.rightMotor2.setPower(rightPower);
        break;
    }
  }

  public void setPower(double leftPower, double rightPower, boolean direction) {
    setPower(leftPower * (direction ? 1 : -1), leftPower * (direction ? 1 : -1));
  }
}
