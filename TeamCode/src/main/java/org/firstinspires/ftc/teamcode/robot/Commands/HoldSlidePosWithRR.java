package org.firstinspires.ftc.teamcode.robot.Commands;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class HoldSlidePosWithRR implements Action {

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        VerticalSlides.holdPos = true;
        return true;
    }
}
