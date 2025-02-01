package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems;

import com.ThermalEquilibrium.homeostasis.Utils.MathUtils;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.BetterSubsystems;

public class Limelight extends SubsystemBase {

    Limelight3A limelight;

    Drivetrain driveTrain;

    Pose3D botpose;

    LLResult result;

    public static double x,y, heading;

    HardwareMap hwMap;

    public Limelight(Drivetrain driveTrain, HardwareMap hwMap){
        this.driveTrain = driveTrain;
        this.hwMap = hwMap;
    }

    @Override
    public void register() {
        super.register();
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
//        limelight.updateRobotOrientation(Math.toRadians(driveTrain.getHeadingFixed()));
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
}
