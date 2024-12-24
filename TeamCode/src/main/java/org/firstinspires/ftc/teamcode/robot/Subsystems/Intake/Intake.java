package org.firstinspires.ftc.teamcode.robot.Subsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;

/**Code for the intake claw.
 */

@Config
public class Intake extends Subsystem {

    public CRServo intake;  // intake wheel

    public Servo twist; // the turning thing that rotates

    public Servo wrist;  // flip-flop



    @Override
    public void initAuto(HardwareMap hwMap) {
        wrist = hwMap.get(Servo.class, "wrist");
        intake = hwMap.get(CRServo.class,"intake");  // hardware map
        twist = hwMap.get(Servo.class, "twist");
    }

    @Override
    public void initTeleop(HardwareMap hwMap) {
        wrist = hwMap.get(Servo.class, "wrist");
        intake = hwMap.get(CRServo.class,"intake");  // hardware map
        twist = hwMap.get(Servo.class, "twist");

    }
    @Override
    public void periodic() {

    }

    //TODO add something for shutdown
    @Override
    public void shutdown() {

    }

    public void setIntakePower(IntakePower intakePower){
        // set the intake power (the intake wheel)
        switch(intakePower){
            case Intake:
                intake.setPower(1);  // intake
            case Outtake:
                intake.setPower(-1);  // outtake
            case Stop:
                intake.setPower(0);  // stop
        }
    }

    public void setWrist(Wrist wriststates){
        // set the wrist's position
        switch(wriststates){
            case Rest:
                wrist.setPosition(0.0);  // set the wrist to rest
            case IntakeSample:
                wrist.setPosition(0.0);  // set the wrist for intaking a sample.  Note that it is hte same position as Rest, but we have different names for easy reading and debugging.
            case OuttakeSample:
                wrist.setPosition(0.2);  // outtake a sample
            case PlacingSpecimin:
                wrist.setPosition(0.0);  // place a specimen
        }

    }

    public void setTwist(Twist twiststates){
        // twist the intake to positions
        switch(twiststates){
            case Rest:
                twist.setPosition(0.0);  // rest
            case PlacingSpecimin:
                twist.setPosition(0.25);  // place a specimen
            case IntakeSample:
                twist.setPosition(0);  // intake a sample
            case OuttakeSample:
                twist.setPosition(0); // outake a sample
        }
    }


// names for wrist positions
    public enum Wrist{
        PlacingSpecimin,
        IntakeSample,
        OuttakeSample,
        Rest
    }
// names for intake powers
    public enum IntakePower{
        Intake,
        Outtake,
        Stop
    }
// names for twist positions
    public enum Twist{
        PlacingSpecimin,
        IntakeSample,
        OuttakeSample,
        Rest
    }
}
