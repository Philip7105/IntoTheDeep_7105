package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

public class MoveGripperAction implements Action {

    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.GripperStates gripperStates;

    public static boolean loopOnceGripper = false;

    public MoveGripperAction(JohnsIntake johnsIntake,JohnsIntake.GripperStates gripperStates){
        this.johnsIntake = johnsIntake;
        this.gripperStates = gripperStates;
        loopOnceGripper = false;
    }
    public void init() {
        if (!loopOnceGripper){
            timer.reset();
            loopOnceGripper = true;
        }
    }

    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        init();
        johnsIntake.setGripper(gripperStates);
        return timer.seconds() > .4;
    }
}
