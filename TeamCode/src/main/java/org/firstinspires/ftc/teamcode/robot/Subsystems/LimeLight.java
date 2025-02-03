package org.firstinspires.ftc.teamcode.robot.Subsystems;

import com.ThermalEquilibrium.homeostasis.Utils.MathUtils;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

public class LimeLight extends Subsystem {

    public Limelight3A limelight;

    DriveTrain driveTrain;

    Pose3D botpose;

    LLResult result;

    public static double x,y, heading;

    public LimeLight(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
    }

    @Override
    public void initAuto(HardwareMap hwMap) {
        x = 0;
        y = 0;

        limelight = hwMap.get(Limelight3A.class,"limelight");

        limelight.pipelineSwitch(0);

        limelight.setPollRateHz(100);
        /*
         * Starts polling for data.
         */
        limelight.start();
    }

    @Override
    public void periodic() {
//        limelight.updateRobotOrientation(driveTrain.getHeadingFixed());
//        result = limelight.getLatestResult();
//        if (result != null && result.isValid()) {
//            botpose = result.getBotpose_MT2();
//            converter();
//            Dashboard.addData("apriltagheading",getBotHeading());
//            Dashboard.addData("getBotX",x);
//            Dashboard.addData("getBotY",y);
//            driveTrain.setPoseEstimateBetter(new Vector2d(x,y), getBotHeading());
//        }
    }

    public void converter(){
        x = (getBotX() * 34.80859179266); //35.9375
        y = (getBotY() * 39.24964035808);
    }

    public double angleWrap(double pos, double offset){
        return MathUtils.AngleWrap(pos+offset);
    }

    public double getBotHeading(){
        return result.getBotpose_MT2().getOrientation().getYaw();
    }
    public double getBotX(){
        return result.getBotpose_MT2().getPosition().x;
    }

    public double getBotY(){
        return result.getBotpose_MT2().getPosition().y;
    }

//    public void setOdo(double x,double y, double heading){
//        robotx = x + odo.getXPos();
//        roboty = y + odo.getYPos();
//        robotH = heading + odo.getHeading();
//    }

    @Override
    public void shutdown() {
        limelight.shutdown();
    }
}
