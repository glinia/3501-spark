package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Claw {

    private DoubleSolenoid claw;
    private Compressor compressor;

    private C.State state;

    public Claw() {
        claw = new DoubleSolenoid(C.CLAW_FORWARD_CHANNEL,
                C.CLAW_REVERSE_CHANNEL);

        compressor = new Compressor();
        compressor.start();

        state = C.State.FREE;
    }

    public void open() {
        claw.set(C.OPEN);
    }

    public void close() {
        claw.set(C.CLOSE);
    }

    public void actuate() {
        if (state == C.State.CLOSED)
            close();
    }

    public void setState(C.State s) {
        state = s;
    }

    public C.State getState() {
        return state;
    }
}
