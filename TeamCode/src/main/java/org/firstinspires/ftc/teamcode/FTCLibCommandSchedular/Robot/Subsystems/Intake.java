package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullout;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfout;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class Intake extends SubsystemBase {

    CRServo rightintake,leftintake;
    Servo gripper,rightarm,leftarm;
    public static double clamp = .74, unclamp = 0.245,basketpos = .64,preclip = .29, hookclip = .3,chamberpos = .76, down = 0.14, parallel = .26,lowerpickup = .13,intakeSlow = .2,intakeSpeed = 1,outtake = -.6;//.78  .155

    SampleStates sampleStates;
    RevColorSensorV3 colorsensor;

    AnalogInput armanalog;

    HardwareMap hwMap;

    public Intake(HardwareMap hwMap){
        this.hwMap = hwMap;
    }

    @Override
    public void register() {
        super.register();
        colorsensor = hwMap.get(RevColorSensorV3.class,"colorsensor");
        rightintake = hwMap.get(CRServo.class,"rightintake");
        leftintake = hwMap.get(CRServo.class,"leftintake");
        gripper = hwMap.get(Servo.class,"gripper");
        rightarm = hwMap.get(Servo.class,"rightarm");
        leftarm = hwMap.get(Servo.class,"leftarm");
        armanalog = hwMap.get(AnalogInput.class,"armanalog");

        sampleStates = SampleStates.READ;
        leftintake.setDirection(DcMotorSimple.Direction.REVERSE);
        rightarm.setDirection(Servo.Direction.REVERSE);
    }
    @Override
    public void periodic() {
        Dashboard.addData("red",getRed());
        Dashboard.addData("blue",getBlue());
        Dashboard.addData("green",getGreen());
        Dashboard.addData("optical",getOptical());
        Dashboard.addData("samplestates",sampleStates);
        runColorSensor();
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

    public void runColorSensor(){
        switch (sampleStates){
            case RED:

                break;
            case BLUE:

                break;
            case YELLOW:

                break;
            case READ:
                if (getOptical() > 450){
                    sampleStates = SampleStates.READCOLOR;
                }
                break;
            case READCOLOR:
                if (getBlue() > 1000){
                    sampleStates = SampleStates.BLUE;
                } else if (getGreen() > 2000) {
                    sampleStates = SampleStates.YELLOW;
                } else {
                    sampleStates = SampleStates.RED;
                }
                break;
            case EMPTY:

                break;
        }
    }

    public void intakeTele(Input input, HorizontalSlides slides, Input input2){
        if (input2.isCross()) {
            setGripper(GripperStates.UNCLAMP);
            setIntake(IntakeStates.STOP);
            setPivotStates(PivotStates.FORWARD);
        } else if (input.isLeft_trigger_press()){
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setGripper(GripperStates.UNCLAMP);
            setIntake(Intake.IntakeStates.OUTTAKE);
        } else if (input.isRight_trigger_press()&& slides.leftservoslide.getPosition() == halfout) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
            setIntake(IntakeStates.INTAKESLOW);
            setPivotStates(PivotStates.SLIGHTLY_LOWER_PICKUP);
        } else if (input.isRight_trigger_press() && slides.leftservoslide.getPosition() == fullout) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
            setIntake(Intake.IntakeStates.INTAKE);
            setPivotStates(Intake.PivotStates.FORWARD);
        }
//        else if (input.isRight_trigger_press() && slides.leftservoslide.getPosition() == halfout) {
//            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Slow;
//            setIntake(JohnsIntake.IntakeStates.intake);
//            setArmStates(PivotStates.slightly_lower_pickup);
//        }
        else if (slides.leftservoslide.getPosition() != fullin
                && !input.isRight_trigger_press() && !input.isLeft_trigger_press()) {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setPivotStates(Intake.PivotStates.PARALLEL);
            setIntake(Intake.IntakeStates.STOP);
        } else {
            DriveTrain.driveSpeed = DriveTrain.DriveSpeed.Fast;
            setIntake(Intake.IntakeStates.STOP);
        }
    }

    public void setIntake(IntakeStates intakeStates){
        switch (intakeStates){
            case INTAKE:
                rightintake.setPower(intakeSpeed);
                leftintake.setPower(intakeSpeed);
                break;
            case INTAKESLOW:
                rightintake.setPower(intakeSlow);
                leftintake.setPower(intakeSlow);
                break;
            case OUTTAKE:
                rightintake.setPower(outtake);
                leftintake.setPower(outtake);
                break;
            case OUTTAKEAUTO:
                rightintake.setPower(-.7);
                leftintake.setPower(-.7);
                break;
            case STOP:
                rightintake.setPower(0);
                leftintake.setPower(0);
                break;
        }
    }

    public void setGripper(GripperStates gripperStates) {
        switch (gripperStates){
            case UNCLAMP:
                gripper.setPosition(unclamp);
                break;
            case CLAMP:
                gripper.setPosition(clamp);
                break;
        }
    }

    public void setPivotStates(PivotStates pivotStates){
        switch (pivotStates){
            case FORWARD:
                rightarm.setPosition(down); // 121
                leftarm.setPosition(down);
                break;
            case SLIGHTLY_LOWER_PICKUP:
                rightarm.setPosition(lowerpickup); // 121
                leftarm.setPosition(lowerpickup);
                break;
            case PARALLEL:
                rightarm.setPosition(parallel);
                leftarm.setPosition(parallel);
                break;
            case HOOKCLIP:
                rightarm.setPosition(hookclip);
                leftarm.setPosition(hookclip);
                break;
            case PRECLIP:
                rightarm.setPosition(preclip);
                leftarm.setPosition(preclip);
                break;
            case SNAPCLIP:
                rightarm.setPosition(.2);
                leftarm.setPosition(.2); //143
                break;
            case BASKETPOS:
                rightarm.setPosition(basketpos);//227
                leftarm.setPosition(basketpos);
                break;
            case CHAMBERPOS:
                rightarm.setPosition(chamberpos);//227
                leftarm.setPosition(chamberpos);
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
        STOP
    }
    public enum GripperStates{
        CLAMP,
        UNCLAMP
    }
    public enum PivotStates {
        BASKETPOS,
        CHAMBERPOS,
        SNAPCLIP,
        PARALLEL,
        HOOKCLIP,
        PRECLIP,
        CLIP,
        FORWARD,
        SLIGHTLY_LOWER_PICKUP
    }
}
