package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;


import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

public class StrafetoLinearHeadingMultiActions extends Command {
    DriveTrain driveTrain;
//    Vector2d targetVec;
//    Rotation2d targetHeading;
    Action[] actions;
    VerticalSlides verticalSlides;
    double target;
    public StrafetoLinearHeadingMultiActions(VerticalSlides verticalSlides, double target,Action[] actions, DriveTrain driveTrain){
        this.driveTrain = driveTrain;
//        this.targetVec = targetVec;
//        this.targetHeading = targetHeading;
        this.actions = actions;
        this.verticalSlides = verticalSlides;
        this.target = target;
//        this.hwMap = hwMap;
    }


    @Override
    public void init() {
//        driveTrain.Listy(hwMap,actions);
    }

    @Override
    public void periodic() {
        //TODO check to see if this was the issue.
//        driveTrain.li(actions);
//        driveTrain.Listy(hwMap,actions);
    }

    @Override
    public boolean completed() {
        return false;
//                !driveTrain.mecanumDrive.leftBack.isBusy() && !driveTrain.mecanumDrive.rightBack.isBusy() &&
//                !driveTrain.mecanumDrive.leftFront.isBusy() && !driveTrain.mecanumDrive.rightFront.isBusy();
    }

    @Override
    public void shutdown() {
        driveTrain.shutdown();
    }
}
