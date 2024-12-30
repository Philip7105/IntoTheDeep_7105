package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.BetterAction;

public class MultiActionsToCommand extends Command {

    BetterAction betterAction;

    Action[] actions;

    public boolean completed = false;

    TelemetryPacket telemetryPacket;

    public MultiActionsToCommand(Action[] actions,TelemetryPacket telemetryPacket){
        this.actions = actions;
        this.telemetryPacket = telemetryPacket;
        betterAction = new BetterAction(actions);
    }

    @Override
    public void init() {
        completed = false;
        Actions.runBlocking(betterAction);
    }

    @Override
    public void periodic() {
        if (betterAction.run(telemetryPacket)){
            completed = true;
        }
    }

    @Override
    public boolean completed() {
        return completed;
    }

    @Override
    public void shutdown() {

    }
}
