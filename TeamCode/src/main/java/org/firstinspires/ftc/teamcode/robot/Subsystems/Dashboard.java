package org.firstinspires.ftc.teamcode.robot.Subsystems;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.NewRR.Drawing;
import org.firstinspires.ftc.teamcode.NewRR.MecanumDrive;

public class Dashboard extends Subsystem {
    public static TelemetryPacket packet = new TelemetryPacket();
    FtcDashboard dash = FtcDashboard.getInstance();

    MecanumDrive drive;

    public Dashboard(MecanumDrive drive){
        this.drive = drive;
    }

    @Override
    public void initAuto(HardwareMap hwMap) {
        drawRobot();
        dash.sendTelemetryPacket(packet);
        packet = new TelemetryPacket();
    }
    @Override
    public void periodic() {
        drawRobot();
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
    public void drawRobot(){
        packet.fieldOverlay().setStroke("#3F51B5");
        Drawing.drawRobot(packet.fieldOverlay(), drive.pose);
    }
}
