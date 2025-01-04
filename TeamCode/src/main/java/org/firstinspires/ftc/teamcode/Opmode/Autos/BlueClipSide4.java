package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.autoGreaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.autoTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.holdPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Config
@Autonomous
public class BlueClipSide4 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimate(new Vector2d(-14,63), Rotation2d.exp(Math.toRadians(90)));
        runpath = new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber,autoTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-1.5,32),Rotation2d.exp(Math.toRadians(90)))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket,autoGreaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.unclamp)),
                groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.outtake))}))
                // clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,47), Rotation2d.exp(Math.toRadians(90)))
                ,groups.moveVerticalSlidesAction(0,autoTolerance),groups.movePivotAction(JohnsIntake.PivotStates.parallel),
                        groups.moveGripperAction(JohnsIntake.GripperStates.clamp),groups.moveIntakeAction(JohnsIntake.IntakeStates.stop)}))
                .addNext(new Delay(2))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos),groups.moveGripper(JohnsIntake.GripperStates.unclamp)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,49), Rotation2d.exp(Math.toRadians(90))),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.intake),
                        groups.movePivotAction(JohnsIntake.PivotStates.slightly_lower_pickup)}))
                // intake another specimen
                .addNext(new Delay(4))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In, fullin),
                        groups.movePivot(JohnsIntake.PivotStates.parallel), groups.moveIntake(JohnsIntake.IntakeStates.stop)));
                //go to get the human players clip
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-2,33), Rotation2d.exp(Math.toRadians(90))),
//                        groups.moveHorizontalSlidesWithRR(HorizontalSlides.HorizontalSlideStates.Fully_In,fullyInEncoderPos)}));
                // go to clip
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-33,38), Rotation2d.exp(Math.toRadians(90)))
//                ,groups.moveVerticalSlidesWithRR(0,autoTolerance)}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-39,10), Rotation2d.exp(Math.toRadians(90)))}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, 10), Rotation2d.exp(Math.toRadians(180)))}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, 55), Rotation2d.exp(Math.toRadians(180)))}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-49, 10), Rotation2d.exp(Math.toRadians(180)))}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-52, 30), Rotation2d.exp(Math.toRadians(90)))}))
//                .addNext(new Delay(2));









//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-39,10),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-45,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-45,55),Rotation2d.exp(Math.toRadians(180)))))
                // push in the first blue sample
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-45,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,55),Rotation2d.exp(Math.toRadians(180)))))
                // push in the second blue sample
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-61,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-61,55),Rotation2d.exp(Math.toRadians(180)))))
                // push in the final blue sample
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,30),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)))))
                // pick up a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-4,33),Rotation2d.exp(Math.toRadians(90)))))
                // place a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,40),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)))))
                // pick up a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-2,33),Rotation2d.exp(Math.toRadians(90)))))
                // place a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,40),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)))))
                // pick up a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(0,33),Rotation2d.exp(Math.toRadians(90)))))
                // place a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,55),Rotation2d.exp(Math.toRadians(90)))));
                // park

    }
}