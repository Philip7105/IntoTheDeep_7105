package org.firstinspires.ftc.teamcode.Opmode.Teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Control.KalmanEstimator;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

@TeleOp
@Config

public class KalmanFilterTest2 extends LinearOpMode {
    protected Robot robot;
    KalmanEstimator kalmanEstimator_X,kalmanEstimator_Y;

    public static double xmultiplier = 1, ymultiplier = 1,modelcovariance_x = .2,sensorcovariance_x = .8,modelcovariance_y = .2,sensorcovariance_y = .8;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap, Robot.OpMode.Teleop, gamepad1, gamepad2);
//        kalmanFilter = new MOREKALMANFILTER(2,modelcovariance,sensorcovariance,getRawX());
        kalmanEstimator_X = new KalmanEstimator(getRawX(),sensorcovariance_x,modelcovariance_x,4);
        kalmanEstimator_Y = new KalmanEstimator(getRawY(),sensorcovariance_y,modelcovariance_y,4);
        waitForStart();
        while (opModeIsActive()) {
            kalmanEstimator_Y.update();
            kalmanEstimator_X.update();
            Dashboard.addData("kalmanx",kalmanEstimator_X.update());
            Dashboard.addData("kalmany",kalmanEstimator_Y.update());
        }
    }

    public double getX() {
        return (((robot.limelight.result.getBotpose_MT2().getPosition().x) * xmultiplier) + robot.driveTrain.getXPos()) / 2;
    }
    public double getY() {
        return (((robot.limelight.result.getBotpose_MT2().getPosition().y) * ymultiplier) + robot.driveTrain.getXPos()) / 2;
    }
    public double getRawX() {
        return ((robot.limelight.result.getBotpose_MT2().getPosition().x) * xmultiplier);
    }
    public double getRawY() {
        return ((robot.limelight.result.getBotpose_MT2().getPosition().y) * ymultiplier);
    }
}
