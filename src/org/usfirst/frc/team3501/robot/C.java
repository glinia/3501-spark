package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class C {
    // joystick control
    final static int RIGHT_JOYSTICK_PORT = 3, LEFT_JOYSTICK_PORT = 2;

    final static double TOGGLE_TIME = 0.2;
    final static int UP = 0, DOWN = 180, NOT_PRESSED = -1;

    // drivetrain
    final static int FRONT_LEFT_ADDRESS = 4, FRONT_RIGHT_ADDRESS = 5,
            REAR_LEFT_ADDRESS = 3, REAR_RIGHT_ADDRESS = 6;

    final static double ROBOT_CENTER_TO_WHEELS = 15.5,
            DIST_TO_ROTATE = ROBOT_CENTER_TO_WHEELS * 2 * Math.PI / 4;

    final static int LEFT_DRIVE_ENCODER_A = 4, LEFT_DRIVE_ENCODER_B = 3,
            RIGHT_DRIVE_ENCODER_A = 2, RIGHT_DRIVE_ENCODER_B = 1;

    final static double P = 0.1, I = 0.001, D = 0;

    final static int PULSES_PER_REV = 256;

    // dist wheel goes per encoder pulse
    final static double DIST_PER_PULSE = ((3.66 / 5.14) * 12 * Math.PI)
            / PULSES_PER_REV;

    // winch
    final static int LEFT_WINCH_ADDRESS = 2, RIGHT_WINCH_ADDRESS = 7;

    final static int LEFT_WINCH_ENCODER_A = 0, LEFT_WINCH_ENCODER_B = 1,
            RIGHT_WINCH_ENCODER_A = 2, RIGHT_WINCH_ENCODER_B = 3;

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
