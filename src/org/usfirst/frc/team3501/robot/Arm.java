package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;

public class Arm {

    private CANJaguar leftJ, rightJ;

    private int level;

    public Arm() {
        leftJ.setCurrentMode(C.P, C.I, C.D);
        rightJ.setCurrentMode(C.P, C.I, C.D);

        leftJ.enableControl();
        rightJ.enableControl();

        level = C.START_LEVEL;
    }

    public void set(double speed) {
        leftJ.set(speed);
        rightJ.set(speed);
    }

    public void setLevel(int level) {
        if (level > C.HIGHEST_ARM_LEVEL)
            level = C.HIGHEST_ARM_LEVEL;

        if (level < C.LOWEST_ARM_LEVEL)
            level = C.LOWEST_ARM_LEVEL;

        this.level = level;
    }

    public void upLevel() {
        setLevel(level + 1);
    }

    public void downLevel() {
        setLevel(level - 1);
    }

    public int getLevel() {
        return level;
    }

    public void move() {
        // if current position is too low, move up
        // if current position is too high, move down
    }
}
