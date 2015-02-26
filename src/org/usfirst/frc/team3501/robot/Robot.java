package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {

    private FireStick leftStick, rightStick;

    private Drivetrain drivetrain;
    private Arm arm;
    private Claw claw;
    private int count = 0;

    public void robotInit() {
        leftStick = new FireStick(C.LEFT_JOYSTICK_PORT);
        rightStick = new FireStick(C.RIGHT_JOYSTICK_PORT);

        drivetrain = new Drivetrain();
        arm = new Arm();
        claw = new Claw();
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
        double left = leftStick.getY();
        double right = -rightStick.getY();

        drivetrain.drive(left, right);
    }

    private void buttonsPressed() {
        // trigger
        if (claw.getState() == C.State.FREE) {
            if (rightStick.get(1))
                claw.close();
            else
                claw.open();
        }

        // thumb toggle
        if (rightStick.getToggleButton(2)) {
            switch (claw.getState()) {
            case FREE:
                claw.setState(C.State.CLOSED);
            default:
                claw.setState(C.State.FREE);
            }
        }

        // arm movement / adjustment
        if (rightStick.getPOV() == C.UP)
            arm.set(C.ARM_SPEED);
        else if (rightStick.getPOV() == C.DOWN)
            arm.set(-C.ARM_SPEED);
        else if (leftStick.get(6))
            arm.moveLeft(C.ARM_SPEED);
        else if (leftStick.get(7))
            arm.moveLeft(-C.ARM_SPEED);
        else if (leftStick.get(11))
            arm.moveRight(C.ARM_SPEED);
        else if (leftStick.get(10))
            arm.moveRight(-C.ARM_SPEED);
        else
            arm.set(0);

        if (rightStick.get(11) || rightStick.get(12))
            claw.turnOff();

        if (rightStick.get(7) || rightStick.get(8))
            claw.turnOn();

        if (rightStick.getToggleButton(3)) {
            C.D -= 0.001;
            drivetrain.refreshPID();
        }

        if (rightStick.getToggleButton(4)) {
            C.D += 0.001;
            drivetrain.refreshPID();
        }

        ++count;
        if (count % 20 == 0)
            DriverStation.reportError("D: " + C.D, false);
    }
}
