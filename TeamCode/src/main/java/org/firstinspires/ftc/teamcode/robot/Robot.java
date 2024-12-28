package org.firstinspires.ftc.teamcode.robot;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.CommandFrameWork.CommandScheduler;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.LimeLight;

/** sets up the framework for the robt
 */

public class Robot {
    public Dashboard dashboard;  // set up dashboard
    public Input gamepad1, gamepad2;  // set up gamepads
    public JohnsIntake intake;  // set up intake
    public ClipMech clipmech;
    public DriveTrain driveTrain;  // set up the drivetrain
    protected CommandScheduler scheduler;  // set up the command scheduler
    public LimeLight limelight;
    public VerticalSlides verticalslides;
    public HorizontalSlides horizontalslides;
    public JohnHanging hang;

    public Robot(HardwareMap hw, OpMode opMode, Gamepad gamepad1, Gamepad gamepad2) {
        // init robot
        driveTrain = new DriveTrain(hw);  // drivetrain
        dashboard= new Dashboard(driveTrain.mecanumDrive);
        clipmech = new ClipMech();
        hang = new JohnHanging();
        verticalslides = new VerticalSlides();
        horizontalslides = new HorizontalSlides();
        limelight = new LimeLight(driveTrain);
        intake= new JohnsIntake();  // intake
        scheduler = new CommandScheduler(hw,dashboard,intake,driveTrain,verticalslides,horizontalslides,limelight,clipmech,hang);  // set the scheduler up w/ all the subsystems.  MAKE SURE TO ADD NEW SUBSYSTEMS HERE
        this.gamepad1 = new Input(gamepad1,scheduler);
        this.gamepad2 = new Input(gamepad2,scheduler);
        // gamepads
        if (opMode.equals(OpMode.Auto)) {  // if auto init the auto
            scheduler.initAuto();
        } else if (opMode.equals(OpMode.Teleop)) {  // if tele init the tele
            scheduler.initTeleop();
        }
    }

    public void update() {  // update everything
        updateGamepads();  // update gamepads
        driveTrain.mecanumDrive.updatePoseEstimate();  // update mecanum drive
        scheduler.run();  // it says run auto but this really just updates the scheduler and runs everything there
    }

    public void updateTele() {  // update everything but tele specific
        updateGamepads();  // update gamepads
        scheduler.run(); // it says run auto but this really just updates the scheduler and runs everything there
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public CommandScheduler getScheduler() {
        return scheduler;
    }

    public void updateGamepads(){
        gamepad1.periodic();
        gamepad2.periodic();
    }

    public enum OpMode {
        Auto,
        Teleop
    }
}

