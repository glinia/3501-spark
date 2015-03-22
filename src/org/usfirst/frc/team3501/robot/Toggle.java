package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;

import java.util.HashMap;

import edu.wpi.first.wpilibj.Timer;

public class Toggle {

    private HashMap<Integer, Timer> timeouts;

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
        return hasTimeLeft(button, TOGGLE_TIME);
    }

    public boolean hasTimeLeft(int button, double time) {
        Timer timer = getTimeout(button);

        return timer != null
            && timer.get() < time;
    }


}
