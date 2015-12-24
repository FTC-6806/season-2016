package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import org.ratchetrobotics.logic.ButtonEventTrigger;
import org.ratchetrobotics.logic.GamepadWatcher;

/**
 * Created by liam on 12/14/15.
 */
public class RobotTeleop extends RobotAbstractionLayer {
  boolean drivetrain_direction = false;

  @Override
  public void init() {
    initializeHardware();

    watchers.add(new GamepadWatcher(gamepad1, new ButtonEventTrigger("a", ButtonEventTrigger.EventType.PRESSED), () -> {
      Log.d("rr-main", "Button 'A' was pressed on gamepad1");
    }));
  }

  @Override
  public void loop() {
    tickWatchers();
  }
}
