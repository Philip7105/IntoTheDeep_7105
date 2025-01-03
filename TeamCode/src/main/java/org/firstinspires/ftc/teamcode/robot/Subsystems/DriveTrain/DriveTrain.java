package org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BetterParallelAction;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.NewRR.PinpointDrive;
import org.firstinspires.ftc.teamcode.robot.Input;


@Config
public class DriveTrain extends Subsystem {
    public PinpointDrive mecanumDrive;
   public static DriveSpeed driveSpeed = DriveSpeed.Fast;
   public DriveTrain(HardwareMap hwMap){
       this.mecanumDrive = new PinpointDrive(hwMap,new Pose2d(new Vector2d(0,0),0));
   }

    @Override
    public void initAuto(HardwareMap hwMap) {
    }

    @Override
    public void periodic() {
    }

    @Override
    public void shutdown() {
        mecanumDrive.setDrivePowers(new PoseVelocity2d(new Vector2d(0,0),0));
    }

    public void RobotRelative(Input input){
       if (driveSpeed == DriveSpeed.Fast) {
           mecanumDrive.setDrivePowers(new PoseVelocity2d(
                   new Vector2d(
                           -input.getLeft_stick_y(),
                           -input.getLeft_stick_x()
                   ),
                   -input.getRight_stick_x()
           ));
       } else if (driveSpeed == DriveSpeed.Slow) {
           mecanumDrive.setDrivePowers(new PoseVelocity2d(
                   new Vector2d(
                           -input.getLeft_stick_y() * .3,
                           -input.getLeft_stick_x() * .3
                   ),
                   -input.getRight_stick_x() *.3
           ));
           }
       }

    public void setPoseEstimate(Vector2d vector2d, Rotation2d heading){
        mecanumDrive.pose = new Pose2d(vector2d,heading);
    }

    public double getXPos(){
        return mecanumDrive.pose.position.x;
    }

    public double getYPos(){
        return mecanumDrive.pose.position.y;
    }

    public Rotation2d getHeading(){
        return mecanumDrive.pose.heading;
    }

    public double getHeadingDouble(){
        return getHeading().toDouble();
    }

    public Action strafeToLinearHeading(Vector2d targetVec, Rotation2d targetHeading) {
        return mecanumDrive.actionBuilder(new Pose2d(new Vector2d(getXPos(), getYPos()), getHeading()))
                .strafeToLinearHeading(targetVec, targetHeading)
                .build();
    }

    public Action strafeToLinearHeadingWithMarker(Vector2d targetVec, Rotation2d targetHeading,double markerX,Action[] actions) {
        return mecanumDrive.actionBuilder(new Pose2d(new Vector2d(getXPos(), getYPos()), getHeading()))
                .strafeToLinearHeading(targetVec, targetHeading)
                .afterDisp(markerX,(actions))
                .build();
    }

    public void turn(Vector2d startVec, Rotation2d startHeading,Rotation2d angle) {
        Actions.runBlocking(mecanumDrive.actionBuilder(new Pose2d(startVec,startHeading))
                .turnTo(angle)
                .build());
    }


    public void update(){
       mecanumDrive.updatePoseEstimate();
    }

    public enum DriveSpeed{
        Fast,
        Slow
    }
}
