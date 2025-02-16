package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain.driveSpeed;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.Control.KalmanEstimator;
import org.firstinspires.ftc.teamcode.Control.PDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

@Config
public class VerticalSlides extends Subsystem {
    DcMotorEx rightslide,leftslide;
    public static double highchamber = 1300, lowbasket = 1830, kg = .18,hangPos = 2030,calculate, down = -1,
            highbasket =2900, ref = 0, normalTolerance = 15, greaterTolerance = 30, crazyBigTolerance = 38
            ,sensorcovariance =.7, modelcovariance = .2;
    public static boolean holdPos = false, hangISReady = false,runKalman = false;
    TouchSensor verticalSlidesTouchSensor;
    ElapsedTime time = new ElapsedTime();
    KalmanEstimator kalmanEstimator;
    public static PDCoefficients coefficients = new PDCoefficients(.012,.000000000002);
    PDController controller = new PDController(coefficients);
    @Override
    public void initAuto(HardwareMap hwMap) {
        runKalman = true;
        ref = 0;
        holdPos = false;
        hangISReady = false;
        verticalSlidesTouchSensor = hwMap.get(TouchSensor.class,"verticalslidestouchsensor");
        rightslide = hwMap.get(DcMotorEx.class,"rightslide");
        leftslide = hwMap.get(DcMotorEx.class,"leftslide");
        rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        resetSlides();
        kalmanEstimator = new KalmanEstimator(getSlidesPos(),sensorcovariance,modelcovariance,4);
    }
    @Override
    public void initTeleop(HardwareMap hwMap) {
        runKalman = false;
        ref = 0;
        holdPos = false;
        hangISReady = false;
        verticalSlidesTouchSensor = hwMap.get(TouchSensor.class,"verticalslidestouchsensor");
        rightslide = hwMap.get(DcMotorEx.class,"rightslide");
        leftslide = hwMap.get(DcMotorEx.class,"leftslide");
        rightslide.setDirection(DcMotorSimple.Direction.REVERSE);
        resetSlides();
    }

    @Override
    public void periodic() {
        if (runKalman){
            kalmanEstimator.update();
            Dashboard.addData("verticalslidepos",getSlidesPos());
            Dashboard.addData("kalmanedslides",kalmanEstimator.update());
        }
    }
    @Override
    public void shutdown() {
    }
    public double getVoltage(){
        return leftslide.getCurrent(CurrentUnit.AMPS);
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
            leftslide.setPower(-.4);
            rightslide.setPower(-.4);
        } else {
        }
    }
    public void updatePos(Input input, Robot robot, ScoringCommandGroups groups) {
//        if (input.getRight_stick_y() < -.9&& !hangISReady) {
//            hangISReady = true;
//            robot.getScheduler().forceCommand(groups.bringInHorizontalSLidesBetter().addNext(
//                    groups.moveClipMag(ClipMech.ArmStates.READY)).addNext(groups.slidesSetPos(hangPos,45)));
//        } else if (input.isShareButtonPressed()) {
//            hangISReady = false;
//        }
//        else
//            if (input.isRightStickButtonPressed() && hangISReady && robot.horizontalslides.getSetPos() == fullin) {
//            robot.getScheduler().forceCommand(new MultipleCommand(new MovePTOServos(robot.hang),groups.moveClipMag(ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK)));
//            driveSpeed = DriveTrain.DriveSpeed.CLIPSPEED;
//        }
//            else if (input.getRight_stick_y() > .9 && hangISReady) {
//            rightslide.setPower(down);
//            leftslide.setPower(down);
//            robot.driveTrain.moveDriveMotors(down*-1);
//        } else
//        else if (!hangISReady) {
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
                robot.getScheduler().forceCommand(new MultipleCommand(groups.slidesTeleop(),groups.movePivot(NewIntake.PivotStates.PRECLIP2), groups.moveCoAxial(NewIntake.CoaxialStates.CLAMP)));
        } else if (input.isRightBumperPressed() && ref == lowbasket) {
                ref = highbasket;
                robot.getScheduler().forceCommand(groups.slidesTeleop());
            } else if (input.isLeftBumperPressed() && ref == highbasket) {
                ref = lowbasket;
                robot.getScheduler().forceCommand(new MultipleCommand(groups.slidesTeleop(),groups.movePivot(NewIntake.PivotStates.PRECLIP2), groups.moveCoAxial(NewIntake.CoaxialStates.CLAMP)));
            } else if (ref == 0 && !touchSensorIsPressed()) {
                setPower(-.3);
            } else  if (ref == 0 && touchSensorIsPressed() && !input.isLeftBumperPressed() && !input.isRightBumperPressed()) {
            resetSlidesWithTimer();
            holdPos = false;
            zeroPower();
        } else if (holdPos && !touchSensorIsPressed() && !input.isLeftBumperPressed() && !input.isRightBumperPressed()) {
            leftslide.setPower(kg);
            rightslide.setPower(kg);
        }
//        }
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
