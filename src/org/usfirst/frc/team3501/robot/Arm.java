package org.usfirst.frc.team3501.robot;

import static org.usfirst.frc.team3501.robot.Consts.*;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.PIDController;

public class Arm {

    private CANJaguar left, right;
    private Lidar leftLidar, rightLidar;

    public Arm() {
        left  = new CANJaguar(LEFT_WINCH_ADDRESS);
        right = new CANJaguar(RIGHT_WINCH_ADDRESS);

        leftLidar  = new Lidar(LEFT_LIDAR_ANALOG);
        rightLidar = new Lidar(RIGHT_LIDAR_ANALOG);
    }

    public void set(double speed) {
        left.set(-speed);
        right.set(speed);
    }

    public void moveLeft(double speed) {
        left.set(speed);
        right.set(0);
    }

    public void moveRight(double speed) {
        right.set(speed);
        left.set(0);
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
