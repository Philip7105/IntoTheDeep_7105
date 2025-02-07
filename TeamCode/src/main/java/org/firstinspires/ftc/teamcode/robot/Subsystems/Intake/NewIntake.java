package org.firstinspires.ftc.teamcode.robot.Subsystems.Intake;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullout;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfout;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.prepreclip;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.prepselfclip;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class NewIntake extends Subsystem {
    ElapsedTime time = new ElapsedTime();
    CRServo rightintake,leftintake;
    Servo coaxial,rightarm;
    public static double shovelpos = .05, offthewallcoaxial = .3,offthewallpivot = 0.26, hoverpos = .4, clamppos = 0.44, clampposoutback =  0.86,releasepos = 0.7,
            basketpos = .64,preclippivot = 0.1,overheadpos = 0, preclipcoaxial = .46, hookclippivot = .18,hookclipcoaxial = 0.44 ,chamberpos = .65, down = 0.14,
            parallel = .23,lowerpickup = .13,intakeSlow = .6,intakeSpeed = 1,outtake = -.6;//.78  .155

//    0.35
    public static boolean shovelMode = false, overHeadMode = false, yellowSample = false, blueSample = false, redSample = false;
//    SampleStates sampleStates;
    RevColorSensorV3 colorsensor;

    AnalogInput armanalog;

    @Override
    public void initAuto(HardwareMap hwMap) {
        colorsensor = hwMap.get(RevColorSensorV3.class,"colorsensor");
        rightintake = hwMap.get(CRServo.class,"rightintake");
        leftintake = hwMap.get(CRServo.class,"leftintake");
        coaxial = hwMap.get(Servo.class,"coaxial");
        rightarm = hwMap.get(Servo.class,"rightarm");
        armanalog = hwMap.get(AnalogInput.class,"armanalog");
        redSample = false;
        blueSample = false;
        yellowSample = false;
        shovelMode = true;
        overHeadMode = false;

//        sampleStates = SampleStates.READ;
        leftintake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightarm.setDirection(Servo.Direction.REVERSE);
    }
    @Override
    public void periodic() {
//        Dashboard.addData("samplestates",sampleStates);
//        runColorSensor();
    }

    @Override
    public void shutdown() {

    }

    public double getArmPos(){
        return armanalog.getVoltage() / 3.3 * 360;
    }

    public double getOptical(){
        return colorsensor.rawOptical();
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
//
//    public void runColorSensor(){
//        switch (sampleStates){
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
//                if (getOptical() > 450){
//                    sampleStates = SampleStates.READCOLOR;
//                }
//                break;
//            case READCOLOR:
//                if (getBlue() > 1000){
//                    sampleStates = SampleStates.BLUE;
//                } else if (getGreen() > 2000) {
//                    sampleStates = SampleStates.YELLOW;
//                } else {
//                    sampleStates = SampleStates.RED;
//                }
//                break;
//            case EMPTY:
//
//                break;
//        }
//    }

    public void runColorSensor(ScoringCommandGroups groups, Robot robot){
        if (getOptical() > 1800){
            if (getGreen() > 4000) {
                //yellow
                blueSample= false;
                redSample = false;
                yellowSample = true;
                robot.getScheduler().forceCommand(groups.bringInHorizontalSLidesBetter());
            } else if (getBlue() > 2200) {
                //blue
                redSample= false;
                yellowSample = false;
                blueSample = true;
//                robot.getScheduler().forceCommand(groups.bringInHorizontalSLidesBetter());
                robot.getScheduler().forceCommand(groups.prepSelfClip().addNext(groups.clipClip()));
            } else if (getRed() > 2000 && getGreen() < 3400) {
                //red
                blueSample= false;
                yellowSample = false;
                redSample = true;
//                robot.getScheduler().forceCommand(groups.bringInHorizontalSLidesBetter());
                robot.getScheduler().forceCommand(groups.prepSelfClip().addNext(groups.clipClip()));
            }
        }
    }

    public void intakeTeleNew(Input input,Input input2,Robot robot, ScoringCommandGroups groups){
        if (input.isRightBumperPressed() && !shovelMode &&!blueSample && !redSample && !yellowSample&& robot.horizontalslides.leftservoslide.getPosition() != fullin&& robot.horizontalslides.leftservoslide.getPosition() != prepselfclip){
            shovelMode = true;
            overHeadMode = false;
        } else if (input.isRightBumperPressed() && shovelMode && robot.horizontalslides.leftservoslide.getPosition() != fullin
                && robot.horizontalslides.leftservoslide.getPosition() != prepselfclip){
            shovelMode = false;
            overHeadMode = true;
        }else if (input2.isSquare()) {
            shovelMode = false;
            overHeadMode = false;
            setIntake(IntakeStates.INTAKESLOW);
            setPivotStates(PivotStates.OFFTHEWALL);
            setCoaxial(CoaxialStates.OFFTHEWALL);
        } else if (input.isLeft_trigger_press()&&robot.horizontalslides.leftservoslide.getPosition() == fullin){
            setCoaxial(CoaxialStates.RELEASE);
            setIntake(IntakeStates.INTAKEOUTTAKE);
            setPivotStates(PivotStates.CHAMBERPOS);
        }else if (shovelMode && robot.horizontalslides.leftservoslide.getPosition() != fullin &&!blueSample && !redSample && !yellowSample
                && robot.horizontalslides.leftservoslide.getPosition() != prepselfclip) {
            if (input.isRight_trigger_press()){
                setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
                setPivotStates(PivotStates.FORWARD);
                setIntake(IntakeStates.INTAKE);
                runColorSensor(groups,robot);
            }  else if (input.isLeft_trigger_press()) {
                setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
                setPivotStates(PivotStates.PARALLEL);
                setIntake(IntakeStates.OUTTAKE);
            } else {
                setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
                setPivotStates(PivotStates.PARALLEL);
                setIntake(IntakeStates.STOP);
            }
        } else if (overHeadMode && robot.horizontalslides.leftservoslide.getPosition() != fullin
                && robot.horizontalslides.leftservoslide.getPosition() != prepselfclip
        && !blueSample && !redSample && !yellowSample) {
            if (input.isRight_trigger_press()){
                setCoaxial(CoaxialStates.OVERHEADPOS);
                setPivotStates(PivotStates.PARALLEL);
                setIntake(IntakeStates.INTAKE);
                runColorSensor(groups,robot);
            } else if (input.isLeft_trigger_press()) {
                setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
                setPivotStates(PivotStates.PARALLEL);
                setIntake(IntakeStates.OUTTAKE);
            } else {
                setCoaxial(CoaxialStates.OVERHEADPOS);
                setPivotStates(PivotStates.PARALLEL);
                setIntake(IntakeStates.STOP);
            }
        } else if (robot.horizontalslides.leftservoslide.getPosition() != prepselfclip && !blueSample && !redSample && !yellowSample){
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setIntake(NewIntake.IntakeStates.STOP);
            setPivotStates(PivotStates.PARALLEL);
            setCoaxial(CoaxialStates.CLAMP);
        }
    }

    public void intakeTele(Input input, HorizontalSlides slides,Input input2){
        if (input.isLeft_trigger_press()&& slides.leftservoslide.getPosition() != fullin ){
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
            setIntake(NewIntake.IntakeStates.OUTTAKE);
            setPivotStates(PivotStates.PARALLEL);
        } else if (input.isLeft_trigger_press() && slides.leftservoslide.getPosition() == fullin) {
//            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
            setCoaxial(CoaxialStates.RELEASE);
            setIntake(IntakeStates.INTAKEOUTTAKE);
            setPivotStates(PivotStates.CHAMBERPOS);
//            setIntake(JohnsIntake.IntakeStates.intake);
//            setArmStates(PivotStates.slightly_lower_pickup);
        } else if (input2.isSquare()) {
            setIntake(IntakeStates.INTAKESLOW);
            setPivotStates(PivotStates.OFFTHEWALL);
            setCoaxial(CoaxialStates.OFFTHEWALL);
        } else if (input.isRight_trigger_press()&& slides.leftservoslide.getPosition() == halfout) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.MEDIUM;
            setIntake(NewIntake.IntakeStates.INTAKE);
            setPivotStates(NewIntake.PivotStates.FORWARD);
            setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
        } else if (input.isRight_trigger_press() && slides.leftservoslide.getPosition() == fullout) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.MEDIUM;
            setIntake(NewIntake.IntakeStates.INTAKE);
            setPivotStates(NewIntake.PivotStates.FORWARD);
            setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
        }
        else if (slides.leftservoslide.getPosition() != fullin
                && !input.isRight_trigger_press() && !input.isLeft_trigger_press()) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setIntake(IntakeStates.STOP);
            setPivotStates(NewIntake.PivotStates.PARALLEL);
            setCoaxial(CoaxialStates.CLAMP);
        } else {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setIntake(NewIntake.IntakeStates.STOP);
        }
    }

    public void setIntake(IntakeStates intakeStates){
        switch (intakeStates){
            case INTAKE:
                rightintake.setPower(intakeSpeed);
                leftintake.setPower(intakeSpeed);
                time.reset();
                break;
            case INTAKESLOW:
                rightintake.setPower(intakeSlow);
                leftintake.setPower(intakeSlow);
                time.reset();
                break;
            case OUTTAKE:
                rightintake.setPower(outtake);
                leftintake.setPower(outtake);
                time.reset();
                break;
            case OUTTAKEAUTO:
                rightintake.setPower(-.7);
                leftintake.setPower(-.7);
                time.reset();
                break;
            case STOP:
                rightintake.setPower(0);
                leftintake.setPower(0);
                time.reset();
                break;
            case INTAKEOUTTAKE:
                if (time.seconds() > .3){
                    rightintake.setPower(outtake);
                    leftintake.setPower(outtake);
                } else {
                    rightintake.setPower(intakeSpeed);
                    leftintake.setPower(intakeSpeed);
                }
                break;
        }
    }

    public void setCoaxial(CoaxialStates coaxialstates) {
        switch (coaxialstates){
            case CLAMP:
                coaxial.setPosition(clamppos);
                break;
            case OVERHEADPOS:
                coaxial.setPosition(overheadpos);
                break;
            case RELEASE:
                coaxial.setPosition(releasepos);
                break;
            case COAXIALSHOVELPOS:
                coaxial.setPosition(shovelpos);
                break;
            case COAXIALHOVERPOS:
                coaxial.setPosition(hoverpos);
                break;
            case OFFTHEWALL:
                coaxial.setPosition(offthewallcoaxial);
                break;
        }
    }

    public void setPivotStates(PivotStates pivotStates){
        switch (pivotStates){
            case OFFTHEWALL:
                rightarm.setPosition(offthewallpivot);
                break;
            case FORWARD:
                rightarm.setPosition(down); // 121
                break;
            case SLIGHTLY_LOWER_PICKUP:
                rightarm.setPosition(lowerpickup); // 121
                break;
            case PARALLEL:
                rightarm.setPosition(parallel);
                break;
            case HOOKCLIP:
                rightarm.setPosition(hookclippivot);
                coaxial.setPosition(hookclipcoaxial);
                break;
            case PRECLIP:
                rightarm.setPosition(preclippivot);
                coaxial.setPosition(preclipcoaxial);
                break;
            case SNAPCLIP:
                rightarm.setPosition(.2);
                break;
            case BASKETPOS:
                rightarm.setPosition(basketpos);//227
                break;
            case CHAMBERPOS:
                rightarm.setPosition(chamberpos);//227
                break;
            case CHAMBERPOSBOTH:
                rightarm.setPosition(chamberpos);//227
                coaxial.setPosition(clampposoutback);
                break;
        }
    }

    public enum SampleStates{
        BLUE,
        RED,
        YELLOW,
        READ,
        READCOLOR,
        EMPTY
    }

    public enum IntakeStates{
        INTAKE,
        INTAKESLOW,
        OUTTAKE,
        OUTTAKEAUTO,
        STOP,
        INTAKEOUTTAKE
    }
    public enum CoaxialStates {
        OFFTHEWALL,
        OVERHEADPOS,
        COAXIALSHOVELPOS,
        CLAMP,
        RELEASE,
        COAXIALHOVERPOS
    }
    public enum PivotStates {
        OFFTHEWALL,
        BASKETPOS,
        CHAMBERPOSBOTH,
        CHAMBERPOS,
        SNAPCLIP,
        PARALLEL,
        HOOKCLIP,
        PRECLIP,
        FORWARD,
        SLIGHTLY_LOWER_PICKUP
    }
}