package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class Drivetrain extends FireBot {

	private CANJaguar frontLeftJ, frontRightJ, rearLeftJ, rearRightJ;
	
	private PIDController frontLeft, frontRight, rearLeft, rearRight;
	
	public Drivetrain() {
		initJags();
		
    	Encoder leftEncoder = new Encoder(LEFT_DRIVE_ENCODER_A, LEFT_DRIVE_ENCODER_B,
    			false, EncodingType.k4X);
    	Encoder rightEncoder = new Encoder(RIGHT_DRIVE_ENCODER_A, RIGHT_DRIVE_ENCODER_B,
    			false, EncodingType.k4X);
    	
    	leftEncoder.setDistancePerPulse(DIST_PER_PULSE);
    	rightEncoder.setDistancePerPulse(DIST_PER_PULSE);
    	
    	frontLeft  = new PIDController(P, I, D, leftEncoder, frontLeftJ);
    	frontRight = new PIDController(P, I, D, leftEncoder, frontRightJ);
    	rearLeft   = new PIDController(P, I, D, rightEncoder, rearLeftJ);
    	rearRight  = new PIDController(P, I, D, rightEncoder, rearRightJ);
    	
    	frontLeft.enable();
    	frontRight.enable();
    	rearLeft.enable();
    	rearRight.enable();
	}
	
	private void initJags() {
		frontLeftJ  = new CANJaguar(FRONT_LEFT_ADDRESS);
		frontRightJ = new CANJaguar(FRONT_RIGHT_ADDRESS);
    	rearLeftJ   = new CANJaguar(REAR_LEFT_ADDRESS);
    	rearRightJ  = new CANJaguar(REAR_RIGHT_ADDRESS);
    	
    	frontLeftJ.setCurrentMode(CANJaguar.kQuadEncoder, 256, 0.1, 0.001, 0);
    	frontRightJ.setCurrentMode(CANJaguar.kQuadEncoder, 256, 0.1, 0.001, 0);
    	rearLeftJ.setCurrentMode(CANJaguar.kQuadEncoder, 256, 0.1, 0.001, 0);
    	rearRightJ.setCurrentMode(CANJaguar.kQuadEncoder, 256, 0.1, 0.001, 0);
    	
    	frontLeftJ.enableControl();
    	frontRightJ.enableControl();
    	rearLeftJ.enableControl();
    	rearRightJ.enableControl();
	}

	public void drive(double left, double right) {
        moveLeft(scale(left));
		moveRight(scale(right));
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
