package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

    private FireStick leftStick, rightStick;

    private Drivetrain drivetrain;
    private Arm arm;
    private Claw claw;

    public void robotInit() {
        leftStick = new FireStick(C.LEFT_JOYSTICK_PORT);
        rightStick = new FireStick(C.RIGHT_JOYSTICK_PORT);

        if (rightStick.get(3))
            C.P -= 0.05;
        if (rightStick.get(4))
            C.P += 0.05;

        if (rightStick.get(1))
            SmartDashboard.putNumber("P-val", C.P);

        drivetrain = new Drivetrain();
        // arm = new Arm();
        // claw = new Claw();
    }

    public void teleopPeriodic() {
        // buttonsPressed();

        drive();
        // arm.move();
        // claw.actuate();
    }

    private void drive() {
        double left = leftStick.getY();
        double right = -rightStick.getY();

        drivetrain.drive(left, right);
    }

    private void buttonsPressed() {
        // trigger
        if (rightStick.get(1))
            claw.close();
        else
            claw.open();

        // thumb toggle
        if (rightStick.getToggleButton(2)) {
            switch (claw.getState()) {
            case FREE:
                claw.setState(C.State.CLOSED);
            default:
                claw.setState(C.State.FREE);
            }
        }

        // hat stick
        if (rightStick.getPOV() == C.UP)
            arm.set(1);
        else if (rightStick.getPOV() == C.DOWN)
            arm.set(-1);
        else
            arm.set(0);

        // top buttons
        if (rightStick.getToggleButton(3))
            arm.downLevel();

        if (rightStick.getToggleButton(4))
            arm.upLevel();
    }
}
