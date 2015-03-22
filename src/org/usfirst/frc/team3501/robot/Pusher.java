package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import static org.usfirst.frc.team3501.robot.Consts.*;

public class Pusher {

    private DoubleSolenoid pusher;

    public Pusher() {
        pusher = new DoubleSolenoid
                (PUSHER_FORWARD_CHANNEL, PUSHER_REVERSE_CHANNEL);
    }

    public void open() {
        pusher.set(OPEN);
    }

    public void close() {
        pusher.set(CLOSE);
    }

}
