package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.Intake;

public class MoveIntakeWheel extends Command {

    Intake.IntakePower intakeStates;
    Intake intake;
    ElapsedTime timer = new ElapsedTime();

    public MoveIntakeWheel(Intake intake, Intake.IntakePower intakestates, Input input){
        this.intakeStates = intakestates;
        this.intake = intake;
    }

    @Override
    public void init() {
        intake.setIntakePower(intakeStates);
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
