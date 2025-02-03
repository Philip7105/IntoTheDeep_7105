package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;


public class MovePivotTimeBased extends Command {

    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.PivotStates pivotStates;

    public MovePivotTimeBased(JohnsIntake johnsIntake, JohnsIntake.PivotStates pivotStates){
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
        return timer.seconds() > 1.5;
    }

    @Override
    public void shutdown() {

    }
}