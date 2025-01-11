package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

public class MovePivotAction implements Action {
    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.PivotStates pivotStates;

    public static boolean loopOncePivot = false;

    TelemetryPacket packet;

    public MovePivotAction(JohnsIntake johnsIntake, JohnsIntake.PivotStates pivotStates, TelemetryPacket telemetryPacket){
        this.johnsIntake = johnsIntake;
        this.packet = telemetryPacket;
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
    public boolean run(TelemetryPacket telemetryPacket) {
        init();
        johnsIntake.setPivotStates(pivotStates);
        if (timer.seconds() > 1){
            return false;
        }
        return true;
    }
}
