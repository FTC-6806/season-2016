package org.ratchetrobotics.logic;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.ratchetrobotics.logic.drivetrain.TankDrivetrain;
import org.ratchetrobotics.logic.events.ButtonEventTrigger;
import org.ratchetrobotics.logic.events.GamepadWatcher;
import org.ratchetrobotics.logic.events.Watcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liam on 12/14/15.
 */
public class RobotAbstractionLayer extends OpMode {
  @Override
  public void init() {}

  @Override
  public void loop() {}

  protected TankDrivetrain drivetrain;

  protected DcMotor winchMotor;
  protected Servo winchRatchetServo, winchAimingServo;
  protected DcMotor driveLeftMotors, driveRightMotors;

  protected List<Watcher> watchers = new ArrayList<>();

  public void onButtonPress(Gamepad gamepad, String button, GamepadWatcher.GamepadEventCallback callback) {
    watchers.add(new GamepadWatcher(gamepad, new ButtonEventTrigger(button, ButtonEventTrigger.EventType.PRESSED), callback));
  }

  public void onButtonRelease(Gamepad gamepad, String button, GamepadWatcher.GamepadEventCallback callback) {
    watchers.add(new GamepadWatcher(gamepad, new ButtonEventTrigger(button, ButtonEventTrigger.EventType.RELEASED), callback));
  }


  public void initializeHardware() {
//    winchMotor = hardwareMap.dcMotor.get("winchMotor");
//    winchRatchetServo = hardwareMap.servo.get("winchRatchetServo");
//    winchAimingServo = hardwareMap.servo.get("winchAimingServo");

    driveLeftMotors = hardwareMap.dcMotor.get("driveLeftMotors");
    driveRightMotors = hardwareMap.dcMotor.get("driveRightMotors"); driveRightMotors.setDirection(DcMotor.Direction.REVERSE);

    drivetrain = new TankDrivetrain(driveLeftMotors, driveRightMotors);
  }

  public void tickWatchers() {
    for (Watcher watcher:
         watchers) {
      watcher.tick();
    }
  }
}
