package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.ratchetrobotics.logic.ButtonEventTrigger;
import org.ratchetrobotics.logic.GamepadWatcher;
import org.ratchetrobotics.logic.Watcher;

import java.util.List;

/**
 * Created by liam on 12/14/15.
 */
public class RobotAbstractionLayer extends OpMode {
  protected List<Watcher> watchers;

  public RobotAbstractionLayer() {
    super();
  }

  public void onButtonPress(Gamepad gamepad, String button, GamepadWatcher.GamepadEventCallback callback) {
    watchers.add(new GamepadWatcher(gamepad, new ButtonEventTrigger(button, ButtonEventTrigger.EventType.PRESSED), callback));
  }

  @Override
  public void init() {}

  @Override
  public void loop() {}

  public void initializeHardware() {
  }

  public void tickWatchers() {
    for (Watcher watcher:
         watchers) {
      watcher.tick();
      Log.d("robotabstractionlayer", watcher.toString());
    }
  }
}
