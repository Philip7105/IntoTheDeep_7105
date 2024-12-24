package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class SlidesChill extends Command {

    VerticalSlides verticalslides;

//    ElapsedTime time = new ElapsedTime();

//    boolean secondLoop = false;

    public SlidesChill(VerticalSlides verticalslides){
        this.verticalslides = verticalslides;
    }

    @Override
    public void init() {

        verticalslides.zeroPower();
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return true;
    }

    @Override
    public void shutdown() {

    }
}
