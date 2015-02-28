package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.C.*;

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

        if (count % 20 == 0)
            DriverStation.reportError("arm speed: " + ARM_SPEED, false);
    }

    public void testPeriodic() {
        LiveWindow.run();
    }

    private void drive() {
        double left = leftStick.getY();
        double right = -rightStick.getY();

        drivetrain.drive(left, right);
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
            switch (claw.getState()) {
            case FREE:
                claw.setState(State.CLOSED);
            default:
                claw.setState(State.FREE);
            }
        }

        // arm movement / adjustment
        if (rightStick.isPOV(UP))
            arm.set(ARM_SPEED);
        else if (rightStick.isPOV(DOWN))
            arm.set(-ARM_SPEED);
        else if (leftStick.get(7))
            arm.moveLeft(ARM_SPEED);
        else if (leftStick.get(6))
            arm.moveLeft(-ARM_SPEED);
        else if (leftStick.get(11))
            arm.moveRight(ARM_SPEED);
        else if (leftStick.get(10))
            arm.moveRight(-ARM_SPEED);
        else
            arm.set(0);

        if (rightStick.getOne(11, 12))
            claw.turnOff();

        if (rightStick.getOne(7, 8))
            claw.turnOn();

        if (rightStick.getToggleButton(3))
            ARM_SPEED -= 0.1;

        if (rightStick.getToggleButton(4))
            ARM_SPEED += 0.1;
    }
}
