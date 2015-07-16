package org.usfirst.frc.team3501.robot;

import org.usfirst.frc.team3501.robot.Consts.State;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static org.usfirst.frc.team3501.robot.Consts.*;

public class Claw {

    private DoubleSolenoid claw;
    private Compressor compressor;

    private State state;

    public Claw() {
        claw = new DoubleSolenoid(CLAW_FORWARD_CHANNEL, CLAW_REVERSE_CHANNEL);

        compressor = new Compressor();

        state = State.FREE;
    }

    public void turnOff() {
        compressor.stop();
    }

    public void turnOn() {
        compressor.start();
    }

    public void open() {
        claw.set(OPEN);
    }

    public void close() {
        claw.set(CLOSE);
    }

    public void actuate() {
        if (state == State.CLOSED)
            close();
    }

    public void toggleState() {
        switch(state) {
        case FREE:   setState(State.CLOSED);
        case CLOSED: setState(State.FREE);
        }
    }

    public void setState(State s) {
        state = s;
    }

    public State getState() {
        return state;
    }
}
