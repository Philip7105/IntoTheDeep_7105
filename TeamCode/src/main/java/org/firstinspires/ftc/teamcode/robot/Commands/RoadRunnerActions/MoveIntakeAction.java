package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

public class MoveIntakeAction implements Action {

    ElapsedTime timer = new ElapsedTime();
    NewIntake johnsIntake;
    NewIntake.IntakeStates intakeStates;
    TelemetryPacket packet;
    public static boolean loopOnceIntake = false;

    public MoveIntakeAction(NewIntake johnsIntake,NewIntake.IntakeStates intakeStates, TelemetryPacket telemetryPacket){
        this.johnsIntake = johnsIntake;
        this.intakeStates = intakeStates;
        this.packet = telemetryPacket;
        loopOnceIntake = false;
    }
    public void init() {
        if (!loopOnceIntake){
            timer.reset();
            loopOnceIntake = true;
        }
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        init();
        johnsIntake.setIntake(intakeStates);
        if (timer.seconds() > 1){
            return false;
        }
        return true;
    }
}
