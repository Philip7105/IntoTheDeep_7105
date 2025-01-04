package org.firstinspires.ftc.teamcode.CommandFrameWork;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

import java.util.List;

public abstract class BaseAuto extends LinearOpMode {
    protected Robot robot;
    public ScoringCommandGroups groups;
//    MoveVerticalSlidesMultiThread moveSlides;
    public Command runpath;
    @Override
    public void runOpMode() throws InterruptedException {

        robot = new Robot(hardwareMap, Robot.OpMode.Auto, gamepad1, gamepad2);
//
        groups = new ScoringCommandGroups(robot.intake, robot.verticalslides, robot.horizontalslides,robot.clipmech, robot.hang);
        double oldTime = 0;
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
        robot.getScheduler().forceCommand(groups.initRobot());



        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        while (opModeInInit()){
            runAuto();
        }

        waitForStart();
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
            robot.updateTele();
        }
        robot.shutdown();
    }
    public void runAuto(){

    }
}