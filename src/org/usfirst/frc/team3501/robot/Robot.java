package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

    private Joystick leftStick, rightStick;

    private Drivetrain drivetrain;
    private Arm arm;
    private Claw claw;

    public void robotInit() {
        leftStick  = new Joystick(LEFT_JOYSTICK_PORT);
        rightStick = new Joystick(RIGHT_JOYSTICK_PORT);

        drivetrain = new Drivetrain();
        arm        = new Arm();
        claw       = new Claw();
    }

    public void teleopPeriodic() {
        buttonsPressed();

        drive();
        claw.actuate();
    }

    public void testPeriodic() {
        LiveWindow.run();
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

        // arm speed from joystick
        double setSpeed = arm.getSpeedFromJoystick(-leftStick.getY());

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
