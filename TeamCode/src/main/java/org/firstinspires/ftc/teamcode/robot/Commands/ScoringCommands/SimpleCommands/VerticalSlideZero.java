package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class VerticalSlideZero extends Command {

    VerticalSlides verticalSlides;

    public VerticalSlideZero(VerticalSlides verticalSlides){
        this.verticalSlides = verticalSlides;
    }

    @Override
    public void init() {
        verticalSlides.zeroPower();
    }

    @Override
    public void periodic() {
        verticalSlides.zeroPower();
    }

    @Override
    public boolean completed() {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
