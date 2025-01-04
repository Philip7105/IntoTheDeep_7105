package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;


public class MoveIntakeJohn extends Command {

    ElapsedTime timer = new ElapsedTime();

    JohnsIntake johnsIntake;

    JohnsIntake.IntakeStates intakeStates;

    public MoveIntakeJohn(JohnsIntake johnsIntake, JohnsIntake.IntakeStates intakeStates){

        this.johnsIntake = johnsIntake;
        this.intakeStates = intakeStates;
    }
    @Override
    public void init() {
        timer.reset();
        johnsIntake.setIntake(intakeStates);
    }

    @Override
    public void periodic() {
    }

    @Override
    public boolean completed() {
        return timer.seconds() > 0.4;
    }

    @Override
    public void shutdown() {
    }
}