package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class Drivetrain {

    private CANJaguar frontLeftJ, frontRightJ, rearLeftJ, rearRightJ;
    private PIDController frontLeft, frontRight, rearLeft, rearRight;

    public Drivetrain() {
        initJags();

        Encoder leftEncoder = new Encoder(C.LEFT_DRIVE_ENCODER_A,
                C.LEFT_DRIVE_ENCODER_B, false, EncodingType.k4X);
        Encoder rightEncoder = new Encoder(C.RIGHT_DRIVE_ENCODER_A,
                C.RIGHT_DRIVE_ENCODER_B, false, EncodingType.k4X);

        leftEncoder.setDistancePerPulse(C.DIST_PER_PULSE);
        rightEncoder.setDistancePerPulse(C.DIST_PER_PULSE);

        frontLeft = new PIDController(C.P, C.I, C.D, leftEncoder, frontLeftJ);
        frontRight = new PIDController(C.P, C.I, C.D, leftEncoder, frontRightJ);
        rearLeft = new PIDController(C.P, C.I, C.D, rightEncoder, rearLeftJ);
        rearRight = new PIDController(C.P, C.I, C.D, rightEncoder, rearRightJ);

        frontLeft.enable();
        frontRight.enable();
        rearLeft.enable();
        rearRight.enable();
    }

    private void initJags() {
        frontLeftJ = new CANJaguar(C.FRONT_LEFT_ADDRESS);
        frontRightJ = new CANJaguar(C.FRONT_RIGHT_ADDRESS);
        rearLeftJ = new CANJaguar(C.REAR_LEFT_ADDRESS);
        rearRightJ = new CANJaguar(C.REAR_RIGHT_ADDRESS);

        // frontLeftJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P,
        // I, D);
        // frontRightJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P,
        // I, D);
        // rearLeftJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P,
        // I, D);
        // rearRightJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P,
        // I, D);
        //
        // frontLeftJ.enableControl();
        // frontRightJ.enableControl();
        // rearLeftJ.enableControl();
        // rearRightJ.enableControl();
    }

    public void drive(double left, double right) {
        moveLeft(scale(left));
        moveRight(scale(-right));
    }

    private double scale(double speed) {
        return Math.abs(speed) * speed;
    }

    private void moveLeft(double forward) {
        frontLeft.setSetpoint(forward);
        rearLeft.setSetpoint(forward);
    }

    private void moveRight(double forward) {
        frontRight.setSetpoint(forward);
        rearRight.setSetpoint(forward);
    }
}
