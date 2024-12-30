package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;


import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;

public class BetterActionToCommand extends Command {
    Action action;

    public  boolean completed = false;

    TelemetryPacket telemetryPacket;

    public BetterActionToCommand(Action action,TelemetryPacket telemetryPacket){
        this.action = action;
        this.telemetryPacket = telemetryPacket;
    }

    @Override
    public void init() {
        completed = false;
        Actions.runBlocking(action);
    }

    @Override
    public void periodic() {
        if (action.run(telemetryPacket)){
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
