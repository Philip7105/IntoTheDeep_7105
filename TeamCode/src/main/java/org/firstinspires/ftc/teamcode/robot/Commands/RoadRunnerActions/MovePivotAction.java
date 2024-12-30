package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

public class MovePivotAction implements Action {
    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.PivotStates pivotStates;

    public static boolean loopOncePivot = false;

    public MovePivotAction(JohnsIntake johnsIntake, JohnsIntake.PivotStates pivotStates){
        this.johnsIntake = johnsIntake;
        this.pivotStates = pivotStates;
        loopOncePivot = false;
    }

    public void init() {
        if (!loopOncePivot){
            timer.reset();
            loopOncePivot = true;
        }
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        init();
        johnsIntake.setArmStates(pivotStates);
        return timer.seconds() > 1;
    }
}
