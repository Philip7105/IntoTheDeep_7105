package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;

public class MoveVerticalSlidesBetter extends Command {
    VerticalSlides verticalSlides;
    boolean setTarget;
    double ref;
    public MoveVerticalSlidesBetter(VerticalSlides verticalSlides, boolean setTarget, double ref){
        this.verticalSlides = verticalSlides;
        this.setTarget = setTarget;
        this.ref = ref;
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
        verticalSlides.pidfController();
    }
    @Override
    public boolean completed() {
        return Math.abs(verticalSlides.getSlidesError()) < 10;
    }
    @Override
    public void shutdown() {
        verticalSlides.holdPos = true;
    }
}
