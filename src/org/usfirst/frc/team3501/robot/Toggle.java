package org.usfirst.frc.team3501.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Timer;

public class Toggle {

    private HashMap<Integer, Timer> timeouts;

    /*
     * WARNING: this only works with one joystick for the moment
     */
    public Toggle() {
        timeouts = new HashMap<Integer, Timer>();
    }

    public void addTimeout(int button) {
        Timer t = new Timer();
        t.start();

        timeouts.put(button, t);
    }

    public Timer getTimeout(int button) {
        return timeouts.get(button);
    }

    public boolean hasTimeLeft(int button) {
        return timeouts.get(button) != null
                && timeouts.get(button).get() < C.TOGGLE_TIME;
    }

}
