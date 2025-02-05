//package org.firstinspires.ftc.teamcode.Opmode.Teleop;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.Pose2d;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.qualcomm.hardware.limelightvision.LLResult;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.Control.KalmanFilter;
//import org.firstinspires.ftc.teamcode.robot.Robot;
//import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
//
//@TeleOp
//@Config
//public class KalmanFilterTest extends LinearOpMode {
//    protected Robot robot;
//    public KalmanFilter kalmanFilter;
//    public static boolean useKalmanFilter = false;
//    @Override
//    public void runOpMode() throws InterruptedException {
//        robot =new Robot(hardwareMap, Robot.OpMode.Teleop, gamepad1, gamepad2);
//        kalmanFilter = new KalmanFilter(robot.driveTrain.getPoseEstimate(),robot.driveTrain,robot.limelight.limelight);
//
//        waitForStart();
//        while (opModeIsActive()) {
////            LLResult llResult = robot.limelight.limelight.getLatestResult();
//            if (useKalmanFilter) {
//                kalmanFilter.kalmanSmart();
//            } else if (!useKalmanFilter && robot.limelight.limelight.getLatestResult() != null){
//                robot.limelight.limelight.updateRobotOrientation(robot.driveTrain.getHeadingFixed());
//
////        if (result != null && result.isValid()) {
//            botpose = result.getBotpose_MT2();
//            Dashboard.addData("apriltagheading",robot.driveTrain());
//            Dashboard.addData("getBotX",x);
//            Dashboard.addData("getBotY",y);
//            driveTrain.setPoseEstimateBetter(new Vector2d(x,y), getBotHeading());
//
//                robot.driveTrain.setPoseEstimateBetter(new Vector2d(limelight.getLatestResult().getBotpose().getPosition().x*39.3701007874, robot.limelight.getLatestResult().getBotpose().getPosition().y*39.3701007874), driveTrain.getHeadingFixed());
//            }
//        }
//    }
//}
