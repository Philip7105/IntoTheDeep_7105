package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;
import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveCoAxialAction.runOnceCoAxial;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetCoAxialAction implements Action {
    TelemetryPacket packet;
    public ResetCoAxialAction(TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        runOnceCoAxial = false;
        return false;
    }
}
