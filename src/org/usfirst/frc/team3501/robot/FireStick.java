package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.Joystick;

public class FireStick extends Joystick {
    Toggle toggle;
    
	public FireStick(int port) {
		super(port);
		toggle = new Toggle();
	}
	
	public boolean getToggleButton(int button) {
    	boolean pressed = this.getRawButton(button)
    				   && !toggle.hasTimeLeft(button);
    	
    	if (pressed)
    		toggle.addTimeout(button);
    	
    	return pressed;
    }
	
	public boolean get(int button) {
		return this.getRawButton(button);
	}
}
