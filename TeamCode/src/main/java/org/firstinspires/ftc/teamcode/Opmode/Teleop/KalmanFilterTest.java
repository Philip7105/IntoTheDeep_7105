package org.firstinspires.ftc.teamcode.Opmode.Teleop;

import static org.firstinspires.ftc.teamcode.Control.KalmanFilterBetter.initalstate_forH;
import static org.firstinspires.ftc.teamcode.Control.KalmanFilterBetter.initalstate_forX;
import static org.firstinspires.ftc.teamcode.Control.KalmanFilterBetter.initalstate_forY;

import com.ThermalEquilibrium.homeostasis.Filters.Estimators.KalmanEstimator;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Control.KalmanFilter;
import org.firstinspires.ftc.teamcode.Control.KalmanFilterBetter;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

@TeleOp
@Config
public class KalmanFilterTest extends LinearOpMode {
    protected Robot robot;
    public KalmanFilterBetter kalmanFilter;
//    KalmanEstimator
    @Override
    public void runOpMode() throws InterruptedException {
        robot =new Robot(hardwareMap, Robot.OpMode.Teleop, gamepad1, gamepad2);
        kalmanFilter = new KalmanFilterBetter(robot.limelight.result,robot.driveTrain);

        waitForStart();
        while (opModeIsActive()) {
            robot.limelight.limelight.updateRobotOrientation(robot.driveTrain.getHeadingFixed());
            if (robot.limelight.result != null && robot.limelight.result.isValid()) {
            kalmanFilter.predictKalmanforX();
            kalmanFilter.predictKalmanforY();
            kalmanFilter.predictKalmanforHeading();
            robot.driveTrain.setPoseEstimateBetter(new Vector2d(initalstate_forX,initalstate_forY),initalstate_forH);
            }
        }
    }
}
