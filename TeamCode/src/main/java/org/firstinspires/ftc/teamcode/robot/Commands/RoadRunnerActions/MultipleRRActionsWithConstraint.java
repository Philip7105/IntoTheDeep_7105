package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.CommandFrameWork.ActionChill;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.NewRR.MecanumDrive;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MultipleActions;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

public class MultipleRRActionsWithConstraint extends Command {

    MultipleActions multipleActions;

    public MultipleRRActionsWithConstraint (Action[] actions){
        multipleActions = new MultipleActions(actions,Dashboard.packet);
    }

    @Override
    public void init() {
        MecanumDrive.vel = 12;
        MecanumDrive.error= 8;
        ActionChill.runBlockingChill(multipleActions);
    }

    @Override
    public void periodic() {
        ActionChill.runBlockingChill(multipleActions);
    }

    @Override
    public boolean completed() {
        return multipleActions.isDone;
    }

    @Override
    public void shutdown() {

    }
}
