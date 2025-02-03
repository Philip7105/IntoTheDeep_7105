package org.firstinspires.ftc.teamcode.Control;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.NewRR.Drawing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class KalmanFilter {
    public static double kalmanGainX = 1;
    public static double kalmanGainY = 1;
    public static double estimateXVariance = 2;
    public static double estimateYVariance = 2;
    Pose2d estimateState;
    Pose2d calculatedState;
    DriveTrain driveTrain;
    Limelight3A limelight;
    ElapsedTime timer;
    Vector2d translatedVel;
    public static double ODO_VARIANCE = 10;
    public static double X_MULT_VARIANCE = 1; //for apriltags
    public static double Y_MULT_VARIANCE = 1;
    public static double DEG_MULT_VARIANCE = 1;
    public static double TAG_VARIANCE = 2;
    public KalmanFilter(Pose2d startPose, DriveTrain driveTrain, Limelight3A limelight){
        estimateState = startPose;
        calculatedState = estimateState;
        timer = new ElapsedTime();
        this.driveTrain = driveTrain;
        this.limelight = limelight;
    }
    public void predictKalman(){
        //predict
        translatedVel = new Vector2d(driveTrain.getVelX()*0.03937, driveTrain.getVelY()*0.03937);
        double time = timer.time();
        timer.reset();
        estimateXVariance += time*time*Math.abs(translatedVel.x); // p n,n -> p n+1, n
        estimateYVariance += time*time*Math.abs(translatedVel.y);


        estimateState = new Pose2d(estimateState.position.x + translatedVel.x * time
                ,estimateState.position.y + translatedVel.y * time
                , driveTrain.getHeadingFixed());
    }
    public void updateKalman(double variance, Pose2d measurement) {
        //update
        kalmanGainX = (estimateXVariance)/(estimateXVariance + variance);
        kalmanGainY = (estimateYVariance)/(estimateYVariance + variance);
        estimateXVariance = (1-kalmanGainX)*estimateXVariance; // p n-1,n -> p n,n
        estimateYVariance = (1-kalmanGainY)*estimateYVariance;
        estimateState = new Pose2d(estimateState.position.x + kalmanGainX*(measurement.position.x - estimateState.position.x),
                estimateState.position.y + kalmanGainY*(measurement.position.y - estimateState.position.y),
                driveTrain.getHeadingFixed());
        calculatedState = new Pose2d(estimateState.position.x, estimateState.position.y, estimateState.heading.toDouble());
    }
    public void aprilTagKalman(){
        predictKalman();
        Pose2d atagPose = new Pose2d(limelight.getLatestResult().getBotpose().getPosition().x*39.3701007874, limelight.getLatestResult().getBotpose().getPosition().y*39.3701007874, driveTrain.getHeadingFixed());
//        telemetry.addData("atagPose", atagPose);
        double xVar = Math.abs(translatedVel.x * X_MULT_VARIANCE);
        double yVar = Math.abs(translatedVel.y * Y_MULT_VARIANCE);
        double degVar = Math.abs (limelight.getLatestResult().getTa() * DEG_MULT_VARIANCE);
        double totalTagVar = xVar + yVar + degVar + Math.abs (Math.abs (Math.toDegrees (limelight.getLatestResult().getTa())) - 180) + TAG_VARIANCE;
        //double totalTagVar = Math.abs (Math.abs (Math.toDegrees (limelight.getLatestResult().getTa()))-180);
        if (limelight.getLatestResult().isValid()) {
            updateKalman(totalTagVar, atagPose);

        }
    }
//    public void odoKalman(){
//        predictKalman();
//        calculatedState = new Pose2d(estimateState.position.x, estimateState.position.y, estimateState.heading.toDouble());

        //filterKalman(ODO_VARIANCE, new Pose2d(pinpoint.getPosX()*0.03937, pinpoint.getPosY()*0.03937, new Rotation2d(0)),telemetry);
//    }
    public Pose2d getCalculatedState(){
        return this.calculatedState;
    }
    public Vector2d rotateVector(Vector2d vector,double angle){
        return new Vector2d(Math.cos(angle)*vector.x - Math.sin(angle)*vector.y, Math.sin(angle)*vector.x + Math.cos(angle)*vector.y);
    }
    public void kalmanSmart() {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid() && result.getBotpose_MT2() != null) {
            aprilTagKalman();
        }
//        else {
//            odoKalman();
//        }
    }

    public void kalmanSmart(Canvas c) {
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid() && result.getBotpose_MT2() != null) {
            aprilTagKalman();

            Pose3D pose3d = result.getBotpose_MT2();
            Pose2d llPose = new Pose2d((pose3d.getPosition().x*39.3701007874), (pose3d.getPosition().y*39.3701007874), pose3d.getOrientation().getYaw(AngleUnit.RADIANS));
            c.setStroke("#34ad38");
            Drawing.drawRobot(c, llPose);
        }
//        else {
//            odoKalman();
//        }
    }
}