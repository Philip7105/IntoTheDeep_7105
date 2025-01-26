package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveClipMagAction implements Action {
    ElapsedTime time = new ElapsedTime();
    TelemetryPacket packet;
    public static boolean loopClipMagOnce = false;
    ClipMech clipMech;
    ClipMech.ArmStates armStates;
    public MoveClipMagAction(ClipMech clipMech, ClipMech.ArmStates armStates,TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
        this.armStates = armStates;
        this.clipMech = clipMech;
    }

    public void init() {
        if (!loopClipMagOnce){
            time.reset();
            loopClipMagOnce = true;
        }
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        init();
        clipMech.setArmStates(armStates);
        if (time.seconds() > 1){
            return false;
        }
        return true;
    }
}
