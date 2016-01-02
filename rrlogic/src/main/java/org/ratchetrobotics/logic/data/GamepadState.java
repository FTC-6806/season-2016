package org.ratchetrobotics.logic.data;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by liam on 12/16/15.
 */

public class GamepadState {
  public float left_stick_x, left_stick_y,
    right_stick_x, right_stick_y;
  public boolean dpad_up, dpad_down, dpad_right, dpad_left;
  public boolean a, b, x, y;
  public boolean guide, start, back;
  public boolean left_bumper, right_bumper;
  public boolean left_stick_button, right_stick_button;
  public float left_trigger, right_trigger;

  /**
   * Copy gamepad state from a Gamepad object into a new GamepadState
   *
   * @param gamepad a Qualcomm Gamepad class.
   */
  public GamepadState(Gamepad gamepad) {
    this.left_stick_x = gamepad.left_stick_x;
    this.left_stick_y = gamepad.left_stick_y;
    this.right_stick_x = gamepad.right_stick_x;
    this.right_stick_y = gamepad.right_stick_y;

    this.dpad_up = gamepad.dpad_up; this.dpad_down = gamepad.dpad_down;
    this.dpad_left = gamepad.dpad_left; this.dpad_right = gamepad.dpad_right;

    this.a = gamepad.a; this.b = gamepad.b;
    this.x = gamepad.x; this.y = gamepad.y;

    this.guide = gamepad.guide; this.start = gamepad.start;
    this.back = gamepad.back;

    this.left_bumper = gamepad.left_bumper; this.right_bumper = gamepad.right_bumper;
    this.left_stick_button = gamepad.left_stick_button; this.right_stick_button = gamepad.right_stick_button;

    this.left_trigger = gamepad.left_trigger; this.right_trigger = gamepad.right_trigger;
  }
}
