package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;


public class Delay extends Command {

    ElapsedTime timer = new ElapsedTime();

    double delay;

    public Delay (double delay){
        this.delay =delay;
    }
    @Override
    public void init() {
        timer.reset();
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return timer.seconds() > delay;
    }

    @Override
    public void shutdown() {

    }
}
