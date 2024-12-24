package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

@Config
public class VerticalSlides extends Subsystem {

    DcMotor rightslide,leftslide;

    public static double  lowchamber = 1210,lowestchamber = 1757, hangPos = 2030, lowbasket = 1630, highbasket =2875, ref, down = -.3;
    public static boolean closeThread = false, holdPos = false;

    public static PIDCoefficients coefficients = new PIDCoefficients(.008,0,.000000000002);

    BasicPID controller = new BasicPID(coefficients);

    double calculate;

    @Override
    public void initAuto(HardwareMap hwMap) {
        ref = 0;
        closeThread = false;
        holdPos = false;
        rightslide = hwMap.get(DcMotor.class,"rightslide");
        leftslide = hwMap.get(DcMotor.class,"leftslide");

        rightslide.setDirection(DcMotorSimple.Direction.REVERSE);

        resetSlides();
    }

    @Override
    public void periodic() {
        Dashboard.addData("verticalslidepos",getSlidesPos());
        Dashboard.addData("reference",ref);
        Dashboard.addData("slidepower",leftslide.getPower());
        Dashboard.addData("closethread",closeThread);


        if (holdPos && ref != 0
//                && inputmanual.getLeft_stick_y() < .7
        ){
//            setPower(.075);
            leftslide.setPower(.128);
            rightslide.setPower(.128);
        } else if (ref == 0
//                && inputmanual.getLeft_stick_y() < .7
        ){
            holdPos = false;
            zeroPower();
        }
//        else {

//        }

    }

    @Override
    public void shutdown() {

    }

    public void pidController(){
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


    public void updatePos(Input input, Robot robot, ScoringCommandGroups groups){
//        inputmanual = input;
        if (input.isRightBumperPressed() && ref == 0){
            ref = lowchamber;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isLeftBumperPressed() && ref == lowchamber){
            ref = 0;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isRightBumperPressed() && ref == lowchamber){
            ref = lowestchamber;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isLeftBumperPressed() && ref == lowestchamber){
            ref = lowchamber;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isRightBumperPressed() && ref == lowestchamber){
            ref = highbasket;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
        } else if (input.isLeftBumperPressed() && ref == highbasket){
            ref = lowestchamber;
            robot.getScheduler().forceCommand(groups.slidesTeleop());
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

    public void resetSlides(){
//        slidepos = 0;
        leftslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightslide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
