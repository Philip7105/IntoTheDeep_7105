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
        robot.driveTrain.setPoseEstimate(new Vector2d(13,-63), Rotation2d.exp(Math.toRadians(90)));
        runpath = new MultipleCommand(new StrafetoLinearHeading(new Vector2d(10,-33),Rotation2d.exp(Math.toRadians(90)),lowchamber,robot,groups)
        ,groups.moveGripper(JohnsIntake.GripperStates.unclamp))
                .addNext(new StrafetoLinearHeading(new Vector2d(18,-41),Rotation2d.exp(Math.toRadians(90)),lowbasket,robot,groups))
                .addNext(new StrafetoLinearHeading(new Vector2d(18, -35), Rotation2d.exp(Math.toRadians(180)), lowchamber, robot, groups));
    }
}