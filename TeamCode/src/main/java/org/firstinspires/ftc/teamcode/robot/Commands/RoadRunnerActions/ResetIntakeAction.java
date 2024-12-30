package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;


import static org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeAction.*;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

public class ResetIntakeAction implements Action {
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        loopOnceIntake = false;
        return true;
    }
}
