package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveGripperAction.loopOnceGripper;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetGripperAction implements Action {
    TelemetryPacket packet;

    public ResetGripperAction(TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        packet = telemetryPacket;
        loopOnceGripper = false;
        return false;
    }
}
