package org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DelayActionTimesTwo implements Action {
    ElapsedTime time = new ElapsedTime();
    public static boolean runOnceDelayTwo = false;
    double settime;
    TelemetryPacket packet;
    public DelayActionTimesTwo(double time, TelemetryPacket telemetryPacket){
        this.settime = time;
        this.packet = telemetryPacket;
    }

    public void init(){
        if (!runOnceDelayTwo){
            runOnceDelayTwo = true;
            time.reset();
        }
    }

    @Override
    public boolean run(TelemetryPacket telemetryPacket) {
        telemetryPacket = packet;
        init();
        if (time.seconds() > settime){
            return false;
        }
        return true;
    }
}
