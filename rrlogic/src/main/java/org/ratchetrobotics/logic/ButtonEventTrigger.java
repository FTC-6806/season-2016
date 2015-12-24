package org.ratchetrobotics.logic;

import org.ratchetrobotics.logic.data.GamepadState;

import java.lang.reflect.Field;

/**
 * Created by liam on 12/18/15.
 */
public class ButtonEventTrigger extends GamepadEventTrigger {
  String button;
  GamepadWatcher.EventType eventType;

  private static final float FBUTTON_THRESHOLD = 0.75F; // range is [0..1], unpressed to fully-pressed

  public ButtonEventTrigger(String button, GamepadWatcher.EventType eventType) {
    this.button = button;
    this.eventType = eventType;
  }

  private boolean reflectButton(GamepadState gs, String button) {
    boolean buttonState = false;
    try {
      Field buttonField = gs.getClass().getDeclaredField(button);
      if (buttonField.getType().equals(Boolean.TYPE)) {
        // we have a boolean button. we can just dump out the value
        buttonState = buttonField.getBoolean(gs);
      } else if (buttonField.getType().equals(Float.TYPE)) {
        // we have a float button. what this means is that the button has gradations of depression.
        // we take a threshold and compare the button to it to get the bool to return.
        // TODO: this is kind of a hack. Find a better solution?
        buttonState = (buttonField.getFloat(gs) > FBUTTON_THRESHOLD);
      }
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return buttonState;
  }

  public boolean shouldFire() {
    switch (this.eventType) {
      case PRESSED:
        return (reflectButton(gamepadState, button) == true && reflectButton(oldGamepadState, button) == false);
      case RELEASED:
        return (reflectButton(gamepadState, button) == false && reflectButton(oldGamepadState, button) == true);
      case HIT:
        // TODO: implement
        return false;
      case HIT_DEBOUNCED:
        // TODO: implement
        return false;
      default:
        return false;
    }
  }
}
