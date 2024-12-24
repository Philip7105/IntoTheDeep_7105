package org.firstinspires.ftc.teamcode.robot.Subsystems;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;

public class Dashboard extends Subsystem {
    public static TelemetryPacket packet = new TelemetryPacket();
    FtcDashboard dash = FtcDashboard.getInstance();

    @Override
    public void initAuto(HardwareMap hwMap) {
        dash.sendTelemetryPacket(packet);
        packet = new TelemetryPacket();
    }
    @Override
    public void periodic() {
        dash.sendTelemetryPacket(packet);
        packet = new TelemetryPacket();
    }
    @Override
    public void shutdown() {

    }
    public static void addData(String string, Object object){
        packet.put(string,object);
    }
    public static void addLine(String string){
        packet.addLine(string);
    }


}
