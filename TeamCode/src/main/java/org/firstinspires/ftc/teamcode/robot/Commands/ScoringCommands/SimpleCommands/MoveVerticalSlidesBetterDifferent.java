package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class MoveVerticalSlidesBetterDifferent extends Command {

    VerticalSlides verticalSlides;

//    VerticalSlides.VerticalSlideStates verticalSlideStates;

//    boolean setTarget;

//    double ref;

    Input input;

    public MoveVerticalSlidesBetterDifferent(VerticalSlides verticalSlides, Input input){
        this.verticalSlides = verticalSlides;
        this.input = input;
//        this.setTarget = setTarget;
//        this.verticalSlideStates = verticalSlideStates;
//        this.ref = ref;
    }
    @Override
    public void init() {
        verticalSlides.setVerticalSlideStates(input);
    }
    @Override
    public void periodic() {
        verticalSlides.setVerticalSlideStates(input);
    }
    @Override
    public boolean completed() {
        return Math.abs(verticalSlides.getSlidesError()) < 10;
    }
    @Override
    public void shutdown() {
        new VerticalHoldPosStates(verticalSlides,input);
    }
}
