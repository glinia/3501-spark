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

        vals  = new ArrayList<Double>();
    }

    public double getVoltage() {
        vals.add(lidar.getAverageVoltage());

        vals = (ArrayList<Double>)
                vals.stream().limit(4).collect(Collectors.toList());

        return vals.stream().mapToDouble(d -> d).average().orElse(0);
    }

}
