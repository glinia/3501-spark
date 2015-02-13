package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;

public class Arm extends FireBot {

	private CANJaguar left, right;
	private Encoder leftShaft, rightShaft;

	private int level;

	public Arm() {
    	left  = new CANJaguar(LEFT_WINCH_ADDRESS);
		right = new CANJaguar(RIGHT_WINCH_ADDRESS);

		leftShaft  = new Encoder(LEFT_WINCH_ENCODER_A,  LEFT_WINCH_ENCODER_B);
		rightShaft = new Encoder(RIGHT_WINCH_ENCODER_A, RIGHT_WINCH_ENCODER_B);

		level = START_LEVEL;
	}

	public void set(double speed) {
		left.set(speed);
		right.set(speed);
	}

	public void setLevel(int level) {
		if (level > 4) level = 4;
		if (level < 0) level = 0;

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
