package org.firstinspires.ftc.teamcode.robot.Subsystems.Intake;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class JohnsIntake extends Subsystem {

    CRServo rightintake,leftintake;
    Servo gripper,rightarm,leftarm;

    public static double basketpos = .64, chamberpos = .7, down = 0.15,parallel = .26;//.78  .155

//    NormalizedColorSensor colorsensor;

    AnalogInput armanalog;

    @Override
    public void initAuto(HardwareMap hwMap) {
        rightintake = hwMap.get(CRServo.class,"rightintake");
        leftintake = hwMap.get(CRServo.class,"leftintake");
        gripper = hwMap.get(Servo.class,"gripper");
        rightarm = hwMap.get(Servo.class,"rightarm");
        leftarm = hwMap.get(Servo.class,"leftarm");
        armanalog = hwMap.get(AnalogInput.class,"armanalog");

        leftintake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightarm.setDirection(Servo.Direction.REVERSE);
    }

    @Override
    public void periodic() {
    }

    @Override
    public void shutdown() {

    }

    public double getArmPos(){
        return armanalog.getVoltage() / 3.3 * 360;
    }

//    public double getBlue(){
//        return colorsensor.getNormalizedColors().blue;
//    }
//
//    public double getRed(){
//        return colorsensor.getNormalizedColors().red;
//    }
//
//    public double getGreen(){
//        return colorsensor.getNormalizedColors().green;
//    }

//    public void getColor(SampleStates samplestates){
//        switch (samplestates){
//            case RED:
//
//                break;
//            case BLUE:
//
//                break;
//            case YELLOW:
//
//                break;
//            case READ:
////                if (colorsensor instanceof SwitchableLight) {
//                    ((SwitchableLight)colorsensor).enableLight(true);
////                }
//                if (getRed() > 200){
//                    samplestates = SampleStates.RED;
//                } else if (getBlue() > 200){
//                    samplestates = SampleStates.BLUE;
//                } else if (getBlue() > 150 && getRed() > 150) {
//                    samplestates = SampleStates.YELLOW;
//                }
//                break;
//            case SHUT_OFF:
//                ((SwitchableLight)colorsensor).enableLight(false);
//                break;
//        }
//    }

    public void intakeTele(Input input, HorizontalSlides slides){
        if (input.isLeft_trigger_press()){
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setGripper(GripperStates.unclamp);
            setIntake(JohnsIntake.IntakeStates.outtake);
        } else if (input.isRight_trigger_press()) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
            setIntake(JohnsIntake.IntakeStates.intake);
            setArmStates(JohnsIntake.PivotStates.forward);
        } else if (slides.leftservoslide.getPosition() != fullin
                && !input.isRight_trigger_press() && !input.isLeft_trigger_press()) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setArmStates(JohnsIntake.PivotStates.parallel);
            setIntake(JohnsIntake.IntakeStates.stop);
        } else {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setIntake(JohnsIntake.IntakeStates.stop);
        }
    }

    public void setIntake(IntakeStates intakeStates){
        switch (intakeStates){
            case intake:
                rightintake.setPower(1);
                leftintake.setPower(1);
                break;
            case outtake:
                rightintake.setPower(-.3);
                leftintake.setPower(-.3);
                break;
            case stop:
                rightintake.setPower(0);
                leftintake.setPower(0);
                break;
        }
    }

    public void setGripper(GripperStates gripperStates) {
        switch (gripperStates){
            case unclamp:
                gripper.setPosition(.23);
                break;
            case clamp:
                gripper.setPosition(.77);
                break;
        }
    }

    public void setArmStates(PivotStates pivotStates){
        switch (pivotStates){
            case forward:
                rightarm.setPosition(down); // 121
                leftarm.setPosition(down);
                break;
            case posauto_clip:
                rightarm.setPosition(0.2);
                leftarm.setPosition(0.25);
                break;
            case preauto_clip:
                rightarm.setPosition(0.4); // 121
                leftarm.setPosition(0.4);
                break;
            case parallel:
                rightarm.setPosition(parallel);
                leftarm.setPosition(parallel);
                break;
            case hookclip:
                rightarm.setPosition(.28);
                leftarm.setPosition(.28);
                break;
            case snapclip:
                rightarm.setPosition(.23);
                leftarm.setPosition(.23); //143
                break;
            case basketpos:
                rightarm.setPosition(basketpos);//227
                leftarm.setPosition(basketpos);
                break;
            case chamberpos:
                rightarm.setPosition(chamberpos);//227
                leftarm.setPosition(chamberpos);
                break;
        }
    }

//    public enum SampleStates{
//        BLUE,
//        RED,
//        YELLOW,
//        READ,
//        SHUT_OFF
//    }

    public enum IntakeStates{
        intake,
        outtake,
        stop
    }
    public enum GripperStates{
        clamp,
        unclamp
    }
    public enum PivotStates {
        basketpos,
        chamberpos,
        snapclip,
        parallel,
        hookclip,
        forward,
        posauto_clip,
        preauto_clip
    }
}
