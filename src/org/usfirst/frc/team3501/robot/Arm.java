package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;
import edu.wpi.first.wpilibj.CANJaguar;

public class Arm {

    private CANJaguar left, right;

    public Arm() {
        left  = new CANJaguar(LEFT_WINCH_ADDRESS);
        right = new CANJaguar(RIGHT_WINCH_ADDRESS);
    }

    public void set(double speed) {
        left.set(-speed);
        right.set(speed);
    }

    public void moveLeft(double speed) {
        left.set(speed);
        right.set(0);
    }

    public void moveRight(double speed) {
        right.set(speed);
        left.set(0);
    }

    public double getSpeedFromJoystick(double speed) {
        if (Math.abs(speed) < MIN_ARM_JOYSTICK_INPUT)
            speed = 0;

        return speed;
    }

}
