package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.Intake;


public class MoveIntake extends Command {

    Intake intake;

    Intake.Wrist intakeWristStates;

    Intake.Twist intakeTwistStates;

    Intake.IntakePower intakeStates;

    Input input;
    ElapsedTime timer = new ElapsedTime();

    public MoveIntake(Intake intake, Intake.IntakePower intakeStates, Intake.Wrist intakeWristStates, Intake.Twist intakeTwistStates, Input input){
        this.intake = intake;
        this.intakeStates = intakeStates;
        this.intakeWristStates = intakeWristStates;
        this.intakeTwistStates = intakeTwistStates;
        this.input = input;
    }

    @Override
    public void init() {
        intake.setIntakePower(intakeStates);
        intake.setWrist(intakeWristStates);
        intake.setTwist(intakeTwistStates);
        timer.reset();
    }

    @Override
    public void periodic() {

    }
    // ||
    @Override
    public boolean completed() {
        if (this.intakeTwistStates == Intake.Twist.PlacingSpecimin) {
            return (timer.seconds() > 10.0);

        } else {
            return (timer.seconds() > 5.0);
        }


    }

    @Override
    public void shutdown() {
        intake.setIntakePower(Intake.IntakePower.Stop);
        intake.setTwist(Intake.Twist.Rest);
        intake.setWrist(Intake.Wrist.Rest);
    }
}
