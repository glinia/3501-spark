package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;

public class Arm {

    private PIDController left, right;
    private Lidar leftLidar, rightLidar;

    public Arm() {
        CANJaguar leftJ  = new CANJaguar(LEFT_WINCH_ADDRESS);
        CANJaguar rightJ = new CANJaguar(RIGHT_WINCH_ADDRESS);

        leftLidar  = new Lidar(LEFT_LIDAR_ANALOG);
        rightLidar = new Lidar(RIGHT_LIDAR_ANALOG);

        left  = new PIDController(ARM_P, ARM_I, ARM_D, leftLidar,  leftJ);
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

    public String getDistance() {
        double leftD  = leftLidar.getDistance();
        double rightD = rightLidar.getDistance();

        return "l: " + leftD + " r: " + rightD;
//        return (leftD + rightD) / 2;
    }

    public double getSpeedFromJoystick(double speed) {
        if (Math.abs(speed) < MIN_ARM_JOYSTICK_INPUT)
            speed = 0;

        return speed;
    }

}
