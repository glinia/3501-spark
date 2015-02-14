package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;

public class FireBot extends IterativeRobot {
	// joystick control
	protected final int RIGHT_JOYSTICK_PORT = 1, LEFT_JOYSTICK_PORT = 2;

	protected final double TOGGLE_TIME = 0.2;

	protected final int UP = 1, UP_RIGHT = 2, RIGHT = 3, DOWN_RIGHT = 4,
											DOWN = 5, DOWN_LEFT = 6, LEFT = 7, UP_LEFT = 8;

	// drivetrain
	protected final int FRONT_LEFT_ADDRESS = 4, FRONT_RIGHT_ADDRESS = 5,
			  REAR_LEFT_ADDRESS = 3,  REAR_RIGHT_ADDRESS = 6;

	// winch
	protected final int LEFT_WINCH_ADDRESS = 2, RIGHT_WINCH_ADDRESS = 7;

	protected final int LEFT_WINCH_ENCODER_A = 0,  LEFT_WINCH_ENCODER_B = 1,
			  RIGHT_WINCH_ENCODER_A = 2, RIGHT_WINCH_ENCODER_B = 3;

  // arm
	protected final int START_LEVEL = 0;

	// claw
	protected final int CLAW_FORWARD_CHANNEL = 0, CLAW_REVERSE_CHANNEL = 1;

	protected final Value FORWARD = Value.kForward, REVERSE = Value.kReverse;

	protected enum State {
		FREE, CLOSED
	}
}
