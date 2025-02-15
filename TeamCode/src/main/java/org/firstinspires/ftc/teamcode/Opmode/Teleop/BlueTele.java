package org.firstinspires.ftc.teamcode.Opmode.Teleop;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.TurnAutoSelfClipOnAndOff;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;

import java.util.List;
@TeleOp(name = "\uD83D\uDFE6 \uD83D\uDE08")
public class BlueTele extends LinearOpMode {
    protected Robot robot;
    protected ScoringCommandGroups groups;

    @Override
    public void runOpMode() {
        robot =new Robot(hardwareMap, Robot.OpMode.Teleop, gamepad1, gamepad2);
        groups =new ScoringCommandGroups(robot.intake, robot.verticalslides,robot.horizontalslides, robot.clipmech,robot.hang);
        double oldTime = 0;
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        waitForStart();
        while (opModeIsActive()){
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            }
            double newTime = getRuntime();
            double loopTime = newTime-oldTime;
            double frequency = 1/loopTime;
            oldTime = newTime;
            Dashboard.addData("Loop Time",frequency);
            robot.intake.intakeTeleBlue(robot.gamepad1,robot.gamepad2,robot,groups);
            robot.verticalslides.updatePos(robot.gamepad2,robot,groups);
            robot.clipmech.setLeftIndex(robot.gamepad2);
            robot.clipmech.setRightIndex(robot.gamepad2);

            robot.gamepad2.whenPSButtonPressed(new TurnAutoSelfClipOnAndOff());
            robot.gamepad1.whenCrossPressed(groups.bringInHorizontalSLidesBetter());
            robot.gamepad1.whenSquarePressed(groups.extendHorizontalSLides());
            robot.gamepad1.whenTrianglePressed(groups.fullExtendHorizontalSLides());
            robot.hang.manualHang(robot.gamepad2);;
            robot.gamepad2.whenDPadLeftPressed(groups.moveClipMag(ClipMech.ArmStates.ALMOST_DOWN));
            robot.gamepad2.whenDPadDownPressed(groups.moveClipMag(ClipMech.ArmStates.DOWN));
            robot.gamepad2.whenDPadRightPressed(groups.moveClipMag(ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES));

            robot.gamepad1.whenDPadUpPressed(groups.armChamberPos());
            robot.gamepad1.whenDPadDownPressed(groups.armOutFront());
            robot.driveTrain.RobotRelative(robot.gamepad1);
            robot.updateTele();
        }
    }
}
