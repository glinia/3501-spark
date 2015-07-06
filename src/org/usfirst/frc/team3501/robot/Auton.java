package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.Timer;

import static org.usfirst.frc.team3501.robot.Consts.*;

public class Auton {

    private Drivetrain drivetrain;
    private Arm arm;
    private Claw claw;

    private Timer timer;

    public Auton(Arm arm, Claw claw, Drivetrain drivetrain, Timer timer) {
        this.arm        = arm;
        this.claw       = claw;
        this.drivetrain = drivetrain;

        this.timer = timer;
    }

    public void driveOverStep() {
        double seconds = 2.9;

        if (timer.get() < seconds * 0.2) {
            drivetrain.driveRaw(-0.3, 0);
        } else if (timer.get() >= seconds * 0.2 && timer.get() <= seconds * 0.8) {
            drivetrain.driveRaw(-0.5, 0);
        } else if (timer.get() < seconds) {
            drivetrain.driveRaw(-0.25, 0);
        } else {
            drivetrain.stop();
        }
    }

    // works well for over step
    public void drivePastStep() {
        double seconds = 1.7;

        if (timer.get() < seconds)
            drivetrain.driveRaw(-0.7, 0);
        else
            drivetrain.stop();
    }

    // normal backwards orientation, start with claw right below container lip
    public void containerAuton() {
        if (timer.get() < 0.4) {
            claw.close();
            claw.setState(State.CLOSED);
        } else if (timer.get() < 0.4 + 1.09) {
            arm.set(0.5);
            drivetrain.driveRaw(0.7, 0);
        } else if (timer.get() < 0.4 + 1.09 + 0.6) {
            arm.set(0.3);
            drivetrain.stop();
        } else {
            arm.set(0.07);
            drivetrain.stop();
        }
    }

    public void driveBackOverStep() {
        double seconds = 1.37;

        if (timer.get() < seconds)
            drivetrain.driveRaw(0.7, 0);
        else
            drivetrain.stop();
    }
}
