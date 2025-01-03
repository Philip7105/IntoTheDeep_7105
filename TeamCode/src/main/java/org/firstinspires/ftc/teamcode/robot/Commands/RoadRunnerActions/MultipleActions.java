package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BetterParallelAction;

import java.util.Arrays;

public class MultipleActions implements Action {
    BetterParallelAction parallelAction;
    public boolean isDone;
    TelemetryPacket packet;

    public MultipleActions(Action[] action, TelemetryPacket telemetryPacket){
        this.packet =telemetryPacket;
        parallelAction= new BetterParallelAction(Arrays.asList(action));
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        parallelAction.run(telemetryPacket);
        if (parallelAction.getInitialActions().isEmpty()){
            isDone = true;
            return isDone;
        }
        isDone = false;
        return isDone;
    }
}
