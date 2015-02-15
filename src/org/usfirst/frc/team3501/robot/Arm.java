package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class Arm extends FireBot {

    private CANJaguar     leftJ, rightJ;
    private PIDController left,  right;

    private int level;

    public Arm() {
        initJags();

        Encoder leftShaft  = new Encoder(LEFT_WINCH_ENCODER_A,  LEFT_WINCH_ENCODER_B,
                false, EncodingType.k4X);
        Encoder rightShaft = new Encoder(RIGHT_WINCH_ENCODER_A, RIGHT_WINCH_ENCODER_B,
                false, EncodingType.k4X);

        left  = new PIDController(P, I, D, leftShaft, leftJ);
        right = new PIDController(P, I, D, rightShaft, rightJ);
        
        left.enable();
        right.enable();
        
        level = START_LEVEL;
    }
    
    private void initJags() {
        leftJ  = new CANJaguar(LEFT_WINCH_ADDRESS);
        rightJ = new CANJaguar(RIGHT_WINCH_ADDRESS);
        
//        leftJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P, I, D);
//        rightJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P, I, D);
//        
//        leftJ.enableControl();
//        rightJ.enableControl();
    }

    public void set(double speed) {
        left.setSetpoint(speed);
        right.setSetpoint(speed);
    }

    public void setLevel(int level) {
        if (level > HIGHEST_ARM_LEVEL) level = HIGHEST_ARM_LEVEL;
        if (level < LOWEST_ARM_LEVEL) level = LOWEST_ARM_LEVEL;

        this.level = level;
    }

    public void upLevel() {
        setLevel(level + 1);
    }

    public void downLevel() {
        setLevel(level - 1);
    }

    public int getLevel() {
        return level;
    }

    public void move() {
        // if current position is too low, move up
        // if current position is too high, move down
    }
}
