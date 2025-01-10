package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.readySecondHang;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.secondHang;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;

public class MoveRightHang extends Command {
    JohnHanging hanging;
    JohnHanging.RightHangStates rightHangStates,nextHangState;
    double ref;
    boolean complete = false;

    public MoveRightHang(JohnHanging hanging, JohnHanging.RightHangStates rightHangStates, JohnHanging.RightHangStates nextHangState, double ref){
        this.hanging = hanging;
        this.ref = ref;
        this.rightHangStates = rightHangStates;
        this.nextHangState = nextHangState;
    }

    @Override
    public void init() {
        complete = false;
//        if (rightHangStates == JohnHanging.RightHangStates.READYFIRSTHANG&& JohnHanging.rightReadyFirstHangDone){
//            rightHangStates = JohnHanging.RightHangStates.READYSECONDHANG;
//            ref = readySecondHang;
//        } else if (rightHangStates == JohnHanging.RightHangStates.FIRSTHANG && JohnHanging.rightFirstHangDone) {
//            rightHangStates = JohnHanging.RightHangStates.SECONDHANG;
//            ref = secondHang;
//        }
    }

    @Override
    public void periodic() {
        if(Math.abs(hanging.getRightHangError(ref)) < 10){
            complete = true;
        } else {
            hanging.setRightHang(rightHangStates);
            complete = false;
        }
    }

    @Override
    public boolean completed() {
        return complete;
    }

    @Override
    public void shutdown() {
//        if (rightHangStates == JohnHanging.RightHangStates.READYFIRSTHANG && !JohnHanging.rightReadyFirstHangDone){
//            JohnHanging.rightReadyFirstHangDone = true;
//        } else if (rightHangStates == JohnHanging.RightHangStates.FIRSTHANG &&!JohnHanging.rightFirstHangDone) {
//            JohnHanging.rightFirstHangDone = true;
//        }
        hanging.setRightHang(nextHangState);
    }
}
