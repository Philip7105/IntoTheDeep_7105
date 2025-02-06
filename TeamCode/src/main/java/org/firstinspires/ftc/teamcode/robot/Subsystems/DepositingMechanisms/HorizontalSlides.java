package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

@Config
public class HorizontalSlides extends Subsystem {
    public ServoImplEx leftservoslide, rightservoslide;
    public static double fullin = .24,halfout = .56,autopos = 0.64,prepreclip = .4,hookclip = 0.34,preclip = .3,fullout = .88,hookclipencoderpos = 153, prepreclipencoderpos = 162, preclipencoderpos = 160,autoposencoderpos = 204,fullyOutEncoderPos = 240,halfOutEncoderPos = 202,fullyInEncoderPos = 144;
    //TODO max and min constrains must be fixed
    //get our analog input from the hardwareMap
    AnalogInput slideAnalog;
    //13 inches

    //    0.28
//    0.38
    @Override
    public void initAuto(HardwareMap hwMap) {
        leftservoslide = hwMap.get(ServoImplEx.class,"leftservoslide");
        rightservoslide = hwMap.get(ServoImplEx.class,"rightservoslide");
        leftservoslide.setDirection(Servo.Direction.REVERSE);
        slideAnalog = hwMap.get(AnalogInput.class, "leftslideencoder");
    }

    @Override
    public void periodic() {
        Dashboard.addData("horizontalslides",getSlidePos());
    }

    @Override
    public void shutdown() {
//        setHorizontalSlides(HorizontalSlideStates.SHUTOFF);
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
            case ZERO_POWER:
                leftservoslide.setPwmDisable();
                rightservoslide.setPwmDisable();
                break;
            case AUTOPOS:
                leftservoslide.setPosition(autopos);
                rightservoslide.setPosition(autopos);
                break;
            case SHUTOFF:
                leftservoslide.setPosition(0);
                rightservoslide.setPosition(0);
                break;
        }
    }

    public enum HorizontalSlideStates {
        FULLY_OUT,
        HALF_OUT,
        AUTOPOS,
        PREPRESELFCLIP,
        PRESELFCLIP,
        HOOKCLIP,
        FULLY_IN,
        ZERO_POWER,
        SHUTOFF
    }
}