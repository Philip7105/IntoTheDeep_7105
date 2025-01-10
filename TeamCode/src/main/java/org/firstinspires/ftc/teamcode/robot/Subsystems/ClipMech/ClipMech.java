package org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

@Config
public class ClipMech extends Subsystem {

    Servo rightmagarm,leftmagarm;

    CRServo rightindex,leftindex;

    public static double fully_up = .9,ready = 0.605,outtheway = .57, down = 0, almost_down = .19;

    AnalogInput clipAnalog;

    @Override
    public void initAuto(HardwareMap hwMap) {
        rightindex = hwMap.get(CRServo.class,"rightindex");
        leftindex = hwMap.get(CRServo.class,"leftindex");
        rightmagarm = hwMap.get(Servo.class,"rightmagarm");
        leftmagarm = hwMap.get(Servo.class,"leftmagarm");
        leftmagarm.setDirection(Servo.Direction.REVERSE);
        rightmagarm.setDirection(Servo.Direction.REVERSE);
        clipAnalog = hwMap.get(AnalogInput.class, "clipanalog");
    }

    @Override
    public void periodic() {
//        Dashboard.addData("clipmagpos",getClipMagPos());
    }

    public double getClipMagPos(){
        return clipAnalog.getVoltage() / 3.3 * 360;
    }

    public double getVoltage(){
        return clipAnalog.getVoltage();
    }

    @Override
    public void shutdown() {
        setArmStates(ArmStates.SHUTOFF);
    }
    public void setArmStates(ArmStates armStates){
        switch (armStates){
            case Clippity_Clappity_Clickity_Click: //51
                rightmagarm.setPosition(fully_up);
                leftmagarm.setPosition(fully_up);
                break;
            case READY:
                rightmagarm.setPosition(ready); //115
                leftmagarm.setPosition(ready);
                break;
            case Out_The_Way:
                rightmagarm.setPosition(outtheway);
                leftmagarm.setPosition(outtheway); //179
                break;
            case Almost_Down:
                rightmagarm.setPosition(almost_down);//279
                leftmagarm.setPosition(almost_down);
                break;
            case Down:
                rightmagarm.setPosition(down);
                leftmagarm.setPosition(down);//359
                break;
            case SHUTOFF:
                rightmagarm.setPosition(0);
                leftmagarm.setPosition(0);//359
                break;
        }
    }

    public void setIndex(IndexState indexState){
        switch (indexState){
            case ZEROPOWER:
                leftindex.setPower(0);
                rightindex.setPower(0);
                break;
            case LeftSide:
                leftindex.setPower(0);
                rightindex.setPower(1);
                break;
            case RightSide:
                leftindex.setPower(1);
                rightindex.setPower(0);
                break;
            case RELOAD:
                leftindex.setPower(-1);
                rightindex.setPower(-1);
                break;
        }
    }

    public enum ArmStates{
        Clippity_Clappity_Clickity_Click, // This states is for fully engaging the clips
        READY,
        Out_The_Way,
        Almost_Down,
        Down,
        SHUTOFF
    }

    public enum IndexState{
        ZEROPOWER,
        RightSide,
        LeftSide,
        RELOAD
    }
}
