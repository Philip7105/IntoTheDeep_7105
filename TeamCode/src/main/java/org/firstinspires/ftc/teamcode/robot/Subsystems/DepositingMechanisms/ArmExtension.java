package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

public class ArmExtension extends Subsystem {

    DcMotor armextension;
//    public static final double COUNTS_PER_REVOLUTION = 751.8; // Adjust as needed

    public static double ref = 0, fullyContracted = 300, maxExtension = 500;

    public static final int GEAR_RATIO = 3, minArmExtensionConstraint = 0, maxArmExtensionConstraint = 600; // Adjust as needed

    public static PIDCoefficients pid = new PIDCoefficients(0.2,0.2,0.2);

    BasicPID controller = new BasicPID(pid);  // create aPID controller

    @Override
    public void initAuto(HardwareMap hwMap) {
        ref = 0;
        armextension = hwMap.get(DcMotor.class, "extendtridentarm"); // hardware map arm
        resetArmExtension();
    }

    @Override
    public void periodic() {
        Dashboard.addData("armextensionpos",armextension.getCurrentPosition());
    }

    @Override
    public void shutdown() {
        armextension.setPower(0);
    }

    //    TODO: Check to make sure this method works.
//    public double ticksToDegrees(double ticks) {
//        // Calculate the degrees per tick
//        double degreesPerTick = 360.0 / (COUNTS_PER_REVOLUTION * GEAR_RATIO);
//        // Convert ticks to degrees
//        return ticks * degreesPerTick;
//    }

    public void armExtensionPID(double ref){  // use PID to go to reference
        if (ref < maxArmExtensionConstraint && ref > minArmExtensionConstraint) {
            armextension.setPower(controller.calculate(ref, getArmExtensionPos()));
            // as long as the reference value is good, use PID to calculate the power for the arm to get to the reference value
        } else {
            shutdown();  // otherwise don't do anything
        }
    }

    public void armExtensionPID_Ticks(double ref){  // has arm go to position but no constraints on if the position is good
        armextension.setPower(controller.calculate(ref, getArmExtensionPos()));
    }

    public double getArmExtensionError(){  // find the difference between the arm's current position and 0.  Used to determine if the arm is at a resting position
        return ref - getArmExtensionPos();
    }

    public void resetArmExtension(){
        armextension.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armextension.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);  // reset the arm's modes
    }
    public double getArmExtensionPos(){
        return armextension.getCurrentPosition();  // return the arm's position
    }

    public void setArmExtensionStates(ArmExtensionStates armExtensionStates){  // set all the arm states for the arm
        switch (armExtensionStates){
            case MaxExtension:  // score in the high basket
                ref = maxExtension;
                armExtensionPID(ref);
                break;
            case FullyContracted:  // score in the low basket
                ref = fullyContracted;
                armExtensionPID(ref);
                break;
        }
    }

    // name for the arm's states
    public enum ArmExtensionStates {
        MaxExtension,
        FullyContracted
    }

}
