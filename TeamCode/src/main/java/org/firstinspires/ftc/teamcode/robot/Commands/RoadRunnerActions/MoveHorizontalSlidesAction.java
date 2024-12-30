package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;

public class MoveHorizontalSlidesAction implements Action {

    HorizontalSlides horizontalslides;

    HorizontalSlides.HorizontalSlideStates horizontalslidestates;

    double target;

    public MoveHorizontalSlidesAction(HorizontalSlides horizontalslides, HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        this.horizontalslides = horizontalslides;
        this.horizontalslidestates = horizontalslidestates;
        this.target = target;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        horizontalslides.setHorizontalSlides(horizontalslidestates);
        return Math.abs(target - horizontalslides.getSlidePos()) <= 8;
    }
}
