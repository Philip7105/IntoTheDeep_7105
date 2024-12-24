package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class MoveVerticalSlides extends Command {

    VerticalSlides verticalSlides;

    ElapsedTime time = new ElapsedTime();

//    double ref;
Input input;
    public MoveVerticalSlides(VerticalSlides verticalSlides, Input input){
        this.verticalSlides = verticalSlides;
        this.input = input;
//        this.ref = ref;
    }

    @Override
    public void init() {
        time.reset();
    }

    @Override
    public void periodic() {
//        verticalSlides.updatePos(input);
        if (Math.abs(verticalSlides.getSlidesError()) > 20){
            verticalSlides.pidController();
        } else if ( verticalSlides.ref == 0) {
//            verticalSlides.getAndSetPower();
            verticalSlides.zeroPower();
        } else {
            verticalSlides.getAndSetPower();
//            time.reset();
        }
    }

    @Override
    public boolean completed() {
        return verticalSlides.ref == 0;
    }

    @Override
    public void shutdown() {

    }
}