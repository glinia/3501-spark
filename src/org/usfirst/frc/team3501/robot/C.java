package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class C {
    // joystick control
    final static int LEFT_JOYSTICK_PORT = 0, RIGHT_JOYSTICK_PORT = 1;

    final static double TOGGLE_TIME = 0.2;
    final static int UP = 0, UP_RIGHT = 45, RIGHT = 90, DOWN_RIGHT = 135,
            DOWN = 180, DOWN_LEFT = 225, LEFT = 270, UP_LEFT = 315,
            NOT_PRESSED = -1;

    // drivetrain
    final static int FRONT_LEFT_ADDRESS = 4, FRONT_RIGHT_ADDRESS = 5,
            REAR_LEFT_ADDRESS = 3, REAR_RIGHT_ADDRESS = 6;

    final static double ROBOT_CENTER_TO_WHEELS = 15.5,
            DIST_TO_ROTATE = ROBOT_CENTER_TO_WHEELS * 2 * Math.PI / 4;

    final static int LEFT_DRIVE_ENCODER_A = 3, LEFT_DRIVE_ENCODER_B = 4,
            RIGHT_DRIVE_ENCODER_A = 1, RIGHT_DRIVE_ENCODER_B = 2;

    static double P = 1, I = 0, D = 0;

    final static int PULSES_PER_REV = 256;

    // dist wheel goes per encoder pulse
    final static double DIST_PER_PULSE = ((3.66 / 5.14) * 12 * Math.PI)
            / PULSES_PER_REV;

    // winch
    final static int LEFT_WINCH_ADDRESS = 2, RIGHT_WINCH_ADDRESS = 7;

    // arm (heights in inches)
    final static int START_LEVEL = 0;
    final static int LOWEST_ARM_LEVEL = 0, HIGHEST_ARM_LEVEL = 5;

    final static double LEVEL_0_HEIGHT = 9.375, LEVEL_1_HEIGHT = 11.875;

    // claw
    final static int CLAW_FORWARD_CHANNEL = 0, CLAW_REVERSE_CHANNEL = 1;

    final static Value OPEN = Value.kForward, CLOSE = Value.kReverse;

    static enum State {
        FREE, CLOSED
    }
}
