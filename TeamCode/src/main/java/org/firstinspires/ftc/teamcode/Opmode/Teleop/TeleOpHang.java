//package org.firstinspires.ftc.teamcode.Opmode.Teleop;
//
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;
//import org.firstinspires.ftc.teamcode.drive.PinPoint_MecanumDrive;
//
//@TeleOp
//public class TeleOpHang extends LinearOpMode {
//
//    GoBildaPinpointDriver odo;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//
//
//        DcMotor righthang,lefthang;
//
//        odo = hardwareMap.get(GoBildaPinpointDriver.class,"pinpointodo");
//        righthang = hardwareMap.get(DcMotor.class,"righthang");
//        lefthang = hardwareMap.get(DcMotor.class,"lefthang");
//
//        PinPoint_MecanumDrive mecanumDrive = new PinPoint_MecanumDrive(hardwareMap);
//        odo = hardwareMap.get(GoBildaPinpointDriver.class,"pinpointodo");
//        righthang.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        waitForStart();
//
//        while (opModeIsActive()){
//
//            mecanumDrive.setWeightedDrivePower(
//                    new Pose2d(-gamepad1.left_stick_y,
//                    -gamepad1.right_stick_x,
//                    -gamepad1.left_stick_x));
//
//            righthang.setPower(gamepad1.right_stick_y);
//            lefthang.setPower(gamepad1.right_stick_y);
//
//        }
//
//
//    }
//}
