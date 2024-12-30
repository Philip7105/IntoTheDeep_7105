package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;

public class BetterAction implements Action {
    Action[] action;
    ParallelAction parallelAction;

    boolean complete;

    public BetterAction(Action[] action){
        this.action = action;
        parallelAction= new ParallelAction(action);
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        parallelAction.run(telemetryPacket);
        if (parallelAction.run(telemetryPacket)){
            complete = true;
        } else {
            complete = false;
        }
        return complete;
    }
}
