package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.BetterSubsystems;
import org.firstinspires.ftc.teamcode.NewRR.Drawing;
import org.firstinspires.ftc.teamcode.NewRR.MecanumDrive;

public class Dashboard extends SubsystemBase {
        public static TelemetryPacket packet = new TelemetryPacket();
        FtcDashboard dash = FtcDashboard.getInstance();

        MecanumDrive drive;

        public Dashboard(MecanumDrive drive){
            this.drive = drive;
        }

        @Override
        public void register() {
            super.register();
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
