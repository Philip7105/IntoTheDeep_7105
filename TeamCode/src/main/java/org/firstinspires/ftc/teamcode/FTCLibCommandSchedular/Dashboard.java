package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Dashboard extends BetterSubsystems{

    public static TelemetryPacket packet = new TelemetryPacket();
    FtcDashboard dash = FtcDashboard.getInstance();
    //    public static Telemetry telemetry;
//    static OpMode opMode;
    ElapsedTime time = new ElapsedTime();

    @Override
    public void init() {
        dash.sendTelemetryPacket(packet);
        packet = new TelemetryPacket();
    }

    @Override
    public void periodic() {
        dash.sendTelemetryPacket(packet);
        packet = new TelemetryPacket();
        addData("Loop Time",time.seconds());
        time.reset();
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
