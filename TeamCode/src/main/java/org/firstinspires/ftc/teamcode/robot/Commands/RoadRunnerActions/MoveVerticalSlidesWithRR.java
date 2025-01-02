package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.canvas.Canvas;
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


//        controller.calculate(ref,);
        VerticalSlides.holdPos = false;
        VerticalSlides.ref = ref;
//        verticalSlides.getSlidesPos();
        verticalSlides.pidController();
        if (Math.abs(verticalSlides.getSlidesError()) < 10){
            telemetryPacket.addLine("stop");
            return false;
        }
//        telemetryPacket.();
        return true;
    }
    @Override
    public void preview(Canvas c) {
        c.setStroke("#4CAF507A");
        c.setStrokeWidth(1);
    }
}
