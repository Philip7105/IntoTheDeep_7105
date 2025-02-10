package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;


public class MoveIntakeJohn extends Command {

    ElapsedTime timer = new ElapsedTime();

    NewIntake johnsIntake;

    NewIntake.IntakeStates intakeStates;

    double time;

    public MoveIntakeJohn(NewIntake johnsIntake, NewIntake.IntakeStates intakeStates,double time){
        this.time = time;
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
        return timer.seconds() > time;
    }

    @Override
    public void shutdown() {
    }
}