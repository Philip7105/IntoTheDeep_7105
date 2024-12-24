package org.firstinspires.ftc.teamcode.Opmode.Autos;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
@Config
@Autonomous
public class BlueClipSide4 extends BaseAuto {
//    @Override
//    public Command runAuto(CommandScheduler scheduler) {
//        Command runpath;
//        robot.driveTrain.mecanumDrive.setPoseEstimate(new Pose2d(-15.8, 59.7, Math.toRadians(90)));
//        TrajectorySequence trajectory = robot.driveTrain.mecanumDrive.trajectorySequenceBuilder(new Pose2d(-15.8, 59.7, Math.toRadians(90)))
//                .lineToLinearHeading(new Pose2d(-10, 46, Math.toRadians(90)))
//                .build();
//        runpath = new MultipleCommand(RoadRunnerPathSequence(trajectory)).addNext(groups.moveClipMechanismsOut(0, ClipMech.ArmStates.Out_The_Way,
//                HorizontalSlides.HorizontalSlideStates.Half_Out,190, JohnsIntake.ArmStates.preauto_clip));
//        return runpath;
//    }
}