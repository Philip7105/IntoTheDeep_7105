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

    public static double fullin = .24, fullout = .88;
    //TODO max and min constrains must be fixed


    //get our analog input from the hardwareMap
    AnalogInput slideAnalog;


    //13 inches

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
        setHorizontalSlides(HorizontalSlideStates.SHUTOFF);
    }

    public double getSlidePos(){
        return slideAnalog.getVoltage() / 3.3 * 360;
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
            case Fully_In:                         //161
                leftservoslide.setPosition(fullin); //170
                rightservoslide.setPosition(fullin);
                break;
            case Half_Out:
                leftservoslide.setPosition(.56);// 190
                rightservoslide.setPosition(.56); //199
                break;
            case Fully_Out:                         // 236
                leftservoslide.setPosition(fullout); // 245
                rightservoslide.setPosition(fullout);
                break;
            case Zero_Power:
                leftservoslide.setPwmDisable();
                rightservoslide.setPwmDisable();
                break;
            case SHUTOFF:
                leftservoslide.setPosition(0);
                rightservoslide.setPosition(0);
                break;
        }
    }

    public enum HorizontalSlideStates {
        Fully_Out,
        Half_Out,
        Fully_In,
        Zero_Power,
        SHUTOFF
    }
}
