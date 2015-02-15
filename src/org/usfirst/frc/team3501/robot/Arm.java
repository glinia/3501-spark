package org.usfirst.frc.team3501.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;

public class Arm {

    private CANJaguar leftJ, rightJ;
    private PIDController left, right;

    private int level;

    public Arm() {
        initJags();

        Encoder leftShaft = new Encoder(C.LEFT_WINCH_ENCODER_A,
                C.LEFT_WINCH_ENCODER_B, false, EncodingType.k4X);
        Encoder rightShaft = new Encoder(C.RIGHT_WINCH_ENCODER_A,
                C.RIGHT_WINCH_ENCODER_B, false, EncodingType.k4X);

        left = new PIDController(C.P, C.I, C.D, leftShaft, leftJ);
        right = new PIDController(C.P, C.I, C.D, rightShaft, rightJ);

        left.enable();
        right.enable();

        level = C.START_LEVEL;
    }

    private void initJags() {
        leftJ = new CANJaguar(C.LEFT_WINCH_ADDRESS);
        rightJ = new CANJaguar(C.RIGHT_WINCH_ADDRESS);

        // leftJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P, I,
        // D);
        // rightJ.setCurrentMode(CANJaguar.kQuadEncoder, PULSES_PER_REV, P, I,
        // D);
        //
        // leftJ.enableControl();
        // rightJ.enableControl();
    }

    public void set(double speed) {
        left.setSetpoint(speed);
        right.setSetpoint(speed);
    }

    public void setLevel(int level) {
        if (level > C.HIGHEST_ARM_LEVEL)
            level = C.HIGHEST_ARM_LEVEL;

        if (level < C.LOWEST_ARM_LEVEL)
            level = C.LOWEST_ARM_LEVEL;

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
