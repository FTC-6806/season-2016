package org.ratchetrobotics.logic;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by liam on 12/14/15.
 */
public class GamepadWatcher implements Watcher {
  private GamepadEventCallback callback;
  private Gamepad gamepad;
  private GamepadEventSelector eventSelector;

  public enum EventType {
    PRESSED, // As soon as transitioned
    RELEASED, // As soon as transitioned
    HIT, // Pressed and then released (debounced)
    MOVED, // Moved the stick
  }

  public interface GamepadEventCallback {
    public void apply(GamepadEventSelector e, Gamepad g);
  }

  private interface GamepadEventSelector {}

  public static class ButtonEventSelector implements GamepadEventSelector {
    public String button;
    public EventType eventType;

    public ButtonEventSelector(String button, EventType eventType) {
      this.button = button;
      this.eventType = eventType;
    }
  }

  public GamepadWatcher(Gamepad gamepad, GamepadEventSelector eventSelector, GamepadEventCallback callback) {
    this.callback = callback;
    this.gamepad = gamepad;
    this.eventSelector = eventSelector;
  }

  public boolean didEventFire() {
    return false; // TODO: Implement
  }

  public void tick() {
    if (this.didEventFire()) {
      this.callback.apply(this.eventSelector, this.gamepad);
    }
  }

  public String toString() {
    return String.format("GamepadWatcher { gamepad.id: %d, event: %s }", this.gamepad.id, this.eventSelector.toString());
  }
}
