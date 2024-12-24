package org.firstinspires.ftc.teamcode.Opmode.Autos;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.NewRR.PinpointDrive;


@Autonomous
public class AutoNoScheduler extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor TurnTridentArm;
        DcMotor ExtendTridentArm;
        CRServoImplEx bothintake;
        GoBildaPinpointDriver odo;
        DcMotor righthang,lefthang;


        TurnTridentArm =hardwareMap.get(DcMotor.class, "TurnTridentArm");
        ExtendTridentArm = hardwareMap.get(DcMotor.class, "ExendTridentArm");
        bothintake = hardwareMap.get(CRServoImplEx.class, "bothintake");
        odo = hardwareMap.get(GoBildaPinpointDriver.class,"pinpointodo");
        righthang = hardwareMap.get(DcMotor.class,"righthang");
        lefthang = hardwareMap.get(DcMotor.class,"lefthang");
//        PinpointDrive mecanumDrive = new PinpointDrive(hardwareMap);
        righthang.setDirection(DcMotorSimple.Direction.REVERSE);        init();

//        mecanumDrive.setPoseEstimate(new Pose2d(-9, 61, Math.toRadians(90)));

//        TrajectorySequence trajectory = mecanumDrive.trajectorySequenceBuilder(new Pose2d(-9, 61, Math.toRadians(90)))
//                .lineToLinearHeading(new Pose2d(-9, 34, Math.toRadians(90)))
//                .lineToLinearHeading(new Pose2d(-9,47, Math.toRadians(0)))
//               // .lineToLinearHeading(new Pose2d(-45, -30, Math.toRadians(155)))
//                //.lineToLinearHeading(new Pose2d(-58,-63.5, Math.toRadians(270)))
//                //.lineToLinearHeading(new Pose2d(-55,-50, Math.toRadians(270)))
//                //.lineToLinearHeading(new Pose2d(-56.5,-49, Math.toRadians(90)))
//                //.lineToLinearHeading(new Pose2d(-56.5,-30, Math.toRadians(90)))
//                //.lineToLinearHeading(new Pose2d(-56.5,-31, Math.toRadians(270)))
//                //.lineToLinearHeading(new Pose2d(-58,-63.5, Math.toRadians(270)))
//                //.lineToLinearHeading(new Pose2d(-59,-61, Math.toRadians(270)))
//                .build();


        waitForStart();

        while (opModeIsActive()) {
//            mecanumDrive.followTrajectorySequence(trajectory);


        }
    }
}
