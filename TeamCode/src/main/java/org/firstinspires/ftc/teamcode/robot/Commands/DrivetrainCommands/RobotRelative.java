package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

public class RobotRelative extends Command {

    DriveTrain driveTrain;

    Input input;

    public RobotRelative(DriveTrain driveTrain, Input input){
      this.driveTrain = driveTrain;
      this.input = input;
    }

    @Override
    public void init() {
    }

    @Override
    public void periodic() {
//        driveTrain.mecanumDrive.setWeightedDrivePower(new Pose2d(
//                -input.getLeft_stick_y(),
//                input.getLeft_stick_x(),
//                -input.getRight_stick_x()));
    }

    @Override
    public boolean completed() {
        return false;
    }

    @Override
    public void shutdown() {

    }
}
