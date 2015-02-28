package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;

public class Arm {

    private PIDController left, right;
    private Lidar leftLidar, rightLidar;

    public Arm() {
        CANJaguar leftJ = new CANJaguar(LEFT_WINCH_ADDRESS);
        CANJaguar rightJ = new CANJaguar(RIGHT_WINCH_ADDRESS);

        leftLidar = new Lidar(LEFT_LIDAR_PORT);
        rightLidar = new Lidar(RIGHT_LIDAR_PORT);

        left = new PIDController(ARM_P, ARM_I, ARM_D, leftLidar, leftJ);
        right = new PIDController(ARM_P, ARM_I, ARM_D, rightLidar, rightJ);

        left.enable();
        right.enable();
    }

    public void set(double speed) {
        left.setSetpoint(-speed);
        right.setSetpoint(speed);
    }

    public void moveLeft(double speed) {
        left.setSetpoint(speed);
        right.setSetpoint(0);
    }

    public void moveRight(double speed) {
        right.setSetpoint(speed);
        left.setSetpoint(0);
    }

    public double getDistance() {
        double leftD = leftLidar.getDistance();
        double rightD = rightLidar.getDistance();

        return leftD;
    }

    public void addSpeed(double val) {
        ARM_SPEED += val;

        if (ARM_SPEED > 1)
            ARM_SPEED = 1;

        if (ARM_SPEED < 0)
            ARM_SPEED = 0;
    }

}
