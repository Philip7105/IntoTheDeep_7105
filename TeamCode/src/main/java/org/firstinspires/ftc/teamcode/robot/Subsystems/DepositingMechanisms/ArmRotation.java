package org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms;

import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.BasicPID;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

/** This is code for the arm
 * Currently is just kooky arm.
 */
@Config
public class ArmRotation extends Subsystem {

    // Define the counts per revolution for your encoder
    public static final double COUNTS_PER_REVOLUTION = 751.8; // Adjust as needed

    // Define the gear ratio (output gear teeth / input gear teeth)
    public static final int GEAR_RATIO = 3, turnminarmconstraint =-10, turnmaxarmconstraint = 900; // Adjust as needed

    public static int ref = 0, uprightninetydegrees = 760;

    DcMotor armrotation;

    public static PIDCoefficients pidturn = new PIDCoefficients(.017,0,.00005);

    BasicPID turncontroller = new BasicPID(pidturn);

    @Override
    public void initAuto(HardwareMap hwMap) {
        ref = 0;
        armrotation = hwMap.get(DcMotor.class, "turntridentarm");
        resetArmRotation();
    }
    @Override
    public void periodic() {
        Dashboard.addData("armrotationpos",armrotation.getCurrentPosition());
        armRotationPID_Ticks(ref);
    }

    @Override
    public void shutdown() {
        // shutdown
        armrotation.setPower(0);

    }

    public void resetArmRotation() {
        armrotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armrotation.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    // Method to convert encoder ticks to degrees
    public double getArmRotationPos(){return armrotation.getCurrentPosition();}

    //    TODO: Check to make sure this method works.
    public double ticksToDegrees(double ticks) {
        // Calculate the degrees per tick
        double degreesPerTick = 360.0 / (COUNTS_PER_REVOLUTION * GEAR_RATIO);
        // Convert ticks to degrees
        return ticks * degreesPerTick;
    }

    public void armRotationPID(double ref){
        if (ref < turnmaxarmconstraint && ref > turnminarmconstraint){
            armrotation.setPower(turncontroller.calculate(ticksToDegrees(ref), getArmRotationPos()));
        } else{
            shutdown();
        }
    }

    public void armRotationPID_Ticks(double ref){
        if (ref < turnmaxarmconstraint && ref > turnminarmconstraint){
            armrotation.setPower(turncontroller.calculate(ref, getArmRotationPos()));
        } else{
            shutdown();
        }
    }


    public void setArmRotateStates(ArmRotationStates armRotationStates){
        switch (armRotationStates){
            case HighBucket_Back:
                ref = uprightninetydegrees;
//                armRotationPID_Ticks(ref);
                break;
            case LowBucket_Back:
                ref = uprightninetydegrees;
//                armRotationPID_Ticks(ref);
                break;
            case HighBucket_Front:
                ref = uprightninetydegrees;
//                armRotationPID_Ticks(ref);
                break;
            case LowBucket_Front:
                ref = uprightninetydegrees;
//                armRotationPID_Ticks(ref);
                break;
            case HighChamber:
                ref = uprightninetydegrees;
//                armRotationPID_Ticks(ref);
                break;
            case Low_Chamber:
                ref = uprightninetydegrees;
//                armRotationPID_Ticks(ref);
                break;
            case PrePickUp:
                ref = uprightninetydegrees;
//                armRotationPID_Ticks(ref);
                break;
            case PickUp:
                ref = 0;
//                armRotationPID_Ticks(ref);
                break;
        }
    }

    public double getArmRotateError() {
        return ref - getArmRotationPos();
    }

    public enum ArmRotationStates {
        HighBucket_Back,
        LowBucket_Back,
        HighBucket_Front,
        LowBucket_Front,
        HighChamber,
        Low_Chamber,
        PrePickUp,
        PickUp
    }
}
