package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Claw extends FireBot {

	private DoubleSolenoid claw;
	private State state;

	public Claw() {
		claw = new DoubleSolenoid(CLAW_FORWARD_CHANNEL, CLAW_REVERSE_CHANNEL);
		state = State.FREE;
	}

	public void close() {
		claw.set(FORWARD);
	}

	public void open() {
		claw.set(REVERSE);
	}

	public void actuate() {
		if (state == State.CLOSED)
			close();
	}

	public void setState(State s) {
		state = s;
	}

	public State getState() {
		return state;
	}
}
