package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;

public class MoveHorizontalSlidesTimeBased extends Command {

    HorizontalSlides horizontalslides;
ElapsedTime timer = new ElapsedTime();
    HorizontalSlides.HorizontalSlideStates horizontalslidestates;

    public MoveHorizontalSlidesTimeBased(HorizontalSlides horizontalslides, HorizontalSlides.HorizontalSlideStates horizontalslidestates){
        this.horizontalslides = horizontalslides;
        this.horizontalslidestates = horizontalslidestates;
    }

    @Override
    public void init() {
        timer.reset();
        horizontalslides.setHorizontalSlides(horizontalslidestates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return timer.seconds() > 1.2;
    }

    @Override
    public void shutdown() {

    }
}
