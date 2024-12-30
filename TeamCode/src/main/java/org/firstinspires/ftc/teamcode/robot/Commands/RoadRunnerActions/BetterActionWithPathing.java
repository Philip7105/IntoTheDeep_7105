package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;

public class BetterActionWithPathing implements Action {
    Action[] action;
//    Action runPath;
    ParallelAction parallelAction;

    boolean complete;

    public BetterActionWithPathing(Action[] action){
        this.action = action;
//        this.runPath = runPath;
        parallelAction= new ParallelAction(action);
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
//        runPath.run(telemetryPacket);
        parallelAction.run(telemetryPacket);
        if (parallelAction.run(telemetryPacket)){
            complete = true;
        } else {
            complete = false;
        }
            return complete;
    }
}
