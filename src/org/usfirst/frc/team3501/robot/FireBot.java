package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;

public class FireBot extends IterativeRobot {
	// joystick control
	protected final int RIGHT_JOYSTICK_PORT = 1, LEFT_JOYSTICK_PORT = 2;

	protected final double TOGGLE_TIME = 0.2;
	protected final int UP = 0, DOWN = 180, NOT_PRESSED = -1;

	// drivetrain
	protected final int FRONT_LEFT_ADDRESS = 4, FRONT_RIGHT_ADDRESS = 5,
			  			REAR_LEFT_ADDRESS = 3,  REAR_RIGHT_ADDRESS = 6;

	protected final double ROBOT_CENTER_TO_WHEELS = 15.5,
			DIST_TO_ROTATE = ROBOT_CENTER_TO_WHEELS * 2 * Math.PI / 4;

	protected final int LEFT_DRIVE_ENCODER_A = 4,  LEFT_DRIVE_ENCODER_B = 3,
						RIGHT_DRIVE_ENCODER_A = 2, RIGHT_DRIVE_ENCODER_B = 1;

	protected final double P = 0.1, I = 0.001, D = 0;

	protected final int PULSES_PER_REV = 256;

		// dist wheel goes per encoder pulse
	protected final double DIST_PER_PULSE = ((3.66 / 5.14) * 12 * Math.PI)
			/ PULSES_PER_REV;

	// winch
	protected final int LEFT_WINCH_ADDRESS = 2, RIGHT_WINCH_ADDRESS = 7;

	protected final int LEFT_WINCH_ENCODER_A = 0,  LEFT_WINCH_ENCODER_B = 1,
			  			RIGHT_WINCH_ENCODER_A = 2, RIGHT_WINCH_ENCODER_B = 3;

    // arm (heights in inches)
	protected final int START_LEVEL = 0;
	protected final int LOWEST_ARM_LEVEL = 0, HIGHEST_ARM_LEVEL = 5;

	protected final double LEVEL_0_HEIGHT = 9.375, LEVEL_1_HEIGHT = 11.875;

	// claw
	protected final int CLAW_FORWARD_CHANNEL = 0, CLAW_REVERSE_CHANNEL = 1;

	protected final Value OPEN = Value.kForward, CLOSE = Value.kReverse;

	protected enum State {
		FREE, CLOSED
	}
}
