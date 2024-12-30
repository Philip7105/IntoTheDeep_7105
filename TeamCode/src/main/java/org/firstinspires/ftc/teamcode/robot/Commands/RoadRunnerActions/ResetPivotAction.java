package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MovePivotAction.loopOncePivot;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetPivotAction implements Action {
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        loopOncePivot = false;
        return true;
    }
}
