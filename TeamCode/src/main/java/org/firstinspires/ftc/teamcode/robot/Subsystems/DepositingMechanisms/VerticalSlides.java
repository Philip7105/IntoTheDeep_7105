package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain.driveSpeed;

import org.firstinspires.ftc.teamcode.Control.PDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.Control.PDController;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;

@Config
public class VerticalSlides extends Subsystem {
    DcMotor rightslide,leftslide;
    public static double intakeslidesup = 140, clipoffwall = 390, highchamber = 1300, lowbasket = 1850, kg = .128, hangPos = 2030,getslideposrr,calculate, highbasket =2900, ref = 0, down = -.7, normalTolerance = 15, greaterTolerance = 30;
    public static boolean holdPos = false, useBasketPos = false, hangISReady = false;
    TouchSensor verticalSlidesTouchSensor;
    ElapsedTime time = new ElapsedTime();
    public VerticalSlideStates verticalSlideStates;
    public static PDCoefficients coefficients = new PDCoefficients(.01,.000000000002);
    PDController controller = new PDController(coefficients);
//    Input input;

//    public VerticalSlides(Input input){
//        this.input = input;
//    }
    @Override
    public void initAuto(HardwareMap hwMap) {
//        verticalSlideStates = VerticalSlideStates.TOUCHSENSORPRESSED;
        ref = 0;
        holdPos = false;
        hangISReady = false;
        useBasketPos = false;
        verticalSlidesTouchSensor = hwMap.get(TouchSensor.class,"verticalslidestouchsensor");
        rightslide = hwMap.get(DcMotor.class,"rightslide");
        leftslide = hwMap.get(DcMotor.class,"leftslide");
        rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        resetSlides();
    }
    @Override
    public void periodic() {
        Dashboard.addData("verticalslidepos",getSlidesPos());
        Dashboard.addData("reference",ref);
        Dashboard.addData("touchsensor",touchSensorIsPressed());
//        setVerticalSlideStates(input);
    }
    @Override
    public void shutdown() {
    }
    public void pdController(){
        calculate = controller.calculate(ref,getSlidesPos());
        leftslide.setPower(calculate + kg);
        rightslide.setPower(calculate + kg);
    }
    public void getAndSetPower(){
        double getPow = leftslide.getPower();
        leftslide.setPower(getPow);
        rightslide.setPower(getPow);
    }
    public void zeroPower(){
        leftslide.setPower(0);
        rightslide.setPower(0);
    }
    public void setPower(double power){
        leftslide.setPower(power);
        rightslide.setPower(power);
    }
    public double getSlidesError(){
        return ref - getSlidesPos();
    }
    public boolean touchSensorIsPressed(){
        return verticalSlidesTouchSensor.isPressed();
    }

    public void updatePosAuto(){
        if (!touchSensorIsPressed() && holdPos){
            leftslide.setPower(kg);
            rightslide.setPower(kg);
        } else if (touchSensorIsPressed()){
            resetSlidesWithTimer();
            zeroPower();
        }else if (ref == 0 && !touchSensorIsPressed()){
            leftslide.setPower(-.3);
            rightslide.setPower(-.3);
        } else {
        }
    }

    public void updatePos(Input input, Robot robot, ScoringCommandGroups groups){
        //TODO: Test the slides to see if they weren't working because we didnt have "ref == 0" in the first if statement
//        inputmanual = input;
//        if (input.isRightBumperPressed() && ref == 0){
////            hangISReady =false;
//            ref = highchamber;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
//        }  else if (input.isLeftBumperPressed() && ref == highchamber){
////            hangISReady =false;
//            ref = 0;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
//        } else if (input.isRightBumperPressed() && ref == highchamber){
////            hangISReady =false;
//            ref = lowbasket;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
//        } else
        if (input.getLeft_stick_x() > .8) {
            hangISReady = true;
            leftslide.setPower(down * -1);
            rightslide.setPower(down * -1);
            robot.driveTrain.moveDriveMotors(down);
//            ref = hangPos;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.getRight_stick_x() > .8 && hangISReady) {
            leftslide.setPower(down);
            rightslide.setPower(down);
            robot.driveTrain.moveDriveMotors(down * -1);
        } else {
            leftslide.setPower(0);
            rightslide.setPower(0);
        }
//        else if (input.isLeftBumperPressed() && ref == lowbasket){
////            hangISReady =false;
//            ref = highchamber;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
//        } else if (input.isRightBumperPressed() && ref == lowbasket){
////            hangISReady =false;
//            ref = highbasket;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
//        } else if (input.isLeftBumperPressed() && ref == highbasket){
////            hangISReady =false;
//            ref = lowbasket;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
//        } else if (ref == 0 && !touchSensorIsPressed()) {
////            hangISReady =false;
//            setPower(-.3);
//        } else if (ref == 0 && touchSensorIsPressed() && !input.isLeftBumperPressed() &&!input.isRightBumperPressed()
////                && inputmanual.getLeft_stick_y() < .7
//        ){
////            hangISReady =false;
//            resetSlidesWithTimer();
//            driveSpeed = DriveTrain.DriveSpeed.Fast;
//            holdPos = false;
//            zeroPower();
//        }else if (holdPos && !touchSensorIsPressed() && !input.isLeftBumperPressed() &&!input.isRightBumperPressed()
////                && inputmanual.getLeft_stick_y() < .7
//        ){
////            hangISReady =false;
//            leftslide.setPower(kg);
//            rightslide.setPower(kg);
//            driveSpeed = DriveTrain.DriveSpeed.MEDIUM;
//        }
//        else if (input.isRightStickButtonPressed()){
//            ref = hangPos;
//            robot.getScheduler().forceCommand(groups.slidesTeleop());
//        }
        //else if (input.isLeftStickButtonPressed()){
          //  ref = 0;
       //     robot.getScheduler().forceCommand(groups.slidesTeleop());
     //   } else if (inputmanual.getLeft_stick_y() > .7){
       //     leftslide.setPower(down);
         //   rightslide.setPower(down);
     //   }
    }

    public void setVerticalSlideStates(Input input){
        switch (verticalSlideStates){
            case HIGHBASKET:
                ref = highbasket;
                pdController();
                if (input.isLeftBumperPressed()) {
                    verticalSlideStates = VerticalSlideStates.LOWBASKET;
                }
                break;
                case HOLDPOS_HIGHBASKET:
                    leftslide.setPower(kg);
                    rightslide.setPower(kg);
                    if (input.isLeftBumperPressed()){
                        verticalSlideStates = VerticalSlideStates.LOWBASKET;
                    }
                break;
            case LOWBASKET:
                ref = lowbasket;
                pdController();
                if (input.isRightBumperPressed()){
                    verticalSlideStates = VerticalSlideStates.HIGHBASKET;
                }else if (input.isLeftBumperPressed()) {
                    verticalSlideStates = VerticalSlideStates.HIGHCHAMBER;
                }
                break;
            case HOLDPOS_LOWBASKET:
                leftslide.setPower(kg);
                rightslide.setPower(kg);

                if (input.isRightBumperPressed()){
                    verticalSlideStates = VerticalSlideStates.HIGHBASKET;
                }else if (input.isLeftBumperPressed()) {
                    verticalSlideStates = VerticalSlideStates.HIGHCHAMBER;
                }
                break;
            case HIGHCHAMBER:
                ref = highchamber;
                pdController();
                if (input.isRightBumperPressed()){
                    verticalSlideStates = VerticalSlideStates.LOWBASKET;
                }else if (input.isLeftBumperPressed()) {
                    verticalSlideStates = VerticalSlideStates.TARGETPOSDOWN;
                }
                break;
            case HOLDPOS_HIGHCHAMBER:
                leftslide.setPower(kg);
                rightslide.setPower(kg);

                if (input.isRightBumperPressed()){

                } else if (input.isLeftBumperPressed()){

                }
                break;
            case PICKUPOFFWALL:
                ref = clipoffwall;
                pdController();
                if (input.isDpadUpPressed()){
                    verticalSlideStates = VerticalSlideStates.PICKUPSUBMERSIBLE;
                } else if (input.isDpad_down()){
                    verticalSlideStates = VerticalSlideStates.TARGETPOSDOWN;
                }
                break;
            case HOLDPOS_PICKUPOFFWALL:
                leftslide.setPower(kg);
                rightslide.setPower(kg);
                if (input.isDpadUpPressed()){
                    verticalSlideStates = VerticalSlideStates.TARGETPOSDOWN;
                } else if (input.isDpad_down()){
                    verticalSlideStates = VerticalSlideStates.PICKUPSUBMERSIBLE;
                }
                break;
            case PICKUPSUBMERSIBLE:
                ref = intakeslidesup;
                pdController();
                if (input.isDpadUpPressed()){
                    verticalSlideStates = VerticalSlideStates.PICKUPSUBMERSIBLE;
                } else if (input.isDpad_down()){
                    verticalSlideStates = VerticalSlideStates.TARGETPOSDOWN;
                }
                break;
            case HOLDPOS_PICKUPOUTOFTHESUBMERSIBLE:
                leftslide.setPower(kg);
                rightslide.setPower(kg);
                if (input.isDpadUpPressed()){
                    verticalSlideStates = VerticalSlideStates.PICKUPSUBMERSIBLE;
                } else if (input.isDpad_down()){
                    verticalSlideStates = VerticalSlideStates.TARGETPOSDOWN;
                }
                break;
            case TARGETPOSDOWN:
                if (touchSensorIsPressed()){
                    verticalSlideStates = VerticalSlideStates.TOUCHSENSORPRESSED;
                }
                break;
            case TOUCHSENSORPRESSED:
                resetSlidesWithTimer();
                driveSpeed = DriveTrain.DriveSpeed.Fast;
                zeroPower();

                if (!touchSensorIsPressed()){
                    verticalSlideStates = VerticalSlideStates.TOUCHSENSOR_NOTPRESSED;
                }
                break;
            case TOUCHSENSOR_NOTPRESSED:
                leftslide.setPower(-.3);
                rightslide.setPower(-.3);
                if (touchSensorIsPressed()){
                    verticalSlideStates = VerticalSlideStates.TOUCHSENSORPRESSED;
                }
                break;
        }
    }

    public double getSlidesPos(){
        return leftslide.getCurrentPosition();
    }
    public void getSlidePosRR(){
        getslideposrr = leftslide.getCurrentPosition();
    }
    public void resetSlides(){
        leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
    public void resetSlidesWithTimer(){
        if (time.seconds() > 1.5){
            time.reset();
        leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);}
    }
    public enum VerticalSlideStates{
        HIGHBASKET,
        LOWBASKET,
        TOUCHSENSOR_NOTPRESSED,
        TOUCHSENSORPRESSED,
        TARGETPOSDOWN,
        PICKUPOFFWALL,
        PICKUPSUBMERSIBLE,
        HOLDPOS_HIGHBASKET,
        HOLDPOS_LOWBASKET,
        HOLDPOS_HIGHCHAMBER,
        HOLDPOS_PICKUPOFFWALL,
        HOLDPOS_PICKUPOUTOFTHESUBMERSIBLE,
        HIGHCHAMBER
    }
}
