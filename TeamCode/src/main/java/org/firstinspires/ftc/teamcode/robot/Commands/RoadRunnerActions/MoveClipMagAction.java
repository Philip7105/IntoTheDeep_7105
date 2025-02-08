package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.target;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveClipMagAction implements Action {
    TelemetryPacket packet;
    ClipMech clipMech;
    ClipMech.ArmStates armStates;
    public MoveClipMagAction(ClipMech clipMech, ClipMech.ArmStates armStates,TelemetryPacket telemetryPacket){
        this.packet = telemetryPacket;
        this.armStates = armStates;
        this.clipMech = clipMech;
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        clipMech.setArmStates(armStates);
        if (Math.abs(target - clipMech.getClipMagPos()) < 4){
            return false;
        }
        return true;
    }
}
