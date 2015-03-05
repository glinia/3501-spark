package org.usfirst.frc.team3501.robot;

import java.util.Arrays;

public class Joystick extends edu.wpi.first.wpilibj.Joystick {

    private Toggle toggle;

    public Joystick(int port) {
        super(port);
        toggle = new Toggle();
    }

    public boolean isPOV(int dir) {
        return (getPOV() == dir);
    }

    public boolean get(int button) {
        return getRawButton(button);
    }

    public boolean getToggleButton(int button) {
        boolean pressed = get(button)
                && !toggle.hasTimeLeft(button);

        if (pressed)
            toggle.addTimeout(button);

        return pressed;
    }

    public boolean getOne(int... buttons) {
        return Arrays.stream(buttons).anyMatch(b -> get(b));
    }
}
