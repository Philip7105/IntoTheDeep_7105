package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;

public class HorizontalCommandsLowerTolerance extends Command {

    HorizontalSlides horizontalslides;

    HorizontalSlides.HorizontalSlideStates horizontalslidestates;

    double target;

    public HorizontalCommandsLowerTolerance(HorizontalSlides horizontalslides, HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        this.horizontalslides = horizontalslides;
        this.horizontalslidestates = horizontalslidestates;
        this.target = target;
    }

    @Override
    public void init() {
        horizontalslides.setHorizontalSlides(horizontalslidestates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return Math.abs(target - horizontalslides.getSlidePos()) <= 3;
    }

    @Override
    public void shutdown() {

    }
}
