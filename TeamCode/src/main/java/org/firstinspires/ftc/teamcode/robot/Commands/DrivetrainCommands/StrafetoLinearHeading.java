package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;


import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Commands.HoldSlidePosWithRR;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Robot;

@Deprecated
public class StrafetoLinearHeading extends Command {
    Robot robot;
    Vector2d startVec, targetVec;

    ScoringCommandGroups groups;

    Rotation2d startHeading,targetheading;

    double target;
    public StrafetoLinearHeading(Vector2d targetVec, Rotation2d targetheading,double target,Robot robot,ScoringCommandGroups groups){
        this.robot = robot;
        this.targetVec = targetVec;
        this.targetheading = targetheading;
        this.groups = groups;
        this.target = target;
    }


    @Override
    public void init() {
        startVec = new Vector2d(robot.driveTrain.getXPos(),robot.driveTrain.getYPos());
        startHeading = robot.driveTrain.getHeading();
        robot.driveTrain.strafeToLinearHeadingParallelAction(startVec,startHeading,targetVec,targetheading,groups.moveVerticalSlidesWithRR(target),new HoldSlidePosWithRR());
    }

    @Override
    public void periodic() {
        //TODO check to see if this was the issue.
        robot.driveTrain.strafeToLinearHeadingParallelAction(startVec,startHeading,targetVec,targetheading,groups.moveVerticalSlidesWithRR(target),new HoldSlidePosWithRR());
    }

    @Override
    public boolean completed() {
        return !robot.driveTrain.mecanumDrive.leftBack.isBusy() && !robot.driveTrain.mecanumDrive.rightBack.isBusy() &&
        !robot.driveTrain.mecanumDrive.leftFront.isBusy() && !robot.driveTrain.mecanumDrive.rightFront.isBusy();
    }

    @Override
    public void shutdown() {
        robot.driveTrain.shutdown();
    }
}
