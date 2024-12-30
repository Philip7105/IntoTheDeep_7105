package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

public class StrafetoLinearHeadingMultiActions extends Command {
    DriveTrain driveTrain;

//    Vector2d startVec, targetVec;

    Action[] actions;
//    Rotation2d startHeading,targetheading;
    public StrafetoLinearHeadingMultiActions(Action[] actions){
//        this.driveTrain = driveTrain;
//        this.targetVec = targetVec;
//        this.targetheading = targetheading;
        this.actions = actions;
    }


    @Override
    public void init() {
//        startVec = new Vector2d(driveTrain.getXPos(),driveTrain.getYPos());
//        startHeading = driveTrain.getHeading();
        driveTrain.strafetoLinearHeadingList(actions);
    }

    @Override
    public void periodic() {
        //TODO check to see if this was the issue.
    }

    @Override
    public boolean completed() {
        return !driveTrain.mecanumDrive.leftBack.isBusy() && !driveTrain.mecanumDrive.rightBack.isBusy() &&
                !driveTrain.mecanumDrive.leftFront.isBusy() && !driveTrain.mecanumDrive.rightFront.isBusy();
    }

    @Override
    public void shutdown() {
        driveTrain.shutdown();
    }
}
