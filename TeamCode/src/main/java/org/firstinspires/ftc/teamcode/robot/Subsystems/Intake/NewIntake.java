package org.firstinspires.ftc.teamcode.robot.Subsystems.Intake;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullout;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfout;
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
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;
@Config
public class NewIntake extends Subsystem {
    ElapsedTime time = new ElapsedTime();
    CRServo intake;
    Servo coaxial,rightarm;
    public static double shovelpos = .15, offthewallcoaxial = .44,offthewallpivot = 0.24,clamppos = .49, clampposforselfclip = 0.44, clampposoutback =  .92,releasepos = 0.8,
             snapclip = 0.1,overheadpos = 0, hookclippivot = .232,chamberpos = .63, down = 0.13,
            parallel = 0.23,lowerpickup = .13, pivotoverheadpos = .21,pivotpostselfclip = .265,coaxialpostselfclip = .56,intakeSlow = .6,intakeSpeed = 1,outtake = -.6;
    public static boolean yellowSample = false, blueSample = false, redSample = false,enableAutoSelfClip = true;
    RevColorSensorV3 colorsensor;
    AnalogInput armanalog;
    @Override
    public void initAuto(HardwareMap hwMap) {
        colorsensor = hwMap.get(RevColorSensorV3.class,"colorsensor");
        intake = hwMap.get(CRServo.class,"intake");
        coaxial = hwMap.get(Servo.class,"coaxial");
        rightarm = hwMap.get(Servo.class,"rightarm");
        armanalog = hwMap.get(AnalogInput.class,"armanalog");
        redSample = false;
        blueSample = false;
        yellowSample = false;
        enableAutoSelfClip = true;

        rightarm.setDirection(Servo.Direction.REVERSE);
    }
    @Override
    public void periodic() {
        Dashboard.addData("green",getGreen());
        Dashboard.addData("red",getRed());
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
    public void intakeTeleBlue(Input input,Input input2,Robot robot, ScoringCommandGroups groups){
        if (redSample){
            setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
            setPivotStates(PivotStates.PARALLEL);
            setIntake(IntakeStates.OUTTAKE);
            runColorSensorBlue(groups,robot);
        }else if (input2.isSquare() && !redSample&&!blueSample&&!yellowSample) {
            setIntake(IntakeStates.INTAKESLOW);
            setPivotStates(PivotStates.OFFTHEWALL);
            setCoaxial(CoaxialStates.OFFTHEWALL);
        } else if (input.isLeft_trigger_press()&&robot.horizontalslides.leftservoslide.getPosition() == fullin){
            setCoaxial(CoaxialStates.RELEASE);
            setIntake(IntakeStates.INTAKEOUTTAKE);
            setPivotStates(PivotStates.CHAMBERPOS);
        }else if (robot.horizontalslides.leftservoslide.getPosition() != fullin && !blueSample&&!redSample && !yellowSample
                && robot.horizontalslides.leftservoslide.getPosition() != prepselfclip) {
            if (input.isRight_trigger_press()){
                setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
                setPivotStates(PivotStates.SHOVELPIVOTPOS);
                setIntake(IntakeStates.INTAKE);
                runColorSensorBlue(groups,robot);
            } else if (input.isRight_bumper()){
                setCoaxial(CoaxialStates.OVERHEADPOS);
                setPivotStates(PivotStates.OVERHEADPOS);
                setIntake(IntakeStates.INTAKE);
                runColorSensorBlue(groups,robot);
            } else if (input.isLeft_trigger_press()) {
                setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
                setPivotStates(PivotStates.PARALLEL);
                setIntake(IntakeStates.OUTTAKE);
            } else {
                setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
                setPivotStates(PivotStates.PARALLEL);
                setIntake(IntakeStates.STOP);
            }
        } else if (robot.horizontalslides.leftservoslide.getPosition() != prepselfclip &&!blueSample &&
                robot.horizontalslides.leftservoslide.getPosition() != fullin
                && !redSample && !yellowSample){
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setIntake(NewIntake.IntakeStates.STOP);
            setPivotStates(PivotStates.PARALLEL);
            setCoaxial(CoaxialStates.CLAMP);
        }
    }
    public void runColorSensorBlue(ScoringCommandGroups groups, Robot robot){
        if (getOptical() > 1800){
            if (getGreen() > 3200) {
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
                if (enableAutoSelfClip) {
                    robot.getScheduler().forceCommand(groups.prepSelfClip().addNext(groups.clipClip2()));
                } else {
                    robot.getScheduler().forceCommand(groups.bringInHorizontalSLidesBetter());
                }
            } else if (getRed() > 2000 && getGreen() < 2000) {
                //red
                blueSample= false;
                yellowSample = false;
                redSample = true;
            }
        } else {
            blueSample= false;
            yellowSample = false;
            redSample = false;
        }
    }
    public void intakeTeleRed(Input input,Input input2,Robot robot, ScoringCommandGroups groups){
    if (blueSample){
        setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
        setPivotStates(PivotStates.PARALLEL);
        setIntake(IntakeStates.OUTTAKE);
        runColorSensorRed(groups,robot);
    }else if (input2.isSquare() && !redSample&&!blueSample&&!yellowSample) {
        setIntake(IntakeStates.INTAKESLOW);
        setPivotStates(PivotStates.OFFTHEWALL);
        setCoaxial(CoaxialStates.OFFTHEWALL);
    } else if (input.isLeft_trigger_press()&&robot.horizontalslides.leftservoslide.getPosition() == fullin){
        setCoaxial(CoaxialStates.RELEASE);
        setIntake(IntakeStates.INTAKEOUTTAKE);
        setPivotStates(PivotStates.CHAMBERPOS);
    }else if (robot.horizontalslides.leftservoslide.getPosition() != fullin && !blueSample&&!redSample && !yellowSample
            && robot.horizontalslides.leftservoslide.getPosition() != prepselfclip) {
        if (input.isRight_trigger_press()){
            setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
            setPivotStates(PivotStates.SHOVELPIVOTPOS);
            setIntake(IntakeStates.INTAKE);
            runColorSensorRed(groups,robot);
        } else if (input.isRight_bumper()){
            setCoaxial(CoaxialStates.OVERHEADPOS);
            setPivotStates(PivotStates.OVERHEADPOS);
            setIntake(IntakeStates.INTAKE);
            runColorSensorRed(groups,robot);
        } else if (input.isLeft_trigger_press()) {
            setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
            setPivotStates(PivotStates.PARALLEL);
            setIntake(IntakeStates.OUTTAKE);
        } else {
            setCoaxial(CoaxialStates.COAXIALSHOVELPOS);
            setPivotStates(PivotStates.PARALLEL);
            setIntake(IntakeStates.STOP);
        }
    } else if (robot.horizontalslides.leftservoslide.getPosition() != prepselfclip &&
            robot.horizontalslides.leftservoslide.getPosition() != fullin&&!blueSample
            && !redSample && !yellowSample){
        DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
        setIntake(NewIntake.IntakeStates.STOP);
        setPivotStates(PivotStates.PARALLEL);
        setCoaxial(CoaxialStates.CLAMP);
    }
}
    public void runColorSensorRed(ScoringCommandGroups groups, Robot robot){
        if (getOptical() > 1800){
            if (getGreen() > 3200) {
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
            } else if (getRed() > 2000 && getGreen() < 2000) {
                //red
                blueSample= false;
                yellowSample = false;
                redSample = true;
                if (enableAutoSelfClip) {
                    robot.getScheduler().forceCommand(groups.prepSelfClip().addNext(groups.clipClip2()));
                } else {
                    robot.getScheduler().forceCommand(groups.bringInHorizontalSLidesBetter());
                }
            }
        } else {
            blueSample= false;
            yellowSample = false;
            redSample = false;
        }
    }
    public void setIntake(IntakeStates intakeStates){
        switch (intakeStates){
            case INTAKE:
                intake.setPower(intakeSpeed);
                time.reset();
                break;
            case INTAKESLOW:
                intake.setPower(intakeSlow);
                time.reset();
                break;
            case OUTTAKE:
                intake.setPower(outtake);
                time.reset();
                break;
            case OUTTAKEAUTO:
                intake.setPower(-.7);
                time.reset();
                break;
            case STOP:
                intake.setPower(0);
                time.reset();
                break;
            case INTAKEOUTTAKE:
                if (time.seconds() > .3){
                    intake.setPower(outtake);
                } else {
                    intake.setPower(intakeSpeed);
                }
                break;
        }
    }
    public void setCoaxial(CoaxialStates coaxialstates) {
        switch (coaxialstates){
            case POSTSELFCLIP:
                coaxial.setPosition(coaxialpostselfclip);
                break;
            case CLAMP:
                coaxial.setPosition(clamppos);
                break;
            case PRECLIP:
                coaxial.setPosition(.4);
                break;
            case CLAMPSELFCLIP:
                coaxial.setPosition(clampposforselfclip);
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
            case POSTSELFCLIP:
                rightarm.setPosition(pivotpostselfclip);
                    break;
            case SHOVELPIVOTPOS:
                rightarm.setPosition(down); // 121
                break;
            case OVERHEADPOS:
                rightarm.setPosition(pivotoverheadpos);
                break;
            case SLIGHTLY_LOWER_PICKUP:
                rightarm.setPosition(lowerpickup); // 121
                break;
            case PARALLEL:
                rightarm.setPosition(parallel);
                break;
            case PRECLIP:
                rightarm.setPosition(.16);
                break;
            case PRECLIP2:
                rightarm.setPosition(.18);
                break;
            case HOOKCLIP:
                rightarm.setPosition(hookclippivot);
                break;
            case SNAPCLIP:
                rightarm.setPosition(snapclip);
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
        CLAMPSELFCLIP,
        CLAMP,
        POSTSELFCLIP,
        PRECLIP,
        RELEASE,
    }
    public enum PivotStates {
        OFFTHEWALL,
        CHAMBERPOSBOTH,
        POSTSELFCLIP,
        CHAMBERPOS,
        PARALLEL,
        OVERHEADPOS,
        HOOKCLIP,
        PRECLIP2,
        PRECLIP,
        SNAPCLIP,
        SHOVELPIVOTPOS,
        SLIGHTLY_LOWER_PICKUP
    }
}