package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class HoldVerticalSlidePosAction implements Action {
    TelemetryPacket packet;
    VerticalSlides verticalSlides;
    public HoldVerticalSlidePosAction(VerticalSlides verticalSlides,TelemetryPacket telemetryPacket){
        this.verticalSlides = verticalSlides;
        this.packet = telemetryPacket;
    }
    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        verticalSlides.holdPos = true;
        return false;
    }
}
