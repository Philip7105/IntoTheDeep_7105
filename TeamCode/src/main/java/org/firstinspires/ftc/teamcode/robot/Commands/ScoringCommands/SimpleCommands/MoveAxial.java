package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

public class MoveAxial extends Command {

    NewIntake intake;

    NewIntake.CoaxialStates coaxialStates;

    ElapsedTime time = new ElapsedTime();

    public MoveAxial(NewIntake intake, NewIntake.CoaxialStates coaxialStates){
        this.intake = intake;
        this.coaxialStates = coaxialStates;
    }

    @Override
    public void init() {
        time.reset();
        intake.setCoaxial(coaxialStates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return time.seconds() > .4;
    }

    @Override
    public void shutdown() {

    }
}
