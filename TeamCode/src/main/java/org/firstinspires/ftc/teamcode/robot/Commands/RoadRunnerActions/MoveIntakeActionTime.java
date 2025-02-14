package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

public class MoveIntakeActionTime implements Action {

    ElapsedTime timer = new ElapsedTime();
    NewIntake johnsIntake;
    NewIntake.IntakeStates intakeStates;
    double time;
    TelemetryPacket packet;
    public static boolean loopOnceIntake = false;

    public MoveIntakeActionTime(NewIntake johnsIntake, NewIntake.IntakeStates intakeStates, TelemetryPacket telemetryPacket, double time){
        this.johnsIntake = johnsIntake;
        this.intakeStates = intakeStates;
        this.packet = telemetryPacket;
        this.time = time;
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
        if (timer.seconds() > time){
            return false;
        }
        return true;
    }
}