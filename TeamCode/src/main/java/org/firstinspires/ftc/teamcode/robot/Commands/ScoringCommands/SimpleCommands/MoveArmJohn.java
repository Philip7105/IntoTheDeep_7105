package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;


public class MoveArmJohn extends Command {

    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.PivotStates pivotStates;

    public MoveArmJohn(JohnsIntake johnsIntake, JohnsIntake.PivotStates pivotStates){
        this.johnsIntake = johnsIntake;
        this.pivotStates = pivotStates;
    }
    @Override
    public void init() {
        timer.reset();
        johnsIntake.setArmStates(pivotStates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return timer.seconds() > 1;
    }

    @Override
    public void shutdown() {

    }
}