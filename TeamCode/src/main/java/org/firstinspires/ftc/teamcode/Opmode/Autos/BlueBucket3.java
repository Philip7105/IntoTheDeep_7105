package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;

@Config
@Autonomous
public class BlueBucket3 extends BaseAuto {
    @Override
    public void runAuto() {
// This is the starting position and heading for Blue Bucket 3 Auto
        robot.driveTrain.setPoseEstimate(new Vector2d(32, 60), Rotation2d.exp(Math.toRadians(180)));
        //This is the next postion for the robot it is to pick up the yellow sample
        runpath = new MultipleCommand(new StrafetoLinearHeading(new Vector2d(48.2,47),Rotation2d.exp(Math.toRadians(90)), lowchamber, robot, groups))
                //This is the next potistion of the robot it is to deposit at the high basket with the yellow sample
        .addNext(new StrafetoLinearHeading(new Vector2d(57, 55),Rotation2d.exp(Math.toRadians(225)),highbasket,robot,groups))
                //this is the next position the robot is to drive to the next yellow sample and pick it up
        .addNext(new StrafetoLinearHeading(new Vector2d(58.2, 47),Rotation2d.exp(Math.toRadians(270)),highbasket,robot,groups))
                //This is the next potistion of the robot it is to deposit at the high basket with the yellow sample
        .addNext(new StrafetoLinearHeading(new Vector2d(57,55),Rotation2d.exp(Math.toRadians(225)),highbasket,robot,groups))
                //this is the next potistion for the robot it is to pick up the yellow sample in a 45 ish degree angle
        .addNext(new StrafetoLinearHeading(new Vector2d(56.2, 47),Rotation2d.exp(Math.toRadians(305)),highbasket,robot,groups))
                //This is the next potistion of the robot it is to deposit at the high basket with the yellow sample
        .addNext(new StrafetoLinearHeading(new Vector2d(57,47),Rotation2d.exp(Math.toRadians(225)),highbasket,robot,groups));





    }












}
