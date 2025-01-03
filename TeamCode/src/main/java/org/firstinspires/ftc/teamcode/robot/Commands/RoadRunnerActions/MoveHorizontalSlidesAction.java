package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;

public class MoveHorizontalSlidesAction implements Action {

    HorizontalSlides horizontalslides;

    HorizontalSlides.HorizontalSlideStates horizontalslidestates;

    double target;

    TelemetryPacket packet;

    public MoveHorizontalSlidesAction(HorizontalSlides horizontalslides, HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target,TelemetryPacket telemetryPacket){
        this.horizontalslides = horizontalslides;
        this.horizontalslidestates = horizontalslidestates;
        this.packet = telemetryPacket;
        this.target = target;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        horizontalslides.setHorizontalSlides(horizontalslidestates);
        if (Math.abs(horizontalslides.getSlideError(target)) <= 8){
            return false;
        }
        return true;
    }
}
