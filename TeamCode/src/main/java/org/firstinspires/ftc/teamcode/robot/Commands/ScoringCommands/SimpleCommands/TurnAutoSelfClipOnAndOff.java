package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.enableAutoSelfClip;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;

public class TurnAutoSelfClipOnAndOff extends Command {
    @Override
    public void init() {
        if (enableAutoSelfClip) {
            enableAutoSelfClip = false;
        } else if (!enableAutoSelfClip) {
            enableAutoSelfClip = true;
        }
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
