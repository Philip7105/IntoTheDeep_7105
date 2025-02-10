package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.blueSample;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.redSample;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.yellowSample;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;

public class NoSamples extends Command {
    @Override
    public void init() {
        blueSample = false;
        redSample = false;
        yellowSample = false;
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return true;
    }

    @Override
    public void shutdown() {

    }
}
