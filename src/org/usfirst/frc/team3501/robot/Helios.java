
package org.usfirst.frc.team3501.robot;

public class Helios extends FireBot {
	
	private FireStick rightStick, leftStick;
	
	private Drivetrain drivetrain;
	private Arm arm;
	private Claw claw;
	
    public void robotInit() {
    	rightStick    = new FireStick(RIGHT_JOYSTICK_PORT);
    	leftStick     = new FireStick(LEFT_JOYSTICK_PORT);
    	
    	drivetrain = new Drivetrain();
    	arm        = new Arm();
    	claw       = new Claw();
    }

    public void teleopPeriodic() {
    	double right = rightStick.getY();
    	double left  = leftStick.getY();
    	
    	drivetrain.drive(right, left);
    	
    	buttonsPressed();
    	
        arm.moveToLevel();
        claw.actuate();
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
    	if (rightStick.getToggleButton(3))
    		arm.setLevel(arm.getLevel() - 1);
    	
    	if (rightStick.getToggleButton(4))
    		arm.setLevel(arm.getLevel() + 1);
    }
}
