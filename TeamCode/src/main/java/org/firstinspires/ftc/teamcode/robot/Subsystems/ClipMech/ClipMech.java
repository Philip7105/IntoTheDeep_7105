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

    Servo rightmagarm,leftmagarm,rightindex,leftindex;

    public static double pushnone = .18,pushone = .2,pushtwo = .23,pushthree = .26, pushfour = .29,fully_up = .85,ready = 0.605,hookclip = 0.59,outtheway = .5, down = .04, almost_down = .19, target, downencoderpos = 340, almostdownencoderpos = 281,
    readyencoderpos = 146.9,outthewayencoderpos = 170,hookclipencoderpos = 151,clippity_clappity_clickity_clickencoderpos = 67;
    public static boolean cliparmdone = false;
    AnalogInput clipAnalog;

    @Override
    public void initAuto(HardwareMap hwMap) {
        rightindex = hwMap.get(Servo.class,"rightindex");
        leftindex = hwMap.get(Servo.class,"leftindex");
        rightmagarm = hwMap.get(Servo.class,"rightmagarm");
        leftmagarm = hwMap.get(Servo.class,"leftmagarm");
        leftmagarm.setDirection(Servo.Direction.REVERSE);
        rightmagarm.setDirection(Servo.Direction.REVERSE);
        clipAnalog = hwMap.get(AnalogInput.class, "clipanalog");
        cliparmdone = false;
    }

    @Override
    public void periodic() {
        Dashboard.addData("clipmagpos",getClipMagPos());
    }

    public double getClipMagPos(){
        return clipAnalog.getVoltage() / 3.3 * 360;
    }

    public double getClipMagError(){
        return Math.abs(target - getClipMagPos());
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
            case CLIPPITY_CLAPPITY_CLICKITY_CLICK: //51
                target = clippity_clappity_clickity_clickencoderpos;
                rightmagarm.setPosition(fully_up);
                leftmagarm.setPosition(fully_up);
                Dashboard.addData("cliparmdone",cliparmdone);
                if (getClipMagError() < 7){
                    cliparmdone = true;
                } else {
                    cliparmdone = false;
                }
                break;
            case READY:
                target = readyencoderpos;
                rightmagarm.setPosition(ready); //115
                leftmagarm.setPosition(ready);
                Dashboard.addData("cliparmdone",cliparmdone);
                if (getClipMagError() < 7){
                    cliparmdone = true;
                } else {
                    cliparmdone = false;
                }
                break;
            case HOOKCLIP:
                target = readyencoderpos;
                rightmagarm.setPosition(hookclip); //115
                leftmagarm.setPosition(hookclip);
                Dashboard.addData("cliparmdone",cliparmdone);
                if (getClipMagError() < 7){
                    cliparmdone = true;
                } else {
                    cliparmdone = false;
                }
                break;
            case EMPTY:
                Dashboard.addData("cliparmdone",cliparmdone);
                break;
            case OUT_THE_WAY:
                target = outthewayencoderpos;
                rightmagarm.setPosition(outtheway);
                leftmagarm.setPosition(outtheway); //179
                Dashboard.addData("cliparmdone",cliparmdone);
                if (getClipMagError() < 7){
                    cliparmdone = true;
                } else {
                    cliparmdone = false;
                }
                break;
            case ALMOST_DOWN:
                target = almostdownencoderpos;
                rightmagarm.setPosition(almost_down);//279
                leftmagarm.setPosition(almost_down);
                Dashboard.addData("cliparmdone",cliparmdone);
                if (getClipMagError() < 7){
                    cliparmdone = true;
                } else {
                    cliparmdone = false;
                }
                break;
            case SPECIALDOWN:
                target = downencoderpos;
                rightmagarm.setPosition(.1);
                leftmagarm.setPosition(.1);//359
                Dashboard.addData("cliparmdone",cliparmdone);
                if (getClipMagError() < 7){
                    cliparmdone = true;
                } else {
                    cliparmdone = false;
                }
                break;
            case DOWN:
                target = downencoderpos;
                rightmagarm.setPosition(down);
                leftmagarm.setPosition(down);//359
                Dashboard.addData("cliparmdone",cliparmdone);
                if (getClipMagError() < 7){
                    cliparmdone = true;
                } else {
                    cliparmdone = false;
                }
                break;
            case SHUTOFF:
                rightmagarm.setPosition(0);
                leftmagarm.setPosition(0);//359
                break;
        }
    }

    public void setLeftIndex(LeftIndexState leftIndexState){
        switch (leftIndexState){
            case PUSHONE:
                leftindex.setPosition(pushone);
                break;
            case PUSHTWO:
                leftindex.setPosition(pushtwo);
                break;
            case PUSHTHREE:
                leftindex.setPosition(pushthree);
                break;
            case PUSHFOUR:
                leftindex.setPosition(pushfour);
                break;
            case OUTOFTHEWAY:
                leftindex.setPosition(pushnone);
                break;
        }
    }

    public void setRightIndex(RightIndexState rightIndexState){
        switch (rightIndexState){
            case PUSHONE:
                rightindex.setPosition(pushone);
                break;
            case PUSHTWO:
                rightindex.setPosition(pushtwo);
                break;
            case PUSHTHREE:
                rightindex.setPosition(pushthree);
                break;
            case PUSHFOUR:
                rightindex.setPosition(pushfour);
                break;
            case OUTOFTHEWAY:
                rightindex.setPosition(pushnone);
                break;
        }
    }

    public enum ArmStates{
        CLIPPITY_CLAPPITY_CLICKITY_CLICK, // This states is for fully engaging the clips
        READY,
        HOOKCLIP,
        OUT_THE_WAY,
        ALMOST_DOWN,
        SPECIALDOWN,
        DOWN,
        SHUTOFF,
        EMPTY
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
