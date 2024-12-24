package org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.TimeTrajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Subsystem;
import org.firstinspires.ftc.teamcode.NewRR.MecanumDrive;
import org.firstinspires.ftc.teamcode.NewRR.PinpointDrive;
import org.firstinspires.ftc.teamcode.robot.Input;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;


@Config
public class DriveTrain extends Subsystem {
//   public SampleMecanumDrive mecanumDrive;
   public static double headingP = 1,xyP = 1;

    public PinpointDrive mecanumDrive;

    public static boolean usingThread = true;

   DriveSpeed driveSpeed = DriveSpeed.Fast;

   MecanumDrive.FollowTrajectoryAction trajectoryAction;
   MecanumDrive.TurnAction turnAction;
    TrajectoryActionBuilder actionBuilder;

   public DriveTrain(HardwareMap hwMap){
       this.mecanumDrive = new PinpointDrive(hwMap,new Pose2d(new Vector2d(0,0),0));
   }

    @Override
    public void initAuto(HardwareMap hwMap) {
//        imu = hwMap.get(IMU.class,"imu");
//        this.mecanumDrive = new PinPoint_MecanumDrive(hwMap);
    }

    @Override
    public void periodic() {
//        Dashboard.addData("imu",getHeading(0));
//        mecanumDrive.update();
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
    public void setStates(VerticalSlides verticalSlides){
//       if (input.isCrossPressed() && slow){
//           slow = false;
//       } else if (input.isCrossPressed() && !slow) {
//           slow = true;
//       }
    }

    public void setPoseEstimate(Vector2d vector2d, Rotation2d heading){
        mecanumDrive.pose = new com.acmerobotics.roadrunner.Pose2d(vector2d,heading);
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

    public void setStatesJohn(Input input){
       if (input.isLeft_bumper()) {
            driveSpeed = DriveSpeed.Slow;
        } else if (!input.isLeft_bumper()) {
            driveSpeed = DriveSpeed.Fast;
        }
    }

    public void followTrajectory(TimeTrajectory traj){

    }


//    public void followTrajectorySequenceAsync(TrajectorySequence trajectory){
//        mecanumDrive.followTrajectorySequenceAsync(trajectory);
//    }


//    public void followTrajectory(Trajectory trajectory){
//        mecanumDrive.followTrajectory(trajectory);
//    }

    public void splineTo(double startX, double startY, double startHeading, double targetX, double targetY, double targetHeading) {
       Actions.runBlocking(mecanumDrive.actionBuilder
                       (new com.acmerobotics.roadrunner.Pose2d(startX,startY,startHeading))
                        .splineTo(new Vector2d(targetX, targetY), targetHeading)
                        .build());
    }

    public void strafeToLinearHeading(Vector2d startVec, Rotation2d startHeading, Vector2d targetVec, Rotation2d targetHeading) {
        Actions.runBlocking(
                mecanumDrive.actionBuilder(new com.acmerobotics.roadrunner.Pose2d(startVec,startHeading))
                        .strafeToLinearHeading(targetVec,targetHeading)
                        .build());
    }

    public void update(){
       mecanumDrive.updatePoseEstimate();
    }
//    public void lockPosition (Pose2d target){
//       Pose2d difference = target.minus(poseEstimate());
//       Vector2d xy = difference.vec().rotated(-poseEstimate().getHeading());
//
//       double heading = Angle.normDelta(target.getHeading()) - Angle.normDelta(poseEstimate().getHeading());
//       mecanumDrive.setWeightedDrivePower(new Pose2d(xy.times(xyP),heading * headingP));
//    }


    public enum DriveSpeed{
       Fast,
        Slow
    }
}
