
package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class Helios extends FireBot {
	
	Joystick rightStick, leftStick;
	DriverStation driverStation;
	Toggle toggle;
	
	Drivetrain drivetrain;
	Arm arm;
	Claw claw;
	
    public void robotInit() {
    	driverStation = DriverStation.getInstance();
    	rightStick    = new Joystick(RIGHT_JOYSTICK_PORT);
    	leftStick     = new Joystick(LEFT_JOYSTICK_PORT);
    	toggle        = new Toggle();
    	
    	drivetrain = new Drivetrain();
    	arm        = new Arm();
    	claw       = new Claw();
    }

    public void teleopPeriodic() {
    	double right = rightStick.getRawAxis(Y_AXIS);
    	double left  = leftStick.getRawAxis(Y_AXIS);
    	
    	drivetrain.drive(right, left);
    	
    	buttonsPressed();
    	
        arm.moveToLevel();
        claw.actuate();
    }
    
    private void buttonsPressed() {
    	// trigger
    	if (rightStick.getRawButton(1))
    		claw.close();
    	else
    		claw.open();
    	
    	// thumb toggle
    	if (getToggleButton(rightStick, 2)) {
    		switch (claw.getState()) {
    		case FREE:
    			claw.setState(State.CLOSED);
    		default:
    			claw.setState(State.FREE);
    		}
    	}
    	
    	// hat stick
    	if (rightStick.getPOV() == UP)
    		arm.set(1);
    	else if (rightStick.getPOV() == DOWN)
    		arm.set(-1);
    	else
    		arm.set(0);
    	
    	// top buttons
    	if (getToggleButton(rightStick, 3))
    		arm.setLevel(arm.getLevel() - 1);
    	
    	if (getToggleButton(rightStick, 4))
    		arm.setLevel(arm.getLevel() + 1);
    }
    
    private boolean getToggleButton(Joystick joystick, int button) {
    	boolean pressed = joystick.getRawButton(button)
    				   && !toggle.hasTimeLeft(button);
    	
    	if (pressed)
    		toggle.addTimeout(button);
    	
    	return pressed;
    }
}
