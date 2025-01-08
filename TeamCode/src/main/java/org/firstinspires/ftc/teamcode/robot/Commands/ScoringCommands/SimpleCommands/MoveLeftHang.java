package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.readySecondHang;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.secondHang;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;

public class MoveLeftHang extends Command {
    JohnHanging hanging;
    JohnHanging.LeftHangStates leftHangStates,nextHangState;
    double ref;
    boolean complete;

    public MoveLeftHang(JohnHanging hanging, JohnHanging.LeftHangStates leftHangStates, JohnHanging.LeftHangStates nextHangState, double ref){
        this.hanging = hanging;
        this.ref = ref;
        this.leftHangStates = leftHangStates;
        this.nextHangState = nextHangState;
    }

    @Override
    public void init() {
        complete = false;
        if (leftHangStates == JohnHanging.LeftHangStates.READYFIRSTHANG&& JohnHanging.leftReadyFirstHangDone){
            leftHangStates = JohnHanging.LeftHangStates.READYSECONDHANG;
            ref = readySecondHang;
        } else if (leftHangStates == JohnHanging.LeftHangStates.FIRSTHANG && JohnHanging.leftFirstHangDone) {
            leftHangStates = JohnHanging.LeftHangStates.SECONDHANG;
            ref = secondHang;
        }
    }

    @Override
    public void periodic() {
        if(Math.abs(hanging.getLeftHangError(ref)) < 10){
            complete = true;
        } else {
            hanging.setLeftHang(leftHangStates);
            complete = false;
        }
    }

    @Override
    public boolean completed() {
        return complete;
    }

    @Override
    public void shutdown() {
        if (leftHangStates == JohnHanging.LeftHangStates.READYFIRSTHANG && !JohnHanging.leftReadyFirstHangDone){
            JohnHanging.leftReadyFirstHangDone = true;
        } else if (leftHangStates == JohnHanging.LeftHangStates.FIRSTHANG &&!JohnHanging.leftFirstHangDone) {
            JohnHanging.leftFirstHangDone = true;
        }
        hanging.setLeftHang(nextHangState);
    }
}
