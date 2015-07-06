package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;

public class Drivetrain {

    private RobotDrive robotDrive;

    public Drivetrain() {
        CANJaguar frontLeft  = new CANJaguar(FRONT_LEFT_ADDRESS);
        CANJaguar frontRight = new CANJaguar(FRONT_RIGHT_ADDRESS);
        CANJaguar rearLeft   = new CANJaguar(REAR_LEFT_ADDRESS);
        CANJaguar rearRight  = new CANJaguar(REAR_RIGHT_ADDRESS);

        robotDrive = new RobotDrive(
                frontLeft,  rearLeft,
                frontRight, rearRight);
    }

    // output is avg of `x` and `sqrt(x)`
    private double adjust(double x) {
        return (x + Math.signum(x) * Math.sqrt(Math.abs(x))) / 2;
    }

    public void drive(double forward, double twist) {
        if (Math.abs(forward) < MIN_DRIVE_JOYSTICK_INPUT)
            forward = 0;
        if (Math.abs(twist) < MIN_DRIVE_JOYSTICK_INPUT)
            twist = 0;

        robotDrive.arcadeDrive(
                MAX_DRIVE_SPEED * adjust(forward),
                MAX_DRIVE_SPEED * adjust(twist),
                false);
    }

    public void driveRaw(double forward, double twist) {
        robotDrive.arcadeDrive(forward, twist, false);
    }

    public void stop() {
        driveRaw(0.0, 0.0);
    }
}
