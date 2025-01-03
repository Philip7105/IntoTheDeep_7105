package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.CommandFrameWork.ActionChill;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MultipleActions;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

public class MultipleRRActionsWithPathing extends Command {

    MultipleActions multipleActions;

    public MultipleRRActionsWithPathing(Action[] actions){
        multipleActions = new MultipleActions(actions,Dashboard.packet);
    }

    @Override
    public void init() {
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
