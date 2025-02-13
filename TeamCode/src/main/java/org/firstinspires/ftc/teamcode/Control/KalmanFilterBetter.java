package org.firstinspires.ftc.teamcode.Control;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class KalmanFilterBetter {
    DriveTrain driveTrain;
    LLResult llResult;
    public static double initalstate_forX = 0; // your initial state
    public static double modelcovariance_forX = 0.1; // your model covariance
    public static double sensorcovariance_forX = 0.4; // your sensor covariance
    public static double initalcovariance_forX = 1; // your initial covariance guess
    public static double KalmanGain_forX = 1; // your initial Kalman gain guess

    public static double x_previous_forX = initalstate_forX;
    public static double p_previous_forX = initalcovariance_forX;
    public static double u_forX = 1;
    public static double z_forX = 2;

    public static double initalstate_forY = 0; // your initial state
    public static double modelcovariance_forY = 0.1; // your model covariance
    public static double sensorcovariance_forY = 0.4; // your sensor covariance
    public static double initalcovariance_forY = 1; // your initial covariance guess
    public static double KalmanGain_forY = 1; // your initial Kalman gain guess

    public static double x_previous_forY = initalstate_forY;
    public static double p_previous_forY = initalcovariance_forY;
    public static double u_forY = 1;
    public static double z_forY = 2;

    public static double initalstate_forH = 0; // your initial state
    public static double modelcovariance_forH= 0.1; // your model covariance
    public static double sensorcovariance_forH= 0.4; // your sensor covariance
    public static double initalcovariance_forH = 1; // your initial covariance guess
    public static double KalmanGain_forH = 1; // your initial Kalman gain guess

    public static double x_previous_forH = initalstate_forH;
    public static double p_previous_forH= initalcovariance_forH;
    public static double u_forH = 1;
    public static double z_forH= 2;
    public KalmanFilterBetter(LLResult llResult,DriveTrain driveTrain){
        this.driveTrain = driveTrain;
        this.llResult = llResult;
    }
    public void predictKalmanforX(){
//        u_forX = driveTrain.getXPos(); // Ex: change in position from odometry.
        initalstate_forX = x_previous_forX + u_forX;

        initalcovariance_forX = p_previous_forX + modelcovariance_forX;

        KalmanGain_forX = initalcovariance_forX /(initalcovariance_forX + sensorcovariance_forX);

        z_forX = llResult.getBotpose_MT2().getPosition().x; // Pose Estimate from April Tag / Distance Sensor


        initalstate_forX = initalstate_forX + KalmanGain_forX * (z_forX - initalstate_forX);

        initalcovariance_forX = (1 - KalmanGain_forX) * initalcovariance_forX;

        x_previous_forX = initalstate_forX;
        p_previous_forX = initalcovariance_forX;
    }
    public void predictKalmanforY(){
        u_forY = driveTrain.getYPos(); // Ex: change in position from odometry.
        initalstate_forY = x_previous_forY + u_forY;

        initalcovariance_forY = p_previous_forY + modelcovariance_forY;

        KalmanGain_forY = initalcovariance_forY /(initalcovariance_forY + sensorcovariance_forY);

        z_forY = llResult.getBotpose_MT2().getPosition().y; // Pose Estimate from April Tag / Distance Sensor


        initalstate_forY = initalstate_forY + KalmanGain_forY * (z_forY - initalstate_forY);

        initalcovariance_forY = (1 - KalmanGain_forY) * initalcovariance_forY;

        x_previous_forY = initalstate_forY;
        p_previous_forY = initalcovariance_forY;
    }
    public void predictKalmanforHeading(){
        u_forH = driveTrain.getHeadingFixed(); // Ex: change in position from odometry.
        initalstate_forH = x_previous_forH + u_forH;

        initalcovariance_forH = p_previous_forH + modelcovariance_forH;

        KalmanGain_forH = initalcovariance_forH /(initalcovariance_forH + sensorcovariance_forH);

        z_forH = llResult.getBotpose_MT2().getOrientation().getYaw(); // Pose Estimate from April Tag / Distance Sensor


        initalstate_forH = initalstate_forH + KalmanGain_forH * (z_forH - initalstate_forH);

        initalcovariance_forH = (1 - KalmanGain_forH) * initalcovariance_forH;

        x_previous_forH = initalstate_forH;
        p_previous_forH = initalcovariance_forH;
    }
}
