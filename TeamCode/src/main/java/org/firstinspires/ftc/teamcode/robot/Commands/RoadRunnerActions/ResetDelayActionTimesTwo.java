package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.DelayActionTimesTwo.*;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetDelayActionTimesTwo implements Action {
    TelemetryPacket packet;
    public ResetDelayActionTimesTwo(TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        runOnceDelayTwo = false;
        return false;
    }
}
