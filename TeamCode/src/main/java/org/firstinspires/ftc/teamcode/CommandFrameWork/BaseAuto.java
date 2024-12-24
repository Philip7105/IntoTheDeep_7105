package org.firstinspires.ftc.teamcode.CommandFrameWork;

//import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands.StrafetoLinearHeading;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

import java.util.List;

public abstract class BaseAuto extends LinearOpMode {
    protected Robot robot;
    public ScoringCommandGroups groups;
//    MoveVerticalSlidesMultiThread moveSlides;
    Command runpath;
    @Override
    public void runOpMode() throws InterruptedException {

        setRobot();

        groups = new ScoringCommandGroups(robot.intake, robot.verticalslides, robot.horizontalslides,robot.clipmech, robot.hang, this);
        robot.getScheduler().forceCommand(groups.initRobot());

        double oldTime = 0;
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        while (opModeInInit()){
            robot.driveTrain.setPoseEstimate(new Vector2d(-15.8,59.7), Rotation2d.exp(Math.toRadians(90)));
            runpath = new MultipleCommand(new StrafetoLinearHeading(new Vector2d(-10,30),Rotation2d.exp(Math.toRadians(90)),robot)
                    .addNext(new StrafetoLinearHeading(new Vector2d(-40,40),Rotation2d.exp(Math.toRadians(90)),robot)));
        }

        waitForStart();
//        robot.getScheduler().forceCommand(runAuto(robot.getScheduler()));
        robot.getScheduler().forceCommand(runpath);
        while (opModeIsActive() && !isStopRequested()){
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            }
            double newTime = getRuntime();
            double loopTime = newTime-oldTime;
            double frequency = 1/loopTime;
            oldTime = newTime;
            Dashboard.addData("Loop Time",frequency);
            robot.update();
        }
        robot.shutdown();
    }
    public void setRobot(){
        robot = new Robot(hardwareMap, Robot.OpMode.Auto, gamepad1, gamepad2);
    }
}