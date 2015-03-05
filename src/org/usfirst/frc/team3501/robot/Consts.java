package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Consts {
    // joystick control
    final static int LEFT_JOYSTICK_PORT = 0, RIGHT_JOYSTICK_PORT = 1;

    final static double TOGGLE_TIME = 0.2;
    final static int UP = 0, UP_RIGHT = 45, RIGHT = 90, DOWN_RIGHT = 135,
            DOWN = 180, DOWN_LEFT = 225, LEFT = 270, UP_LEFT = 315,
            NOT_PRESSED = -1;

    // drivetrain
    final static double MIN_DRIVE_JOYSTICK_INPUT = 0.1;

    final static int FRONT_LEFT_ADDRESS = 4, FRONT_RIGHT_ADDRESS = 5,
                     REAR_LEFT_ADDRESS  = 3, REAR_RIGHT_ADDRESS  = 6;

    // winch
    final static int LEFT_WINCH_ADDRESS = 2, RIGHT_WINCH_ADDRESS = 7;

    final static double ARM_ADJUST_SPEED = 0.3;

    final static double MIN_ARM_JOYSTICK_INPUT = 0.1;

    // claw
    final static int CLAW_FORWARD_CHANNEL = 0, CLAW_REVERSE_CHANNEL = 1;

    final static Value CLOSE = Value.kForward, OPEN = Value.kReverse;

    static enum State {
        FREE, CLOSED
    }
}
