package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;

public class MoveHorizontalSlides extends Command {

    ElapsedTime time = new ElapsedTime();

    HorizontalSlides horizontalslides;

    HorizontalSlides.HorizontalSlideStates horizontalslidestates;

    public MoveHorizontalSlides(HorizontalSlides horizontalslides, HorizontalSlides.HorizontalSlideStates horizontalslidestates){
        this.horizontalslides = horizontalslides;
        this.horizontalslidestates = horizontalslidestates;
    }

    @Override
    public void init() {
        time.reset();
        horizontalslides.setHorizontalSlides(horizontalslidestates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return time.seconds() > 1;
    }

    @Override
    public void shutdown() {

    }
}