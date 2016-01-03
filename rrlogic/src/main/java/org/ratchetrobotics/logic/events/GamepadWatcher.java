package org.ratchetrobotics.logic.events;

import android.util.Log;

import org.ratchetrobotics.logic.data.GamepadState;

/**
 * Created by liam on 12/14/15.
 */
public class GamepadWatcher implements Watcher {
  private GamepadEventCallback callback;
  private GamepadState gamepad;
  private GamepadEventTrigger eventTrigger;


  public interface GamepadEventCallback {
    void apply();
  }

  public GamepadWatcher(GamepadState gamepad, GamepadEventTrigger eventTrigger, GamepadEventCallback callback) {
    this.callback = callback;
    this.gamepad = gamepad;
    this.eventTrigger = eventTrigger; this.eventTrigger.attach(gamepad);
  }

  public void tick() {
    Log.d("event-layer", "x=" + gamepad.x);
    this.eventTrigger.tick();
    if (this.eventTrigger.shouldFire()) {
      this.callback.apply();
    }
  }

  public String toString() {
    return String.format("GamepadWatcher { gamepad.id: %d, event: %s }", "?", this.eventTrigger.toString());
  }
}
