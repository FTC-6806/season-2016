package org.ratchetrobotics.logic;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.ratchetrobotics.logic.data.GamepadState;

/**
 * Created by liam on 12/18/15.
 */
public abstract class GamepadEventTrigger {
  Gamepad gamepad;
  GamepadState gamepadState;
  GamepadState oldGamepadState = null;

  public void attach(Gamepad g) {
    this.gamepad = g;
  }

  public void tick() {
    this.gamepadState = new GamepadState(gamepad);
    this.oldGamepadState = this.gamepadState;
  }

  abstract boolean shouldFire();
}
