package org.firstinspires.ftc.teamcode.robot.Subsystems.Intake;

import static com.acmerobotics.roadrunner.ftc.GoBildaPinpointDriver.DEFAULT_ADDRESS;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.lynx.LynxI2cDeviceSynch;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.NewRR.RevColorSensorV3Better;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class JohnsIntake extends Subsystem {

    CRServo rightintake,leftintake;
    Servo gripper, rightpivot, leftpivot;

    SampleStates sampleStates;

    ElapsedTime timer = new ElapsedTime();

    public static double basketpos = .64, chamberpos = .68, down = 0.15,parallel = .26,lowerpickup = .13, opticalConstaint = 850,
            intakeSpeed = 1,outtake = -.3, redConstraint = 200, blueConstraint = 200,greenConstraint = 200, blueForYellowConstraint = 150, redForYellowConstraint = 150, colorSensorDelay = 1.2;//.78  .155

    RevColorSensorV3Better colorsensor;
    public static boolean speedRead = false;

    AnalogInput armanalog;

    @Override
    public void initAuto(HardwareMap hwMap) {
        rightintake = hwMap.get(CRServo.class,"rightintake");
        leftintake = hwMap.get(CRServo.class,"leftintake");
        gripper = hwMap.get(Servo.class,"gripper");
        rightpivot = hwMap.get(Servo.class,"rightarm");
        leftpivot = hwMap.get(Servo.class,"leftarm");
        armanalog = hwMap.get(AnalogInput.class,"armanalog");
        colorsensor = hwMap.get(RevColorSensorV3Better.class,"colorsensor");
//        if (speedRead){
//        ((LynxI2cDeviceSynch)(deviceClient)).setBusSpeed(LynxI2cDeviceSynch.BusSpeed.FAST_400K);
//            colorsensor.setBusSpeed(LynxI2cDeviceSynch.BusSpeed.FAST_400K);}

        leftintake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightpivot.setDirection(Servo.Direction.REVERSE);
        sampleStates = SampleStates.READ;
        timer.reset();
    }

    @Override
    public void periodic() {
        getColor();
        Dashboard.addData("optical",getOptical());
        Dashboard.addData("samplestate",sampleStates);
        Dashboard.addData("getPivotPos", getPivotPos());
    }

    @Override
    public void shutdown() {

    }

    public double getVoltage(){
        return armanalog.getVoltage();
    }

    public double getPivotPos(){
        return getVoltage() / 3.3 * 360;
    }

    public double getBlue(){
        return colorsensor.blue();
    }

    public double getRed(){
        return colorsensor.red();
    }

    public double getGreen(){
        return colorsensor.green();
    }

    public double getOptical(){
        return colorsensor.rawOptical();
    }

    public void getColor(){
        switch (sampleStates){
            case RED:

                break;
            case BLUE:

                break;
            case YELLOW:

                break;
            case LIGHT_ON:
//                ((SwitchableLight)colorsensor).enableLight(true);
                sampleStates = SampleStates.READCOLOR;
                break;
            case READ_SLIGHTDELAY:
                if (getRed() > redConstraint&& timer.seconds() > colorSensorDelay){
                    timer.reset();
                    sampleStates = SampleStates.RED;
                } else if (getBlue() > blueConstraint && timer.seconds()>colorSensorDelay){
                    timer.reset();
                    sampleStates = SampleStates.BLUE;
                } else if (getBlue() > blueForYellowConstraint && getRed() > redForYellowConstraint&& timer.seconds()>colorSensorDelay) {
                    timer.reset();
                    sampleStates = SampleStates.YELLOW;
                }
                break;
            case READ:
                if (getOptical()>opticalConstaint){
                    sampleStates = SampleStates.READAGAIN;
                }
                break;
            case READCOLOR:
//                Dashboard.addData("red",getRed());
//                Dashboard.addData("blue",getBlue());
//                Dashboard.addData("green",getGreen());
//                if (getRed() > 800 &&getGreen() > 500){
//                    sampleStates = SampleStates.YELLOW;
//                } else if (get){
//                    sampleStates = SampleStates.RED;
//                } else if (getBlue() > 200) {
//                    sampleStates = SampleStates.BLUE;
//                }
                break;
            case READAGAIN:
                Dashboard.addData("red",getRed());
                Dashboard.addData("blue",getBlue());
                Dashboard.addData("green",getGreen());
               if (getRed() > 1200 && getGreen() >2000&& getBlue() > 600){
                    sampleStates = SampleStates.YELLOW;
               }else if (getRed() > 800 && getBlue() <550){
                   sampleStates = SampleStates.RED;
               } else if (getBlue() > 1500) {
                    sampleStates = SampleStates.BLUE;
               }
                break;
            case LIGHT_OFF:
//                ((SwitchableLight)colorsensor).enableLight(false);
                sampleStates = SampleStates.EMPTY_STATE;
                break;
            case EMPTY_STATE:

                break;
        }
    }

    public void intakeTeleWithColorSensor(Input input, HorizontalSlides slides){
        if (input.isLeft_trigger_press()){
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setGripper(GripperStates.unclamp);
            setIntake(JohnsIntake.IntakeStates.outtake);
        } else if (input.isRight_trigger_press()) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
            setIntake(JohnsIntake.IntakeStates.intake);
            setArmStates(JohnsIntake.PivotStates.forward);
        }
//        else if (input.isRight_trigger_press() && slides.leftservoslide.getPosition() == halfout) {
//            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
//            setIntake(JohnsIntake.IntakeStates.intake);
//            setArmStates(PivotStates.slightly_lower_pickup);
//        }
        else if (slides.leftservoslide.getPosition() != fullin
                && !input.isRight_trigger_press() && !input.isLeft_trigger_press()) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setArmStates(JohnsIntake.PivotStates.parallel);
            setIntake(JohnsIntake.IntakeStates.stop);
        } else {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setIntake(JohnsIntake.IntakeStates.stop);
        }
    }

    public void intakeTele(Input input, HorizontalSlides slides){
        if (input.isLeft_trigger_press()){
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setGripper(GripperStates.unclamp);
            setIntake(JohnsIntake.IntakeStates.outtake);
        } else if (input.isRight_trigger_press()) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
            setIntake(JohnsIntake.IntakeStates.intake);
            setArmStates(JohnsIntake.PivotStates.forward);
        }
//        else if (input.isRight_trigger_press() && slides.leftservoslide.getPosition() == halfout) {
//            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
//            setIntake(JohnsIntake.IntakeStates.intake);
//            setArmStates(PivotStates.slightly_lower_pickup);
//        }
        else if (slides.leftservoslide.getPosition() != fullin
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
                rightintake.setPower(intakeSpeed);
                leftintake.setPower(intakeSpeed);
                break;
            case outtake:
                rightintake.setPower(outtake);
                leftintake.setPower(outtake);
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
                rightpivot.setPosition(down); // 121
                leftpivot.setPosition(down);
                break;
            case slightly_lower_pickup:
                rightpivot.setPosition(lowerpickup); // 121
                leftpivot.setPosition(lowerpickup);
                break;
            case posauto_clip:
                rightpivot.setPosition(0.25);
                leftpivot.setPosition(0.25);
                break;
            case preauto_clip:
                rightpivot.setPosition(0.4); // 121
                leftpivot.setPosition(0.4);
                break;
            case parallel:
                rightpivot.setPosition(parallel);
                leftpivot.setPosition(parallel);
                break;
            case hookclip:
                rightpivot.setPosition(.28);
                leftpivot.setPosition(.28);
                break;
            case snapclip:
                rightpivot.setPosition(.23);
                leftpivot.setPosition(.23); //143
                break;
            case basketpos:
                rightpivot.setPosition(basketpos);//227
                leftpivot.setPosition(basketpos);
                break;
            case chamberpos:
                rightpivot.setPosition(chamberpos);//227
                leftpivot.setPosition(chamberpos);
                break;
        }
    }

    public enum SampleStates{
        BLUE,
        RED,
        YELLOW,
        READ_SLIGHTDELAY,
        READCOLOR,
        READAGAIN,
        READ,
        LIGHT_ON,
        LIGHT_OFF,
        EMPTY_STATE
    }

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
        slightly_lower_pickup,
        posauto_clip,
        preauto_clip
    }
}
