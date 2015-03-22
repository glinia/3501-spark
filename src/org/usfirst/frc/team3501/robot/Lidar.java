package org.usfirst.frc.team3501.robot;

import java.util.ArrayList;
import java.util.stream.Collectors;

import edu.wpi.first.wpilibj.AnalogInput;

import static org.usfirst.frc.team3501.robot.Consts.*;

public class Lidar {

    private AnalogInput lidar;

    private ArrayList<Double> vals;

    public Lidar() {
        lidar = new AnalogInput(LIDAR_ANALOG_PORT);
        vals  = new ArrayList<Double>(0);
    }

    public double getVoltage() {
        double voltage = lidar.getAverageVoltage();
        vals.add(voltage);

        vals = (ArrayList<Double>)
                vals.stream().limit(4).collect(Collectors.toList());

        voltage = vals.stream().mapToDouble(v -> v).average().getAsDouble();
        return voltage;
    }

}
