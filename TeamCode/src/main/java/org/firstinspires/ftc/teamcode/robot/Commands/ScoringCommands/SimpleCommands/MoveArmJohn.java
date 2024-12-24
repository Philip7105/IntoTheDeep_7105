package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;


public class MoveArmJohn extends Command {

    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.ArmStates armStates;

    public MoveArmJohn(JohnsIntake johnsIntake, JohnsIntake.ArmStates armStates){
        this.johnsIntake = johnsIntake;
        this.armStates = armStates;
    }
    @Override
    public void init() {
        timer.reset();
        johnsIntake.setArmStates(armStates);
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