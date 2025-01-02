package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

public class StrafeToLinearHeadingCringe extends Command {
    DriveTrain driveTrain;
    Vector2d targetVecter;
    Rotation2d targetHeading;
    Action verticalslidesaction,horizontalslidesaction,pivotaction,gripperaction,intakeAction;
    public StrafeToLinearHeadingCringe(Vector2d targetVecter, Rotation2d targetHeading,Action verticalslidesaction,Action pivotaction,Action horizontalslidesaction, Action intakeAction,Action gripperaction,DriveTrain driveTrain){
        this.verticalslidesaction = verticalslidesaction;
        this.horizontalslidesaction = horizontalslidesaction;
        this.pivotaction = pivotaction;
        this.gripperaction = gripperaction;
        this.targetHeading = targetHeading;
        this.targetVecter = targetVecter;
        this.intakeAction = intakeAction;
        this.driveTrain = driveTrain;
    }


    @Override
    public void init() {
        driveTrain.strafeToLinearHeadingParallelCringe(targetVecter,targetHeading,verticalslidesaction,pivotaction,horizontalslidesaction,intakeAction,gripperaction);
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
