package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

public class MoveArmFeedBack extends Command {

    JohnsIntake intake;

    public MoveArmFeedBack(JohnsIntake intake){
        this.intake = intake;
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
