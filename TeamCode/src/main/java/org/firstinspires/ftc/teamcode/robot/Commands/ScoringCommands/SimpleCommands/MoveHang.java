package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;

public class MoveHang extends Command {
    JohnHanging hanging;
    JohnHanging.HangStates hangStates;
//    ElapsedTime timer = new ElapsedTime();

    double ref, power;

    public MoveHang(JohnHanging hanging, double power, double ref){
        this.hanging = hanging;
//        this.hangStates = hangStates;
        this.power = power;
        this.ref = ref;
    }

    @Override
    public void init() {
//        hanging.setRightHang(hangStates);
        hanging.setPower(power);
//        timer.reset();
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return Math.abs(hanging.getError(ref)) < 10;
    }

    @Override
    public void shutdown() {
//        hanging.setRightHang(JohnHanging.HangStates.ZERO_POWER);
        hanging.setPower(0);
    }
}
