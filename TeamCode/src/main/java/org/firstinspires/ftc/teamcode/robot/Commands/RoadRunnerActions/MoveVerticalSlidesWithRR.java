package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class MoveVerticalSlidesWithRR implements Action {

    VerticalSlides verticalSlides;

    double ref;

    public MoveVerticalSlidesWithRR(VerticalSlides verticalSlides, double ref){
        this.verticalSlides = verticalSlides;
        this.ref = ref;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        VerticalSlides.ref = ref;
        verticalSlides.pidController();
        return Math.abs(verticalSlides.getSlidesError()) < 10;
    }
}
