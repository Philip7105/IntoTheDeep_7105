package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;
import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.DelayAction.runOnce;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetDelayAction implements Action {
    TelemetryPacket packet;
    public ResetDelayAction(TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        runOnce = false;
        return false;
    }
}
