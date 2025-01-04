package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;

public class MoveIntakeWrist extends Command {

    Intake.Wrist wrist;
    Intake intake;
    ElapsedTime timer = new ElapsedTime();

    public MoveIntakeWrist(Intake intake, Intake.Wrist wrist, Input input){
        this.wrist = wrist;
        this.intake = intake;
    }

    @Override
    public void init() {
        intake.setWrist(wrist);
    }

    @Override
    public void periodic() {
    }

    @Override
    public boolean completed() {
        return (timer.seconds() > 5);
    }

    @Override
    public void shutdown() {

    }
}
