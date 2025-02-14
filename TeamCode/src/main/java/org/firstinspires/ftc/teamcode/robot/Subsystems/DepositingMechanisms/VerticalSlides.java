package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain.driveSpeed;

import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.Control.PDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.Control.PDController;
import org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands.MovePTOServos;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;
@Config
public class VerticalSlides extends Subsystem {
    DcMotor rightslide,leftslide;
    public static double highchamber = 1300, lowbasket = 1830, kg = .18,hangPos = 2030,calculate, down = -.85,
            highbasket =2900, ref = 0, normalTolerance = 15, greaterTolerance = 30;
    public static boolean holdPos = false, hangISReady = false;
    TouchSensor verticalSlidesTouchSensor;
    ElapsedTime time = new ElapsedTime();
    public static PDCoefficients coefficients = new PDCoefficients(.012,.000000000002);
    PDController controller = new PDController(coefficients);
    @Override
    public void initAuto(HardwareMap hwMap) {
        ref = 0;
        holdPos = false;
        hangISReady = false;
        verticalSlidesTouchSensor = hwMap.get(TouchSensor.class,"verticalslidestouchsensor");
        rightslide = hwMap.get(DcMotor.class,"rightslide");
        leftslide = hwMap.get(DcMotor.class,"leftslide");
        rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        resetSlides();
    }
    @Override
    public void periodic() {
//        Dashboard.addData("verticalslidepos",getSlidesPos());
        Dashboard.addData("reference",ref);
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
    public void updatePos(Input input, Robot robot, ScoringCommandGroups groups) {
        if (input.getRight_stick_y() < -.8&& !hangISReady) {
            hangISReady = true;
            robot.getScheduler().forceCommand(groups.bringInHorizontalSLidesBetter().addNext(
                    groups.moveClipMag(ClipMech.ArmStates.READY)).addNext(groups.slidesSetPos(hangPos,45)));
        } else if (input.isShareButtonPressed()) {
            hangISReady = false;
        } else if (input.isRightStickButtonPressed() && hangISReady && robot.horizontalslides.getSetPos() == fullin) {
            robot.getScheduler().forceCommand(new MultipleCommand(new MovePTOServos(robot.hang),groups.moveClipMag(ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK)));
            driveSpeed = DriveTrain.DriveSpeed.CLIPSPEED;
        } else if (input.getRight_stick_y() > .8 && hangISReady) {
            rightslide.setPower(down);
            leftslide.setPower(down);
            robot.driveTrain.moveDriveMotors(down*-1);
        } else if (ref == 0 && touchSensorIsPressed() && !input.isLeftBumperPressed() && !input.isRightBumperPressed()) {
                resetSlidesWithTimer();
                driveSpeed = DriveTrain.DriveSpeed.FAST;
                holdPos = false;
                zeroPower();
        } else if (holdPos && !touchSensorIsPressed() && !input.isLeftBumperPressed() && !input.isRightBumperPressed()) {
                leftslide.setPower(kg);
                rightslide.setPower(kg);
                driveSpeed = DriveTrain.DriveSpeed.MEDIUM;
        }else if (!hangISReady) {
            if (input.isRightBumperPressed() && ref == 0) {
                ref = highchamber;
                robot.getScheduler().forceCommand((groups.slidesTeleop()));
            } else if (input.isLeftBumperPressed() && ref == highchamber) {
                ref = 0;
                robot.getScheduler().forceCommand(groups.slidesTeleop());
            } else if (input.isRightBumperPressed() && ref == highchamber) {
                ref = lowbasket;
                robot.getScheduler().forceCommand(groups.slidesTeleop());
            } else if (input.isLeftBumperPressed() && ref == lowbasket) {
                ref = highchamber;
                robot.getScheduler().forceCommand(groups.slidesTeleop());
            } else if (input.isRightBumperPressed() && ref == lowbasket) {
                ref = highbasket;
                robot.getScheduler().forceCommand(groups.slidesTeleop());
            } else if (input.isLeftBumperPressed() && ref == highbasket) {
                ref = lowbasket;
                robot.getScheduler().forceCommand(groups.slidesTeleop());
            } else if (ref == 0 && !touchSensorIsPressed()) {
                setPower(-.3);
            }
        }
    }
    public double getSlidesPos(){
        return leftslide.getCurrentPosition();
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
