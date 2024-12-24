package org.firstinspires.ftc.teamcode.robot.Subsystems.Intake;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;

@Config
public class GirlsIntake extends Subsystem {

    CRServo bothintake;

    public static double outback = .78, down = .14;

    ColorSensor colorsensor;

    @Override
    public void initAuto(HardwareMap hwMap) {
        bothintake = hwMap.get(CRServo.class,"Intake");

        colorsensor = hwMap.get(ColorSensor.class,"colorsensor");
    }

    @Override
    public void periodic() {

    }

    @Override
    public void shutdown() {

    }

    public void setIntake(IntakeStates intakeStates){
        switch (intakeStates){
            case intake:
                bothintake.setPower(1);
                break;
            case outtake:
                bothintake.setPower(-1);
                break;
            case stop:
                bothintake.setPower(0);
                break;
        }
    }



    public enum IntakeStates{
        intake,
        outtake,
        stop
    }



}
