//package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.arcrobotics.ftclib.command.CommandOpMode;
//import com.arcrobotics.ftclib.command.CommandScheduler;
//import com.arcrobotics.ftclib.gamepad.GamepadEx;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//@Disabled
//@Config
//@TeleOp(name = "Solo")
//public class Tele extends CommandOpMode {
//
////    private final RobotHardware robot = RobotHardware.getInstance();
//    private GamepadEx gamepadEx;
//    private GamepadEx gamepadEx2;
//
//    private double loopTime = 0.0;
//    private boolean lastJoystickUp = false;
//    private boolean lastJoystickDown = false;
//
//    private boolean extendIntake = true;
//
//    @Override
//    public void initialize() {
//        CommandScheduler.getInstance().reset();
//
//        gamepadEx = new GamepadEx(gamepad1);
//        gamepadEx2 = new GamepadEx(gamepad2);
//
//
////        robot.read();
//        while (opModeInInit()) {
//            telemetry.addLine("Robot Initialized.");
//            telemetry.update();
//        }
//    }
//
//    @Override
//    public void run() {
//        CommandScheduler.getInstance().run();
////        robot.clearBulkCache();
////        robot.read();
////        robot.periodic();
////        robot.write();
//
//        // G1 - Drivetrain Control
//
//        boolean currentJoystickUp = gamepad1.right_stick_y < -0.5;
//        boolean currentJoystickDown = gamepad1.right_stick_y > 0.5;
//
//
////        lastJoystickUp = currentJoystickUp;
////        lastJoystickDown = currentJoystickDown;
//
////        if (gamepad1.dpad_up && gamepad1.y) robot.drone.updateState(DroneSubsystem.DroneState.FIRED);
//
//        double loop = System.nanoTime();
//        telemetry.addData("hz ", 1000000000 / (loop - loopTime));
////        telemetry.addData("height", robot.extension.getBackdropHeight());
//        loopTime = loop;
//        telemetry.update();
//    }
//
//
//}