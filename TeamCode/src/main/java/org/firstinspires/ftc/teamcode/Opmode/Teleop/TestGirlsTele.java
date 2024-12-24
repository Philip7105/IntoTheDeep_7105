//package org.firstinspires.ftc.teamcode.Opmode.Teleop;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServoImplEx;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
//
//import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;
//import org.firstinspires.ftc.teamcode.drive.PinPoint_MecanumDrive;
//
//@TeleOp
//public class TestGirlsTele extends LinearOpMode {
//    @Override
//    public void runOpMode() throws InterruptedException {
//        DcMotor TurnTridentArm;
//        DcMotor ExtendTridentArm;
//        CRServoImplEx bothintake;
//        GoBildaPinpointDriver odo;
//        DcMotor righthang,lefthang;
//
//        Boolean isReverse = false;
//
//        init();
//
//        TurnTridentArm =hardwareMap.get(DcMotor.class, "TurnTridentArm");
//        ExtendTridentArm = hardwareMap.get(DcMotor.class, "ExendTridentArm");
//        bothintake = hardwareMap.get(CRServoImplEx.class, "bothintake");
//        odo = hardwareMap.get(GoBildaPinpointDriver.class,"pinpointodo");
//        righthang = hardwareMap.get(DcMotor.class,"righthang");
//        lefthang = hardwareMap.get(DcMotor.class,"lefthang");
//        PinPoint_MecanumDrive mecanumDrive = new PinPoint_MecanumDrive(hardwareMap);
//        righthang.setDirection(DcMotorSimple.Direction.REVERSE);
//
//
//        waitForStart();
//        while(opModeIsActive()){
//
//            if (gamepad1.a){
//                isReverse = true;
//            }
//            if (gamepad1.b){
//                isReverse = false;
//            }
//
//            if (!isReverse){
//                ExtendTridentArm.setPower(0.3*gamepad1.left_trigger);
//                TurnTridentArm.setPower(0.5*gamepad1.right_trigger);
//            }
//            if (isReverse){
//                ExtendTridentArm.setDirection(DcMotorSimple.Direction.REVERSE);
//                TurnTridentArm.setDirection(DcMotorSimple.Direction.REVERSE);
//                ExtendTridentArm.setPower(0.3*gamepad1.left_trigger);
//                TurnTridentArm.setPower(0.3*gamepad1.right_trigger);;
//            }
//
//            if (gamepad1.right_bumper){
//                bothintake.setPower(0.75);
//            }
//
//
//            else if (gamepad1.left_bumper){
//                bothintake.setPower(-0.75);
//            }
//
//            else {
//                bothintake.setPower(0);
//            }
//            mecanumDrive.setWeightedDrivePower(
//                    new Pose2d(-gamepad1.left_stick_y,
//                            -gamepad1.right_stick_x,
//                            -gamepad1.left_stick_x));
//
//            righthang.setPower(gamepad1.right_stick_y);
//            lefthang.setPower(gamepad1.right_stick_y);
//        }
//
//
//
//
//
//
//
//
//
//
//
//    }
//}