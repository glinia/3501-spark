package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Lidar extends AnalogInput {

    public Lidar(int inputChannel) {
        super(inputChannel);
    }

    public double getDistance() {
        return getAverageVoltage();
    }

    public double pidGet() {
        return 0;
    }

}
