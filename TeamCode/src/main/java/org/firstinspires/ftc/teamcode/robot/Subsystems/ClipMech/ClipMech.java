package org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

@Config
public class ClipMech extends Subsystem {
    Servo rightmagarm,leftmagarm,rightindex,leftindex;
    public static double pushnone = .1,pushone = .22,pushtwo = .31,pushthree = .395, pushfour = .485,fully_up = .85,ready = 0.53,hookclip = 0.64
            ,outtheway = .7, down = .04, almost_down = .19, target, downencoderpos = 334, almostdownencoderpos = 281, backbeforeclip = .61, backbeforeclipencoderpos = 146,
    readyencoderpos = 171,outthewayencoderpos = 116, preclip = .8, hookclipencoderpos = 137,preclipencoderpos = 84,clippity_clappity_clickity_clickencoderpos = 69;
    public static boolean cliparmdone = false, reverseLeftIndex = false, reverseRightIndex = false;
    AnalogInput clipAnalog;
//    Input input;
    LeftIndexState leftIndexState;
    RightIndexState rightIndexState;
//    public ClipMech(Input input){
//        this.input = input;
//    }
    @Override
    public void initAuto(HardwareMap hwMap) {
        rightindex = hwMap.get(Servo.class,"rightindex");
        leftindex = hwMap.get(Servo.class,"leftindex");
        rightmagarm = hwMap.get(Servo.class,"rightmagarm");
        leftmagarm = hwMap.get(Servo.class,"leftmagarm");
        leftIndexState = LeftIndexState.OUTOFTHEWAY;
        rightIndexState = RightIndexState.OUTOFTHEWAY;
        leftmagarm.setDirection(Servo.Direction.REVERSE);
        rightmagarm.setDirection(Servo.Direction.REVERSE);
            leftindex.setDirection(Servo.Direction.REVERSE);
        if (reverseRightIndex){
            rightindex.setDirection(Servo.Direction.REVERSE);
        }

        clipAnalog = hwMap.get(AnalogInput.class, "clipanalog");
        cliparmdone = false;
    }
    @Override
    public void periodic() {
        Dashboard.addData("clipmagpos",getClipMagPos());
    }
    public double getClipMagPos(){
        return getVoltage() / 3.3 * 360;
    }
    public double getClipMagError(){
        return Math.abs(target - getClipMagPos());
    }

    public double getVoltage(){
        return clipAnalog.getVoltage();
    }

    @Override
    public void shutdown() {
    }
    public void setArmStates(ArmStates armStates){
        switch (armStates){
            case CLIPPITY_CLAPPITY_CLICKITY_CLICK:
                rightmagarm.setPosition(fully_up);
                leftmagarm.setPosition(fully_up);
                break;
            case READY:
                rightmagarm.setPosition(ready);
                leftmagarm.setPosition(ready);
                break;
            case PRECLIP:
                rightmagarm.setPosition(preclip);
                leftmagarm.setPosition(preclip);
                break;
            case PRECLIP2:
                rightmagarm.setPosition(.7); //117
                leftmagarm.setPosition(.7);
                break;
            case PRECLIP3:
                rightmagarm.setPosition(0.565); // 160
                leftmagarm.setPosition(0.565);
                break;
            case BACKBEFORECLIP:
                rightmagarm.setPosition(backbeforeclip);
                leftmagarm.setPosition(backbeforeclip);
                    break;
            case HOOKCLIP:
                rightmagarm.setPosition(hookclip);
                leftmagarm.setPosition(hookclip);
                break;
            case OUT_THE_WAYOFHORIZONTALSLIDES:
                rightmagarm.setPosition(outtheway);
                leftmagarm.setPosition(outtheway);
                break;
            case ALMOST_DOWN:
                rightmagarm.setPosition(almost_down);
                leftmagarm.setPosition(almost_down);
                break;
            case DOWN:
                rightmagarm.setPosition(down);
                leftmagarm.setPosition(down);
                break;
        }
    }

    public void setLeftIndex(Input input){
        switch (leftIndexState){
            case PUSHONE:
                leftindex.setPosition(pushone);
                if (input.isRightTriggerPressed()){
                    leftIndexState = LeftIndexState.PUSHTWO;
                }
                break;
            case PUSHTWO:
                leftindex.setPosition(pushtwo);
                if (input.isRightTriggerPressed()){
                    leftIndexState = LeftIndexState.PUSHTHREE;
                }
                break;
            case PUSHTHREE:
                leftindex.setPosition(pushthree);
                if (input.isRightTriggerPressed()){
                    leftIndexState = LeftIndexState.PUSHFOUR;
                }
                break;
            case PUSHFOUR:
                leftindex.setPosition(pushfour);
                if (input.isRightTriggerPressed()){
                    leftIndexState = LeftIndexState.OUTOFTHEWAY;
                }
                break;
            case OUTOFTHEWAY:
                leftindex.setPosition(pushnone);
                if (input.isRightTriggerPressed()){
                    leftIndexState = LeftIndexState.PUSHONE;
                }
                break;
        }
    }

    public void setRightIndex(Input input){
        switch (rightIndexState){
            case PUSHONE:
                rightindex.setPosition(pushone);
                if (input.isLeftTriggerPressed()){
                    rightIndexState = RightIndexState.PUSHTWO;
                }
                break;
            case PUSHTWO:
                rightindex.setPosition(pushtwo);
                if (input.isLeftTriggerPressed()){
                    rightIndexState = RightIndexState.PUSHTHREE;
                }
                break;
            case PUSHTHREE:
                rightindex.setPosition(pushthree);
                if (input.isLeftTriggerPressed()){
                    rightIndexState = RightIndexState.PUSHFOUR;
                }
                break;
            case PUSHFOUR:
                rightindex.setPosition(pushfour);
                if (input.isLeftTriggerPressed()){
                    rightIndexState = RightIndexState.OUTOFTHEWAY;
                }
                break;
            case OUTOFTHEWAY:
                rightindex.setPosition(pushnone);
                if (input.isLeftTriggerPressed()){
                    rightIndexState = RightIndexState.PUSHONE;
                }
                break;
        }
    }

    public enum ArmStates{
        CLIPPITY_CLAPPITY_CLICKITY_CLICK, // This states is for fully engaging the clips
        READY,
        PRECLIP,
        PRECLIP2,
        PRECLIP3,
        HOOKCLIP,
        OUT_THE_WAYOFHORIZONTALSLIDES,
        ALMOST_DOWN,
        BACKBEFORECLIP,
        DOWN
    }

    public enum RightIndexState{
        PUSHONE,
        PUSHTWO,
        PUSHTHREE,
        PUSHFOUR,
        OUTOFTHEWAY
    }

    public enum LeftIndexState {
        PUSHONE,
        PUSHTWO,
        PUSHTHREE,
        PUSHFOUR,
        OUTOFTHEWAY
    }
}
