package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveGripperAction.loopOnceGripper;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetGripperAction implements Action {


    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        loopOnceGripper = false;
        return true;
    }
}
