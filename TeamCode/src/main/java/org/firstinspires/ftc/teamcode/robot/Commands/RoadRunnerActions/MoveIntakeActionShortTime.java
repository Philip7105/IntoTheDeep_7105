package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

public class MoveIntakeActionShortTime implements Action {

    ElapsedTime timer = new ElapsedTime();
    JohnsIntake johnsIntake;
    JohnsIntake.IntakeStates intakeStates;
    TelemetryPacket packet;
    public static boolean loopOnceIntake = false;

    public MoveIntakeActionShortTime(JohnsIntake johnsIntake, JohnsIntake.IntakeStates intakeStates, TelemetryPacket telemetryPacket){
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
        if (timer.seconds() > .35){
            return false;
        }
        return true;
    }
}
