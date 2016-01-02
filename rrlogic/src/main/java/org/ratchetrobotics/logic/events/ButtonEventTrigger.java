package org.ratchetrobotics.logic.events;

import android.util.Log;

import org.ratchetrobotics.logic.data.GamepadState;

import java.lang.reflect.Field;

/**
 * Created by liam on 12/18/15.
 */
public class ButtonEventTrigger extends GamepadEventTrigger {
  private String button;
  private EventType eventType;

  // for debouncing
  private boolean isDebounced;
  private long lastDebounceTime = 0;
  private static final long DEBOUNCE_TIME = 50;

  private static final float FBUTTON_THRESHOLD = 0.75F; // range is [0..1], unpressed to fully-pressed

  public enum EventType {
    PRESSED, // As soon as pressed
    RELEASED // As soon as released
  }

  public ButtonEventTrigger(String button, EventType eventType) {
    this.button = button;
    this.eventType = eventType;
    this.isDebounced = true; // default to debouncing
  }
  public ButtonEventTrigger(String button, EventType eventType, boolean debounced) {
    this.button = button;
    this.eventType = eventType;
    this.isDebounced = debounced;
  }

  public void tick() {
    super.tick();
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
    if (isDebounced && reflectButton(gamepadState, button) != reflectButton(oldGamepadState, button)) {
      lastDebounceTime = System.nanoTime();
    }

    switch (this.eventType) {
      case PRESSED:
        if (isDebounced) {
          return (reflectButton(gamepadState, button) && !reflectButton(oldGamepadState, button)) && (System.nanoTime() - lastDebounceTime > DEBOUNCE_TIME);
        } else {
          return (reflectButton(gamepadState, button) && !reflectButton(oldGamepadState, button));
        }
      case RELEASED:
        if (isDebounced) {
          return (!reflectButton(gamepadState, button) && reflectButton(oldGamepadState, button)) && (System.nanoTime() - lastDebounceTime > DEBOUNCE_TIME);
        } else {
          return (!reflectButton(gamepadState, button) && reflectButton(oldGamepadState, button));
        }
      default:
        return false;
    }
  }
}
