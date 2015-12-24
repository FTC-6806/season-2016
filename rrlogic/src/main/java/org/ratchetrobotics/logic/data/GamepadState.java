package org.ratchetrobotics.logic.data;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by liam on 12/16/15.
 */

public class GamepadState {
  public float stick_l_x, stick_l_y,
               stick_r_x, stick_r_y;
  public boolean dpad_up, dpad_down, dpad_right, dpad_left;
  public boolean a, b, x, y;
  public boolean guide, start, back;
  public boolean bumper_l, bumper_r;
  public boolean stick_l_button, stick_r_button;
  public boolean right_stick_button;
  public float trigger_l, trigger_r;
  public static void mirrorGamepadState(Gamepad gamepad) {
  }
}
