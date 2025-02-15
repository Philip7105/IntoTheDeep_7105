package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.almostdownencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.backbeforeclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.clippity_clappity_clickity_clickencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.downencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.hookclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.outthewayencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.preclipencoderpos2;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.readyencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.target;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveClipMagAction implements Action {
    TelemetryPacket packet;
    ClipMech clipMech;
    ClipMech.ArmStates armStates;
    public static boolean initSelfClipper = true;
    public MoveClipMagAction(ClipMech clipMech, ClipMech.ArmStates armStates,TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
        this.armStates = armStates;
        this.clipMech = clipMech;
        initSelfClipper = true;
    }

    public void init(){
        if (initSelfClipper) {
            if (armStates == ClipMech.ArmStates.DOWN) {
                target = downencoderpos;
            } else if (armStates == ClipMech.ArmStates.ALMOST_DOWN) {
                target = almostdownencoderpos;
            } else if (armStates == ClipMech.ArmStates.READY) {
                target = readyencoderpos;
            } else if (armStates == ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES) {
                target = outthewayencoderpos;
            } else if (armStates == ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK) {
                target = clippity_clappity_clickity_clickencoderpos;
            }  else if (armStates == ClipMech.ArmStates.HOOKCLIP) {
                target = hookclipencoderpos;
            } else if (armStates == ClipMech.ArmStates.BACKBEFORECLIP) {
                target = backbeforeclipencoderpos;
            } else if (armStates == ClipMech.ArmStates.PRECLIP2) {
                target = 116;
            } else if (armStates == ClipMech.ArmStates.PRECLIP3) {
                target = 160;
            }
            initSelfClipper = false;
        }
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        init();
        clipMech.setArmStates(armStates);
        if (Math.abs(target - clipMech.getClipMagPos()) < 4){
            initSelfClipper = true;
            return false;
        }
        return true;
    }
}
