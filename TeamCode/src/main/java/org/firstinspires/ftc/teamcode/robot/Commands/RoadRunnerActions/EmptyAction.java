package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class EmptyAction implements Action {
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        return true;
    }
}
