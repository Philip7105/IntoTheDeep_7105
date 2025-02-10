package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class MoveVerticalSlidesBetter extends Command {

    VerticalSlides verticalSlides;

    boolean setTarget;

    double ref,tolerance;

    public MoveVerticalSlidesBetter(VerticalSlides verticalSlides, boolean setTarget, double ref, double tolerance){
        this.verticalSlides = verticalSlides;
        this.setTarget = setTarget;
        this.ref = ref;
        this.tolerance = tolerance;
    }

    @Override
    public void init() {
        verticalSlides.holdPos = false;
        if (setTarget){
            VerticalSlides.ref = ref;
        }
    }

    @Override
    public void periodic() {
        verticalSlides.pdController();
    }

    @Override
    public boolean completed() {
        return Math.abs(verticalSlides.getSlidesError()) < tolerance;
    }

    @Override
    public void shutdown() {
        verticalSlides.holdPos = true;
    }
}
