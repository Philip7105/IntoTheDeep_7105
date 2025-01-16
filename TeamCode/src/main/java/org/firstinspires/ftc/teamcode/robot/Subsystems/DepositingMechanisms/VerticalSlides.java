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
    public static double  lowchamber = 2650, lowbasket = 3500, hangPos = 2030,getslideposrr,calculate, highbasket =5700, ref = 0, down = -.3, normalTolerance = 15, greaterTolerance = 30;
    public static boolean holdPos = false;
    TouchSensor verticalSlidesTouchSensor;
    ElapsedTime time = new ElapsedTime();
    public static PDCoefficients coefficients = new PDCoefficients(.008,.000000000002);
    PDController controller = new PDController(coefficients);
    @Override
    public void initAuto(HardwareMap hwMap) {
        ref = 0;
        holdPos = false;
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
    }
    @Override
    public void shutdown() {
    }
    public void pdController(){
        calculate = controller.calculate(ref,getSlidesPos());
        leftslide.setPower(calculate);
        rightslide.setPower(calculate);
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
            leftslide.setPower(.128);
            rightslide.setPower(.128);
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
        if (input.isRightBumperPressed() && ref == 0){
            ref = lowchamber;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        }  else if (input.isLeftBumperPressed() && ref == lowchamber){
            ref = 0;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isRightBumperPressed() && ref == lowchamber){
            ref = lowbasket;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isLeftBumperPressed() && ref == lowbasket){
            ref = lowchamber;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isRightBumperPressed() && ref == lowbasket){
            ref = highbasket;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isLeftBumperPressed() && ref == highbasket){
            ref = lowbasket;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (ref == 0 && !touchSensorIsPressed()) {
            setPower(-.3);
        } else if (ref == 0 && touchSensorIsPressed() && !input.isLeftBumperPressed() &&!input.isRightBumperPressed()
//                && inputmanual.getLeft_stick_y() < .7
        ){
            resetSlidesWithTimer();
            driveSpeed = DriveTrain.DriveSpeed.Fast;
            holdPos = false;
            zeroPower();
        }else if (holdPos && !touchSensorIsPressed() && !input.isLeftBumperPressed() &&!input.isRightBumperPressed()
//                && inputmanual.getLeft_stick_y() < .7
        ){
            leftslide.setPower(.128);
            rightslide.setPower(.128);
            driveSpeed = DriveTrain.DriveSpeed.MEDIUM;
        }
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
}
