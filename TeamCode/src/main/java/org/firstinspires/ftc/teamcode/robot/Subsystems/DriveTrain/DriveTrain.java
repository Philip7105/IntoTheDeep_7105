package org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.NewRR.PinpointDrive;
import org.firstinspires.ftc.teamcode.robot.Input;


@Config
public class DriveTrain extends Subsystem {
    public PinpointDrive mecanumDrive;
   public static DriveSpeed driveSpeed = DriveSpeed.Fast;
   public static double headingoffset = 0;
   public static boolean engagePTO = false;
   public DriveTrain(HardwareMap hwMap){
       this.mecanumDrive = new PinpointDrive(hwMap,new Pose2d(new Vector2d(0,0),0));
   }

    @Override
    public void initAuto(HardwareMap hwMap) {
       engagePTO = false;
    }

    @Override
    public void periodic() {
    }

    @Override
    public void shutdown() {
        mecanumDrive.setDrivePowers(new PoseVelocity2d(new Vector2d(0,0),0));
    }

    public void RobotRelative(Input input){
       if (driveSpeed == DriveSpeed.Fast && !engagePTO) {
           mecanumDrive.setDrivePowers(new PoseVelocity2d(
                   new Vector2d(
                           -input.getLeft_stick_y(),
                           -input.getLeft_stick_x()
                   ),
                   -input.getRight_stick_x()
           ));
       }
       else if (engagePTO) {
           mecanumDrive.setDrivePowers(new PoseVelocity2d(
                   new Vector2d(
                           0,
                           -input.getLeft_stick_x() * .1
                   ),
                   0
           ));
       } else if (driveSpeed == DriveSpeed.Slow && !engagePTO) {
           mecanumDrive.setDrivePowers(new PoseVelocity2d(
                   new Vector2d(
                           -input.getLeft_stick_y() * .3,
                           -input.getLeft_stick_x() * .3
                   ),
                   -input.getRight_stick_x() *.3
           ));
           }else if (driveSpeed == DriveSpeed.MEDIUM && !engagePTO) {
           mecanumDrive.setDrivePowers(new PoseVelocity2d(
                   new Vector2d(
                           -input.getLeft_stick_y() * .6,
                           -input.getLeft_stick_x() * .6
                   ),
                   -input.getRight_stick_x() *.6
           ));
       }
   }

   public void moveDriveMotors(double speed){
       mecanumDrive.rightFront.setPower(speed);
       mecanumDrive.rightBack.setPower(speed);
       mecanumDrive.leftFront.setPower(speed);
       mecanumDrive.leftBack.setPower(speed);
   }

   public void changeDriveState(Input input){
       if (input.isLeft_trigger_press()){
           driveSpeed = DriveSpeed.Slow;
       } else {
           driveSpeed = DriveSpeed.Fast;
       }
   }

    public void setPoseEstimate(Vector2d vector2d, Rotation2d heading){
        mecanumDrive.pose = new Pose2d(vector2d,heading);
    }

    public void setPoseEstimateBetter(Vector2d vector2d, double heading){
        mecanumDrive.pose = new Pose2d(vector2d,heading);
    }

    public double getVelX(){
       return mecanumDrive.pinpoint.getVelX();
    }

    public double getVelY(){
        return mecanumDrive.pinpoint.getVelY();
    }

    public Pose2d getPoseEstimate(){
       return mecanumDrive.pose;
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

    public double getHeadingFixed(){
        return Math.toDegrees(getHeading().toDouble()) + headingoffset;
    }

    public Action strafeToLinearHeading(Vector2d startVec,double startHeading, Vector2d targetVec, double targetHeading) {
        return mecanumDrive.actionBuilderBetter(new Pose2d(startVec, startHeading))
                .strafeToLinearHeading(targetVec, targetHeading)
                .build();
    }

    public Action getTheSamples(Vector2d startVec, double startHeading) {
        return mecanumDrive.actionBuilderBetter(new Pose2d(startVec, startHeading))
                .strafeToLinearHeading(new Vector2d(-35,38),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-35,10), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-49, 26), Math.toRadians(90))
//                .splineToConstantHeading(new Vector2d(-45, 10), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-49, 53), Math.toRadians(90))
                // push in the first blue sample
//                .strafeToLinearHeading(new Vector2d(-45, 10), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(-50, 10), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-57, 25), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-57, 53), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-61, 10), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(-62.5, 53), Math.toRadians(90))
                .build();
    }

    public Action strafeToLinearHeadingWithMarker(Vector2d targetVec, Rotation2d targetHeading,double markerX,Action[] actions) {
        return mecanumDrive.actionBuilder(new Pose2d(new Vector2d(getXPos(), getYPos()), getHeadingFixed()))
                .strafeToLinearHeading(targetVec, targetHeading)
//                .afterDisp(markerX,(actions))
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
        Slow,
        MEDIUM
    }
}
