package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands.StrafetoLinearHeading;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Config
@Autonomous
public class BlueClipSide4 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimate(new Vector2d(-13,63), Rotation2d.exp(Math.toRadians(90)));
        runpath = new MultipleCommand(new StrafetoLinearHeading(new Vector2d(-10,33),Rotation2d.exp(Math.toRadians(90)),lowchamber,robot,groups))
                // clip first specimen
                .addNext(new StrafetoLinearHeading(new Vector2d(-33,38),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-39,10),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-45,10),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-45,55),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                // push in the first blue sample
                .addNext(new StrafetoLinearHeading(new Vector2d(-45,10),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-54,10),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-54,55),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                // push in the second blue sample
                .addNext(new StrafetoLinearHeading(new Vector2d(-54,10),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-61,10),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-61,55),Rotation2d.exp(Math.toRadians(180)),lowchamber, robot,groups))
                // push in the final blue sample
                .addNext(new StrafetoLinearHeading(new Vector2d(-52,30),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                // pick up a blue specimen
                .addNext(new StrafetoLinearHeading(new Vector2d(-8,33),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                // place a blue specimen
                .addNext(new StrafetoLinearHeading(new Vector2d(-52,40),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                // pick up a blue specimen
                .addNext(new StrafetoLinearHeading(new Vector2d(-6,33),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                // place a blue specimen
                .addNext(new StrafetoLinearHeading(new Vector2d(-52,40),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                // pick up a blue specimen
                .addNext(new StrafetoLinearHeading(new Vector2d(-4,33),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                // place a blue specimen
                .addNext(new StrafetoLinearHeading(new Vector2d(-54,55),Rotation2d.exp(Math.toRadians(90)),lowchamber, robot,groups))
                // park
                ;
    }
}