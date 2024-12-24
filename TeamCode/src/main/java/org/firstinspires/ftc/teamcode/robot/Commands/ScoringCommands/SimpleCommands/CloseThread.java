package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class CloseThread extends Command {
    VerticalSlides verticalslides;

    ElapsedTime time = new ElapsedTime();

    boolean secondLoop = false;

    public CloseThread(VerticalSlides verticalslides){
        this.verticalslides = verticalslides;
    }

    @Override
    public void init() {
        time.reset();
        secondLoop = false;
    }

    @Override
    public void periodic() {
//        verticalslides.getAndSetPower();
        //TODO: Check to see if we can remove the timer
//        verticalslides
//        verticalslides.setPower(.074);
//        verticalslides.holdPos = true;
        if (time.seconds() > 1 && secondLoop == false){
            secondLoop = true;
//        verticalslides.getAndSetPower();
        }
    }

    @Override
    public boolean completed() {
        return secondLoop == true;
    }

    @Override
    public void shutdown() {
//        verticalslides.zeroPower();
    }
}
