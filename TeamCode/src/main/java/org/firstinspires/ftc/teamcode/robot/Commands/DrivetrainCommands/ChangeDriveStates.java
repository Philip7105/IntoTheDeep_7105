package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

public class ChangeDriveStates extends Command {

    DriveTrain.DriveSpeed driveSpeed;

    public ChangeDriveStates(DriveTrain.DriveSpeed driveSpeed){
        this.driveSpeed = driveSpeed;
    }

    @Override
    public void init() {
        DriveTrain.driveSpeed = driveSpeed;
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
