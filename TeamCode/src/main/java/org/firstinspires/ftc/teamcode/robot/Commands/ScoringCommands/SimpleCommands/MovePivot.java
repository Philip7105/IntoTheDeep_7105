package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;


public class MovePivot extends Command {

    ElapsedTime timer = new ElapsedTime();

    NewIntake johnsIntake;

    NewIntake.PivotStates pivotStates;

    public MovePivot(NewIntake johnsIntake, NewIntake.PivotStates pivotStates){
        this.johnsIntake = johnsIntake;
        this.pivotStates = pivotStates;
    }
    @Override
    public void init() {
        timer.reset();
        johnsIntake.setPivotStates(pivotStates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return timer.seconds() > .7;
    }

    @Override
    public void shutdown() {

    }
}