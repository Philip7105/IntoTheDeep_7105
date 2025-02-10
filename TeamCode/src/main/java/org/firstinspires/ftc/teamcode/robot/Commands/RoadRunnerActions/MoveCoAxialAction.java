package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

public class MoveCoAxialAction implements Action {
    TelemetryPacket packet;
    NewIntake intake;
    NewIntake.CoaxialStates coaxialStates;
    ElapsedTime time = new ElapsedTime();
    public static boolean runOnceCoAxial = false;
    public MoveCoAxialAction(NewIntake intake, NewIntake.CoaxialStates coaxialStates, TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
        this.intake = intake;
        this.coaxialStates = coaxialStates;
    }

    public void init(){
        if (!runOnceCoAxial){
            time.reset();
            runOnceCoAxial = true;
        }
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        init();
        intake.setCoaxial(coaxialStates);
        if (time.seconds()>.4){
            return false;
        }
        return true;
    }
}
