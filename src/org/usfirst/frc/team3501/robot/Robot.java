package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

    private FireStick leftStick, rightStick;

    private Drivetrain drivetrain;
    private Arm arm;
    private Claw claw;

    private int count;

    public void robotInit() {
        leftStick = new FireStick(LEFT_JOYSTICK_PORT);
        rightStick = new FireStick(RIGHT_JOYSTICK_PORT);

        drivetrain = new Drivetrain();
        arm = new Arm();
        claw = new Claw();

        count = 0;
    }

    public void teleopPeriodic() {
        buttonsPressed();

        drive();
        claw.actuate();

        if (count++ % 20 == 0) {
            DriverStation.reportError("arm speed: " + ARM_SPEED, false);
        }
    }

    public void testPeriodic() {
        LiveWindow.run();
    }

    private void drive() {
        double forward = rightStick.getY();
        double twist = rightStick.getTwist();

        drivetrain.drive(forward, twist);
    }

    private void buttonsPressed() {
        // trigger
        if (claw.getState() == State.FREE) {
            if (rightStick.get(1))
                claw.close();
            else
                claw.open();
        }

        // thumb toggle
        if (rightStick.getToggleButton(2)) {
            if (claw.getState() == State.FREE)
                claw.setState(State.CLOSED);
            else if (claw.getState() == State.CLOSED)
                claw.setState(State.FREE);
        }

        // arm movement / adjustment
        if (rightStick.isPOV(UP))
            arm.set(ARM_SPEED);
        else if (rightStick.isPOV(DOWN))
            arm.set(-ARM_SPEED);
        else if (leftStick.get(7))
            arm.moveLeft(ARM_ADJUST_SPEED);
        else if (leftStick.get(6))
            arm.moveLeft(-ARM_ADJUST_SPEED);
        else if (leftStick.get(11))
            arm.moveRight(ARM_ADJUST_SPEED);
        else if (leftStick.get(10))
            arm.moveRight(-ARM_ADJUST_SPEED);
        else
            arm.set(0);

        if (rightStick.getOne(11, 12))
            claw.turnOff();

        if (rightStick.getOne(7, 8))
            claw.turnOn();

        if (rightStick.getToggleButton(3))
            arm.addSpeed(-0.1);

        if (rightStick.getToggleButton(4))
            arm.addSpeed(0.1);
    }
}
