package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class MoveVerticalSlidesAction implements Action {

    VerticalSlides verticalSlides;

    double ref,tolerance;

    TelemetryPacket packet;

    public MoveVerticalSlidesAction(VerticalSlides verticalSlides, double ref,double tolerance, TelemetryPacket telemetryPacket){
        this.verticalSlides = verticalSlides;
        this.ref = ref;
        this.tolerance = tolerance;
        this.packet = telemetryPacket;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        VerticalSlides.holdPos = false;
        VerticalSlides.ref = ref;
        verticalSlides.pdController();
        if (Math.abs(verticalSlides.getSlidesError()) < tolerance){
            return false;
        }
        return true;
    }
}
