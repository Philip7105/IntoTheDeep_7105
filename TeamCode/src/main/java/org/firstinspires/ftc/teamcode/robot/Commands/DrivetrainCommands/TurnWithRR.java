package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;


import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;

import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Commands.HoldSlidePosWithRR;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class TurnWithRR extends Command {
    Robot robot;
    Vector2d startVec;
    Rotation2d startHeading,targetheading;
    public TurnWithRR(Rotation2d targetheading){
        this.targetheading = targetheading;
    }

    @Override
    public void init() {
        startVec = new Vector2d(robot.driveTrain.getXPos(),robot.driveTrain.getYPos());
        startHeading = robot.driveTrain.getHeading();
        robot.driveTrain.turn(startVec,startHeading,targetheading);
    }

    @Override
    public void periodic() {
        //TODO check to see if this was the issue.
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
