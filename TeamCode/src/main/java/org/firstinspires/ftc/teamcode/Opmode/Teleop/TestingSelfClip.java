package org.firstinspires.ftc.teamcode.Opmode.Teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.ScoringCommandGroups;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveClipMech;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

import java.util.List;

@Config
@TeleOp
public class TestingSelfClip extends LinearOpMode {
    protected Robot robot;
    protected ScoringCommandGroups groups;
    public static boolean runClipSequence = false;
    @Override
    public void runOpMode() {
        robot =new Robot(hardwareMap, Robot.OpMode.Teleop, gamepad1, gamepad2);
        groups =new ScoringCommandGroups(robot.intake, robot.verticalslides,robot.horizontalslides, robot.clipmech,robot.hang);
        double oldTime = 0;
        List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
        init_loop();
//        robot.getScheduler().forceCommand(

        waitForStart();
//        robot.getScheduler().forceCommand(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT, HorizontalSlides.halfOutEncoderPos),
//                groups.moveGripper(JohnsIntake.GripperStates.CLAMP), groups.movePivot(JohnsIntake.PivotStates.PRECLIP)).addNext(groups.clipClip()));
        while (opModeIsActive()){
            for (LynxModule hub : allHubs) {
                hub.clearBulkCache();
            }
            double newTime = getRuntime();
            double loopTime = newTime-oldTime;
            double frequency = 1/loopTime;
            oldTime = newTime;
            Dashboard.addData("Loop Time",frequency);
            robot.gamepad1.whenDPadLeftPressed(groups.clipClip());
            robot.gamepad1.whenDPadRightPressed(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT, HorizontalSlides.halfOutEncoderPos),
                    groups.moveGripper(JohnsIntake.GripperStates.CLAMP),groups.moveClipMag(ClipMech.ArmStates.READY), groups.movePivot(JohnsIntake.PivotStates.PRECLIP)));
//            if (runClipSequence) {

//            }else {
//                robot.getScheduler().forceCommand(
//                        new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT, HorizontalSlides.halfOutEncoderPos),
//                                groups.moveGripper(JohnsIntake.GripperStates.CLAMP), groups.moveClipMag(ClipMech.ArmStates.READY), groups.movePivot(JohnsIntake.PivotStates.PRECLIP)));
//            }
//            robot.intake.intakeTele(robot.gamepad1, robot.horizontalslides,robot.gamepad2);
//            robot.driveTrain.changeDriveState(robot.gamepad1);
//            robot.verticalslides.updatePos(robot.gamepad2,robot,groups);

//            robot.gamepad1.whenCrossPressed(groups.bringInHorizontalSLidesBetter());
//            robot.gamepad1.whenSquarePressed(groups.extendHorizontalSLides());
//            robot.gamepad1.whenTrianglePressed(groups.fullExtendHorizontalSLides());
//            robot.gamepad1.whenTrianglePressed(groups.movePivot(JohnsIntake.PivotStates.PARALLEL));
//            robot.gamepad1.whenCirclePressed(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_IN,HorizontalSlides.fullyInEncoderPos));
//            robot.gamepad1.whenSquarePressed(groups.clipClip());
//            robot.hang.manualHang(robot.gamepad2);

//            robot.gamepad2.whenRightTriggerPressed(groups.moveHang(JohnHanging.LeftHangStates.READYFIRSTHANG, JohnHanging.LeftHangStates.ZERO_POWER,JohnHanging.RightHangStates.READYFIRSTHANG, JohnHanging.RightHangStates.ZERO_POWER,readyFirstHang));
//            robot.gamepad2.whenLeftTriggerPressed(groups.moveHang(JohnHanging.LeftHangStates.FIRSTHANG, JohnHanging.LeftHangStates.HOLDPOS,JohnHanging.RightHangStates.FIRSTHANG, JohnHanging.RightHangStates.HOLDPOS,firstHang));

//            robot.gamepad1.whenSquarePressed(new MoveHorizontalSlides(robot.horizontalslides, HorizontalSlides.HorizontalSlideStates.Fully_In));
//            robot.gamepad1.whenCrossPressed(groups.moveArmJohn(JohnsIntake.PivotStates.parallel));

//            robot.gamepad2.whenDPadUpPressed(new MoveClipMech(robot.clipmech, ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK));
//            robot.gamepad2.whenDPadLeftPressed(new MoveClipMech(robot.clipmech, ClipMech.ArmStates.ALMOST_DOWN));
//            robot.gamepad2.whenDPadRightPressed(new MoveClipMech(robot.clipmech, ClipMech.ArmStates.READY));
//            robot.gamepad2.whenDPadDownPressed(new MoveClipMech(robot.clipmech, ClipMech.ArmStates.DOWN));
//            robot.gamepad2.whenRightStickButtonPressed(new MoveClipMech(robot.clipmech, ClipMech.ArmStates.OUT_THE_WAY));

//            robot.gamepad1.whenDPadUpPressed(groups.armChamberPos());
//            robot.gamepad1.whenDPadRightPressed(groups.armBasketPos());
//            robot.gamepad1.whenDPadDownPressed(groups.armOutFront());

//            robot.gamepad1.whenDPadRightPressed(groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP));
//            robot.gamepad1.whenDPadLeftPressed(groups.moveGripper(JohnsIntake.GripperStates.CLAMP));

//            robot.driveTrain.RobotRelative(robot.gamepad1);
            robot.updateTele();
        }
    }
}
