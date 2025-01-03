package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.ActionChill;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

public class StrafeToLinearHeadingXMarker extends Command {
    DriveTrain driveTrain;
    public StrafeToLinearHeadingXMarker(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
    }

    @Override
    public void init() {
        ActionChill.runBlockingChill();
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
