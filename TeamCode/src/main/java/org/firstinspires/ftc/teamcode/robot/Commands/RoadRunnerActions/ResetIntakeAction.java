package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;


import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeAction.*;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetIntakeAction implements Action {
    TelemetryPacket packet;
    public ResetIntakeAction(TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
    }
    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        loopOnceIntake = false;
        return false;
    }
}
