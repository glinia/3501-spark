package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;

public class Arm {

    private PIDController left, right;
    private Lidar leftLidar, rightLidar;

    public Arm() {
        CANJaguar leftJ = new CANJaguar(C.LEFT_WINCH_ADDRESS);
        CANJaguar rightJ = new CANJaguar(C.RIGHT_WINCH_ADDRESS);

        leftLidar = new Lidar(C.LEFT_LIDAR_PORT);
        rightLidar = new Lidar(C.RIGHT_LIDAR_PORT);

        left = new PIDController(C.ARM_P, C.ARM_I, C.ARM_D, leftLidar,
                leftJ);
        right = new PIDController(C.ARM_P, C.ARM_I, C.ARM_D, rightLidar,
                rightJ);

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

}
