package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;


public class LockPosition extends Command {

    DriveTrain driveTrain;

    Pose2d target;

    ElapsedTime time = new ElapsedTime();

    public LockPosition(DriveTrain driveTrain, Pose2d target){
        this.driveTrain =driveTrain;
        this.target = target;
    }

    @Override
    public void init() {
        time.reset();
//        driveTrain.lockPosition(target);
        driveTrain.mecanumDrive.updatePoseEstimate();
    }

    @Override
    public void periodic() {
//        driveTrain.lockPosition(target);
        driveTrain.mecanumDrive.updatePoseEstimate();
    }

    @Override
    public boolean completed() {
        return time.seconds() > 1000000;
    }

    @Override
    public void shutdown() {

    }
}
