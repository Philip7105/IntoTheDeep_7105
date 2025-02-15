package org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

@Config
public class JohnHanging extends Subsystem {

    DcMotorEx rightHang,leftHang;
    Servo rightPTOServo,leftPTOServo;
    public static double engagePTO = .5, hangpow, readyFirstHang = 295, firstHang = -2074.0, secondHang = 715.0, readySecondHang = 5000, kP = 0.007, kG = 0,manualPowerMultiplier = .5;
    public static boolean reverserightservo = false, reverseleftservo = false,leftFirstHangDone = false, leftReadyFirstHangDone = false, rightFirstHangDone = false, rightReadyFirstHangDone = false;
    @Override
    public void initAuto(HardwareMap hwMap) {
        hangpow = 0;
        rightHang = hwMap.get(DcMotorEx.class, "rightHang");
        leftHang = hwMap.get(DcMotorEx.class, "leftHang");
        rightPTOServo = hwMap.get(Servo.class,"rightptoservo");
        leftPTOServo = hwMap.get(Servo.class,"leftptoservo");
        rightHang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftHang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        if (reverseleftservo){
            rightPTOServo.setDirection(Servo.Direction.REVERSE);
        }
        if (reverserightservo){
            leftPTOServo.setDirection(Servo.Direction.REVERSE);
        }
        rightHang.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftHang.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightHang.setDirection(DcMotorSimple.Direction.REVERSE);
        rightReadyFirstHangDone = false;
        rightFirstHangDone = false;
        leftReadyFirstHangDone = false;
        leftFirstHangDone =false;
    }

    @Override
    public void periodic() {
//        Dashboard.addData("lefthangpos",getLeftHangPos());
//        Dashboard.addData("righthangpos",getRightHangPos());
//        Dashboard.addData("getHangPow",getPower());
    }

    @Override
    public void shutdown() {

    }
    public void setRightHang(RightHangStates rightHangStates){
        switch(rightHangStates){
            case READYFIRSTHANG:
                rightHang.setPower(calculateRightHangP(readyFirstHang));
                break;
            case READYSECONDHANG:
                rightHang.setPower(calculateRightHangP(readySecondHang));
                break;
            case FIRSTHANG:
                rightHang.setPower(calculateRightHangP(firstHang)+kG);
                break;
            case SECONDHANG:
                rightHang.setPower(calculateRightHangP(secondHang)+kG);
                break;
            case HOLDPOS:
                rightHang.setPower(kG);
                break;
            case ZERO_POWER:
                rightHang.setPower(0);
                break;
        }
    }
    public void setLeftHang(LeftHangStates leftHangStates){
        switch(leftHangStates){
            case READYFIRSTHANG:
                leftHang.setPower(calculateLeftHangP(readyFirstHang));
            break;
            case READYSECONDHANG:
                leftHang.setPower(calculateLeftHangP(readySecondHang));
                break;
            case FIRSTHANG:
                leftHang.setPower(calculateLeftHangP(firstHang) + kG);
                break;
            case SECONDHANG:
                leftHang.setPower(calculateLeftHangP(secondHang)+kG);
                break;
            case HOLDPOS:
                leftHang.setPower(kG);
                break;
            case ZERO_POWER:
                leftHang.setPower(0);
                break;
        }
    }
    public void setPTO(PTOSTATES ptostates){
        switch (ptostates){
            case ENGAGE:
                leftPTOServo.setPosition(engagePTO);
                rightPTOServo.setPosition(engagePTO);
                break;
        }
    }
    public double calculateLeftHangP(double ref){
        return getLeftHangError(ref) * kP;}
    public double calculateRightHangP(double ref){
        return getRightHangError(ref) * kP;}
    public void manualHang(Input input){
        if (input.getLeft_stick_y() > .9){
            hangpow = hangpow + .075;
            setPower(hangpow);
        } else if (input.getLeft_stick_y() < -.9) {
            hangpow = hangpow - .075;
            setPower(hangpow);
        } else {
            setPower(hangpow);
        }
    }
    public void setPower(double power){
        rightHang.setPower(power);
        leftHang.setPower(power);}
    public double getPower(){
        return leftHang.getPower();}
    public double getLeftHangError(double ref){
        return ref - getLeftHangPos();}
    public double getRightHangError(double ref){
        return ref - getRightHangPos();}
    public double getLeftHangPos(){
        return leftHang.getCurrentPosition();}
    public double getRightHangPos(){
        return rightHang.getCurrentPosition();}
    public enum LeftHangStates {
        READYFIRSTHANG,
        READYSECONDHANG,
        FIRSTHANG,
        SECONDHANG,
        HOLDPOS,
        ZERO_POWER
    }
    public enum RightHangStates {
        READYFIRSTHANG,
        READYSECONDHANG,
        FIRSTHANG,
        SECONDHANG,
        HOLDPOS,
        ZERO_POWER
    }
    public enum PTOSTATES{
        ENGAGE
    }
}
