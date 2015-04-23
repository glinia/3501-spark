package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import static org.usfirst.frc.team3501.robot.Consts.*;

public class Robot extends IterativeRobot {

    private Joystick leftStick, rightStick;

    private Drivetrain drivetrain;
    private Arm arm;
    private Claw claw;

    private Timer timer;

    public void robotInit() {
        leftStick  = new Joystick(LEFT_JOYSTICK_PORT);
        rightStick = new Joystick(RIGHT_JOYSTICK_PORT);

        drivetrain = new Drivetrain();
        arm        = new Arm();
        claw       = new Claw();

        timer = new Timer();
    }

    public void teleopPeriodic() {
        buttonsPressed();

        drive();
        claw.actuate();
    }

    public void autonomousInit() {
        timer.reset();
        timer.start();
    }

    public void autonomousPeriodic() {
        // swiggity swag got this auton in the bag
        driveBackOverStep();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }

    private void driveOverStep() {
        double seconds = 2.9;

        if (timer.get() < seconds * 0.2) {
            drivetrain.driveRaw(-0.3, 0);
        } else if (timer.get() >= seconds * 0.2 && timer.get() <= seconds * 0.8) {
            drivetrain.driveRaw(-0.5, 0);
        } else if (timer.get() < seconds) {
            drivetrain.driveRaw(-0.25, 0);
        } else {
            drivetrain.driveRaw(0, 0);
        }
    }

    private void drivePastStep() {
        double seconds = 2.4;

        if (timer.get() < seconds * 0.2) {
            drivetrain.driveRaw(-0.3, 0);
        } else if (timer.get() >= seconds * 0.2 && timer.get() <= seconds * 0.8) {
            drivetrain.driveRaw(-0.5, 0);
        } else if (timer.get() < seconds) {
            drivetrain.driveRaw(-0.25, 0);
        } else {
            drivetrain.driveRaw(0, 0);
        }
    }

    // normal backwards orientation, start with claw right below container lip
    private void containerAuton() {
        if (timer.get() < 0.4) {
            claw.close();
        } else if (timer.get() < 0.4 + 1.4) {
            arm.set(0.5);
            drivetrain.driveRaw(0.7, 0);
        } else if (timer.get() < 0.4 + 1.4 + 0.37) {
            arm.set(0);
            drivetrain.driveRaw(0.7, 0);
        } else {
            arm.set(0);
            drivetrain.driveRaw(0, 0);
        }
    }

    private void driveBackOverStep() {
        double seconds = 1.37;

        if (timer.get() < seconds)
            drivetrain.driveRaw(0.7, 0);
        else
            drivetrain.driveRaw(0, 0);
    }

    private void drive() {
        double forward = rightStick.getY();
        double twist   = rightStick.getTwist();

        if (rightStick.getOne(3, 4, 5, 6))
            twist = 0;

        drivetrain.drive(forward, twist);
    }

    private void buttonsPressed() {
        State clawState = claw.getState();

        // trigger
        if (clawState == State.FREE) {
            if (rightStick.get(1))
                claw.close();
            else
                claw.open();
        }

        // thumb toggle
        if (rightStick.getToggleButton(2)) {
            if (clawState == State.FREE)
                claw.setState(State.CLOSED);
            else if (clawState == State.CLOSED)
                claw.setState(State.FREE);
        }

        // arm movement / adjustment
        double setSpeed = arm.getSpeedFromJoystick(-leftStick.getY());

        if (leftStick.get(1))
            setSpeed /= 5;

        if (leftStick.get(7))
            arm.moveLeft(ARM_ADJUST_SPEED);
        else if (leftStick.get(6))
            arm.moveLeft(-ARM_ADJUST_SPEED);
        else if (leftStick.get(11))
            arm.moveRight(ARM_ADJUST_SPEED);
        else if (leftStick.get(10))
            arm.moveRight(-ARM_ADJUST_SPEED);
        else
            arm.set(setSpeed);

        if (rightStick.getOne(11, 12))
            claw.turnOff();

        if (rightStick.getOne(7, 8))
            claw.turnOn();
    }

}
