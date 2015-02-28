package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Lidar extends AnalogInput {

    public Lidar(int channel) {
        super(channel);
    }

    public double getDistance() {
        return getVoltage();
    }

    public double pidGet() {
        return 0;
    }

}
