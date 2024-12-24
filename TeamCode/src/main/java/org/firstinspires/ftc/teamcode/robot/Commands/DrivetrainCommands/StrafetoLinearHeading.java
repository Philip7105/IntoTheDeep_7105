package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;


import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class StrafetoLinearHeading extends Command {
    Robot robot;
    Vector2d startVec, targetVec;

    Rotation2d startHeading,targetheading;

    public StrafetoLinearHeading(Vector2d targetVec, Rotation2d targetheading, Robot robot){
        this.robot = robot;
//        this.startVec = startVec;
//        this.startHeading = startHeading;
        this.targetVec = targetVec;
        this.targetheading = targetheading;
    }


    @Override
    public void init() {
        startVec = new Vector2d(robot.driveTrain.getXPos(),robot.driveTrain.getYPos());
        startHeading = robot.driveTrain.getHeading();
        robot.driveTrain.strafeToLinearHeading(startVec,startHeading,targetVec,targetheading);
//        robot.driveTrain.strafeToLinearHeading(new Vector2d(-15.8,59.7), Rotation2d.exp(Math.toRadians(90)),new Vector2d(-10,46),90);
//        robot.driveTrain.followTrajectory(traj);
    }

    @Override
    public void periodic() {
        //TODO check to see if this was the issue.
    }

    @Override
    public boolean completed() {
//        return false;
//        TODO This needs to be fixed. It will currently loop infinately.
        return !robot.driveTrain.mecanumDrive.leftBack.isBusy() && !robot.driveTrain.mecanumDrive.rightBack.isBusy() &&
        !robot.driveTrain.mecanumDrive.leftFront.isBusy() && !robot.driveTrain.mecanumDrive.rightFront.isBusy();
    }

    @Override
    public void shutdown() {
        robot.driveTrain.shutdown();
    }
}
