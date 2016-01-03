package org.ratchetrobotics.logic.events;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.ratchetrobotics.logic.data.GamepadState;

/**
 * Created by liam on 12/18/15.
 */
public abstract class GamepadEventTrigger {
  GamepadState gamepad;
  GamepadState gamepadState;
  GamepadState oldGamepadState = null;

  public void attach(GamepadState g) {
    this.gamepad = g;
  }

  public void tick() {
    this.gamepadState = new GamepadState(gamepad);
    this.oldGamepadState = new GamepadState(gamepadState);
  }

  abstract boolean shouldFire();
}
