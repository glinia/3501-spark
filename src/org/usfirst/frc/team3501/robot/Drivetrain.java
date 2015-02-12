package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;

public class Drivetrain extends FireBot {

	CANJaguar frontRight, frontLeft, rearRight, rearLeft;
	
	public Drivetrain() {
    	frontLeft  = new CANJaguar(FRONT_LEFT_ADDRESS);
		frontRight = new CANJaguar(FRONT_RIGHT_ADDRESS);
    	rearLeft   = new CANJaguar(REAR_LEFT_ADDRESS);
    	rearRight  = new CANJaguar(REAR_RIGHT_ADDRESS);
	}
	
	public void drive(double left, double right) {
        moveLeft(left);
		moveRight(right);
    }
	
	private void moveRight(double forward) {
    	frontRight.set(forward);
    	rearRight.set(forward);
    }
    
    private void moveLeft(double forward) {
    	frontLeft.set(forward);
    	rearLeft.set(forward);
    }
}
