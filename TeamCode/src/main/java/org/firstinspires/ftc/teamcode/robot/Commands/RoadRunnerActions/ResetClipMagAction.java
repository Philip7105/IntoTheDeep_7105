package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;


import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveClipMagAction.*;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetClipMagAction implements Action {
    TelemetryPacket packet;
    public ResetClipMagAction(TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        loopClipMagOnce = false;
        return false;
    }
}
