package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class VerticalHoldPosStates extends Command {

    VerticalSlides verticalSlides;

//    VerticalSlides.VerticalSlideStates holdPosState;

    Input input;

    public VerticalHoldPosStates(VerticalSlides verticalSlides, Input input){
        this.verticalSlides = verticalSlides;
//        this.holdPosState = holdPosState;
        this.input = input;
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
        return false;
    }

    @Override
    public void shutdown() {

    }
}
