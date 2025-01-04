package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MovePivotAction.loopOncePivot;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetPivotAction implements Action {
    TelemetryPacket packet;
    public ResetPivotAction(TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
    }
    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        loopOncePivot = false;
        return false;
    }
}
