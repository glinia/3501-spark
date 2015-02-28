package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.C.*;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class Drivetrain {

    private CANJaguar frontLeftJ, frontRightJ, rearLeftJ, rearRightJ;
    private PIDController frontLeft, frontRight, rearLeft, rearRight;
    private Encoder leftEncoder, rightEncoder;

    public Drivetrain() {
        initJags();

        leftEncoder = new Encoder(
                LEFT_ENCODER_A, LEFT_ENCODER_B,
                false, EncodingType.k4X);

        rightEncoder = new Encoder(
                RIGHT_ENCODER_A, RIGHT_ENCODER_B,
                false, EncodingType.k4X);

        leftEncoder.setDistancePerPulse(DIST_PER_PULSE);
        rightEncoder.setDistancePerPulse(DIST_PER_PULSE);

        frontLeft = new PIDController(
                DRIVE_P, DRIVE_I, DRIVE_D,
                leftEncoder, frontLeftJ);
        frontRight = new PIDController(
                DRIVE_P, DRIVE_I, DRIVE_D,
                leftEncoder, frontRightJ);
        rearLeft = new PIDController(
                DRIVE_P, DRIVE_I, DRIVE_D,
                rightEncoder, rearLeftJ);
        rearRight = new PIDController(
                DRIVE_P, DRIVE_I, DRIVE_D,
                rightEncoder, rearRightJ);

        frontLeft.enable();
        frontRight.enable();
        rearLeft.enable();
        rearRight.enable();
    }

    private void initJags() {
        frontLeftJ = new CANJaguar(FRONT_LEFT_ADDRESS);
        frontRightJ = new CANJaguar(FRONT_RIGHT_ADDRESS);
        rearLeftJ = new CANJaguar(REAR_LEFT_ADDRESS);
        rearRightJ = new CANJaguar(REAR_RIGHT_ADDRESS);
    }

    public void drive(double left, double right) {
        moveLeft(left);
        moveRight(right);
    }

    private void moveLeft(double forward) {
        frontLeft.setSetpoint(forward);
        rearLeft.setSetpoint(forward);
    }

    private void moveRight(double forward) {
        frontRight.setSetpoint(forward);
        rearRight.setSetpoint(forward);
    }

    public void refreshPID() {
        frontLeft.setPID(DRIVE_P, DRIVE_I, DRIVE_D);
        rearLeft.setPID(DRIVE_P, DRIVE_I, DRIVE_D);
        frontRight.setPID(DRIVE_P, DRIVE_I, DRIVE_D);
        rearRight.setPID(DRIVE_P, DRIVE_I, DRIVE_D);
    }
}
