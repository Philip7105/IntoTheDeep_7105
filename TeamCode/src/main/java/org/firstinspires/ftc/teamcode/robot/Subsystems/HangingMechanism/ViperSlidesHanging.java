package org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;

/** This is code for the hanging mechanism.
 * Includes diffrent hanging levels
 */
@Config
public class ViperSlidesHanging extends Subsystem {

    //TODO test this position for the first level hang
    public static int hangFirstLevel = 500;
    public static int minslideconstraint =0, maxslideconstraint=1200;
    public static double Kp = 0;
    public static double Ki = 0;
    public static double Kd = 0;

    public static PIDCoefficients pid = new PIDCoefficients(Kp,Ki,Kd);

    BasicPID controller = new BasicPID(pid);  // PID controller

    static DcMotor ViperSlideOne;
    static DcMotor ViperSlideTwo;

    public ViperSlidesHanging(){
    }

    @Override
    public void initAuto(HardwareMap hwMap) {

        ViperSlideOne = hwMap.get(DcMotor.class, "ViperSlidesOne");
        ViperSlideTwo = hwMap.get(DcMotor.class, "ViperSlidesTwo");

        ViperSlideTwo.setDirection(DcMotorSimple.Direction.REVERSE);

        ViperSlideOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ViperSlideTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ViperSlideOne.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ViperSlideOne.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);



    }

    @Override
    public void periodic() {

    }

    @Override
    public void shutdown() {
        ViperSlideOne.setPower(0);
        ViperSlideTwo.setPower(0);

    }

    public static double getViperSlideOnePos() {return ViperSlideOne.getCurrentPosition();}  // get the position of the first lead screw motor

    public static double getViperSlideTwoPos() {return ViperSlideTwo.getCurrentPosition();}  // get the position fo the second lead screw motor

//    public static doubl
//
//    e ticksToDegrees(int ticks) {
        // Calculate the degrees per tick
//        double degreesPerTick = 360.0 / (COUNTS_PER_REVOLUTION * GEAR_RATIO);
        // Convert ticks to degrees
//        return ticks * degreesPerTick;
//    }

    public void ViperSlideTwoPID(int ref) {
        // use PID for the second viper slide
        if (ref < maxslideconstraint && ref > minslideconstraint) {
            ViperSlideTwo.setPower(controller.calculate(ref, getViperSlideTwoPos()));
            // go to the reference position if it is within acceptable boundaries
        } else {
            ViperSlideTwo.setPower(0);  // otherwise don't do anything
        }
    }

    public void ViperSlideOnePID(int ref){
        // use PID for the first viper slide
        if (ref < maxslideconstraint && ref > minslideconstraint){
            ViperSlideOne.setPower(controller.calculate(ref, getViperSlideOnePos()));
            // go to the reference position if it is within acceptable boundaries
        }
        else {
            ViperSlideOne.setPower(0);  // otherwise don't do anything
        }

    }

    public void ViperSlideOnePIDTicks(int ref) {
        ViperSlideOne.setPower(controller.calculate(ref, getViperSlideOnePos()));
        // use PID to set first lead screw to a position.  No constraints on what the position will be
    }

    public void LeadScrewTwoPIDTicks(int ref) {
        ViperSlideTwo.setPower(controller.calculate(ref,getViperSlideTwoPos()));
        // use PID to set second lead screw to a position.  No constraints on what the position will be
    }

    public void setViperSlideStates(ViperSlideStates viperslidestates){

        switch(viperslidestates){
            case Down:
                // when not hanging
                ViperSlideOnePID(0);
                ViperSlideTwoPID(0);
                break;
            case HangFirstLevel:
                // position for hanging
                ViperSlideTwoPID(hangFirstLevel);
                ViperSlideOnePID(hangFirstLevel);
                break;
        }
    }



    public double getViperSlideOneError(int ref) {return ref-getViperSlideOnePos();}

    public double getViperSlideTwoError(int ref) {return ref - getViperSlideTwoPos();}

    // names for lead screw states
    public enum ViperSlideStates{
        Down,
        HangFirstLevel
    }


}

