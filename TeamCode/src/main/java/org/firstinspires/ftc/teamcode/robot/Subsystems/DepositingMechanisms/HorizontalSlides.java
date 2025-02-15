package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain.driveSpeed;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class HorizontalSlides extends Subsystem {
    public ServoImplEx leftservoslide, rightservoslide;
    public static double fullin = .24,halfout = .56,autopos = 0.46, prepselfclip = .7,prepreclip = .4,hookclip = 0.387,hookclipencoderpos = 167,preclip = 0.3,fullout = .88,
            autoposencoderpos = 184, preclipencoderpos = 148,prepselfclipencoderpos = 212,fullyOutEncoderPos = 240,halfOutEncoderPos = 202,fullyInEncoderPos = 144;
    //TODO max and min constrains must be fixed
    AnalogInput slideAnalog;
    @Override
    public void initAuto(HardwareMap hwMap) {
        leftservoslide = hwMap.get(ServoImplEx.class,"leftservoslide");
        rightservoslide = hwMap.get(ServoImplEx.class,"rightservoslide");
        leftservoslide.setDirection(Servo.Direction.REVERSE);
        slideAnalog = hwMap.get(AnalogInput.class, "leftslideencoder");
    }
    @Override
    public void periodic() {
//        Dashboard.addData("horizontalslides",getSlidePos());
    }
    @Override
    public void shutdown() {
    }
    public double getSlidePos(){
        return getVoltage() / 3.3 * 360;
    }
    public double getVoltage(){
        return slideAnalog.getVoltage();
    }
    public double getSlideError(double ref){
        return ref - getSlidePos();
    }
    public double getSlideErrorInches(double ref){
        return ref - ticksToInches();
    }
    public double ticksToInches(){
        return (getSlidePos() - 143) / 5.846;
    }

    public double getSetPos(){
        return leftservoslide.getPosition();
    }
    public void setPosition(double ref){
        leftservoslide.setPosition(ref);
        rightservoslide.setPosition(ref);
    }
    public void setHorizontalSlides(HorizontalSlideStates horizontalslidestates){
        switch (horizontalslidestates){
            case FULLY_IN:                         //161
                leftservoslide.setPosition(fullin); //170
                rightservoslide.setPosition(fullin);
                break;
            case HALF_OUT:
                leftservoslide.setPosition(halfout);// 190
                rightservoslide.setPosition(halfout); //199
                break;
            case FULLY_OUT:                         // 236
                leftservoslide.setPosition(fullout); // 245
                rightservoslide.setPosition(fullout);
                break;
            case PREPSELFCLIP:
                leftservoslide.setPosition(prepselfclip);
                rightservoslide.setPosition(prepselfclip);
                driveSpeed = DriveTrain.DriveSpeed.CLIPSPEED;
                break;
            case PREPRESELFCLIP:
                leftservoslide.setPosition(prepreclip);
                rightservoslide.setPosition(prepreclip);
                break;
            case PRESELFCLIP:
                leftservoslide.setPosition(preclip);
                rightservoslide.setPosition(preclip);
                break;
            case HOOKCLIP:
                leftservoslide.setPosition(hookclip);
                rightservoslide.setPosition(hookclip);
                break;
            case AUTOPOS:
                leftservoslide.setPosition(autopos);
                rightservoslide.setPosition(autopos);
                break;
        }
    }
    public enum HorizontalSlideStates {
        FULLY_OUT,
        HALF_OUT,
        AUTOPOS,
        PREPSELFCLIP,
        PREPRESELFCLIP,
        PRESELFCLIP,
        HOOKCLIP,
        FULLY_IN
    }
}