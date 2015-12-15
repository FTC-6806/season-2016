package com.qualcomm.ftcrobotcontroller.opmodes;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

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

  @Override
  public void init() {}

  @Override
  public void loop() {}

  public void initializeHardware() {
  }

  public void tickWatchers() {
    for (Watcher watcher:
         watchers) {
      Log.d("robotabstractionlayer", watcher.toString());
    }
  }
}
