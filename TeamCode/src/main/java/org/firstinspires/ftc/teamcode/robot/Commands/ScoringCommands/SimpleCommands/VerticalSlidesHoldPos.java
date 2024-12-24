package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class VerticalSlidesHoldPos extends Command {

    VerticalSlides verticalslides;

//    ElapsedTime time = new ElapsedTime();

//    boolean secondLoop = false;

    public VerticalSlidesHoldPos(VerticalSlides verticalslides){
        this.verticalslides = verticalslides;
    }

    @Override
    public void init() {
        verticalslides.holdPos = true;
//       time.reset();
//       secondLoop = false;
//        verticalslides.getAndSetPower();
//        verticalslides.setPower(.074);
    }

    @Override
    public void periodic() {
//        verticalslides.getAndSetPower();
//        if (time.seconds() > 2 && secondLoop == false){
//            verticalslides.closeSLIDEThread();
//            secondLoop = true;
//        verticalslides.getAndSetPower();
//        verticalslides.setPower(.074);
//            verticalslides.getAndSetPower();
    }

    @Override
    public boolean completed() {
        return true;
    }

    @Override
    public void shutdown() {

    }
}
