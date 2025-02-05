package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;


public class MoveGripper extends Command {

    ElapsedTime timer = new ElapsedTime();

    NewIntake johnsIntake;

//    NewIntake.GripperStates gripperStates;

    public MoveGripper(NewIntake johnsIntake,JohnsIntake.GripperStates gripperStates){
        this.johnsIntake = johnsIntake;
//        this.gripperStates = gripperStates;
    }
    @Override
    public void init() {
        timer.reset();
//        johnsIntake.setGripper(gripperStates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return timer.seconds() > .4;
    }

    @Override
    public void shutdown() {

    }
}