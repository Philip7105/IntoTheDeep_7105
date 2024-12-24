package org.firstinspires.ftc.teamcode.robot.Subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;

public class ColorSensorIntake extends Subsystem {

    public ColorSensor colorSensorIntake;


    public ColorSensorIntake() {

    }

    @Override
    public void initAuto(HardwareMap hwMap) {
        colorSensorIntake=hwMap.get(ColorSensor.class, "intakecolorsensor");

    }

    @Override
    public void periodic() {

        double yellow = colorSensorIntake.red()+colorSensorIntake.green();
        double red = colorSensorIntake.red();
        double blue = colorSensorIntake.blue();
        double green =colorSensorIntake.green();
        Dashboard.addData("Red", red);
        Dashboard.addData("Blue", blue);
        Dashboard.addData("Green", green);
        Dashboard.addData("Yellow", yellow);

        if ((blue < yellow) || (blue < red)){
            Dashboard.addData("wrong color", "not blue");
        }



    }


    @Override
    public void shutdown() {

    }
}
