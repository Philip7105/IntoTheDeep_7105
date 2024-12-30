package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

public class MoveIntakeAction implements Action {

    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.IntakeStates intakeStates;

    public static boolean loopOnceIntake = false;

    public MoveIntakeAction(JohnsIntake johnsIntake,JohnsIntake.IntakeStates intakeStates){
        this.johnsIntake = johnsIntake;
        this.intakeStates = intakeStates;
        loopOnceIntake = false;
    }
    public void init() {
        if (!loopOnceIntake){
            timer.reset();
            loopOnceIntake = true;
        }
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        init();
        johnsIntake.setIntake(intakeStates);
        return timer.seconds() > 1;
    }
}
