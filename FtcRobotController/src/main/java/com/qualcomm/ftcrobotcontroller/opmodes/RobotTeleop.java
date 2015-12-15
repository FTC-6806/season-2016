package com.qualcomm.ftcrobotcontroller.opmodes;


import org.ratchetrobotics.logic.GamepadWatcher;
import org.ratchetrobotics.logic.GamepadWatcher.ButtonEventSelector;


/**
 * Created by liam on 12/14/15.
 */
public class RobotTeleop extends RobotAbstractionLayer {
  boolean a_pressed = false;

  @Override
  public void init() {
    initializeHardware();
    watchers.add(new GamepadWatcher(gamepad1, new ButtonEventSelector("a", GamepadWatcher.EventType.PRESSED), (e, g) -> {
      this.a_pressed = true;
    }));
  }

  @Override
  public void loop() {
    tickWatchers();
  }
}
