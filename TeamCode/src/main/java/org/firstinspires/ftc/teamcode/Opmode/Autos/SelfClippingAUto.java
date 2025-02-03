package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highchamber;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Autonomous
public class SelfClippingAUto extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(-9.5, 63), Math.toRadians(90));
        runpath = new MultipleRRActionsWithPathing(new Action[]{groups.delayAction(.2, groups.moveVerticalSlidesAction(highchamber, greaterTolerance)), robot.driveTrain.strafeToLinearHeading(new Vector2d(-9.5, 63), Math.toRadians(90), new Vector2d(-7, 31), Math.toRadians(90)), groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAY)})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance), groups.delayAction(.6, new ParallelAction(groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)
                        , groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.OUTTAKE)))}))
                // clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-7, 31), Math.toRadians(90), new Vector2d(-47, 45), Math.toRadians(90))
                        , groups.moveClipMagAction(ClipMech.ArmStates.SPECIALDOWN)
                        , groups.moveVerticalSlidesAction(0, greaterTolerance), groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP), groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT, halfOutEncoderPos), groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47, 45), Math.toRadians(90), new Vector2d(-47, 48.5), Math.toRadians(90)),
                        groups.delayAction(.3, groups.movePivotAction(JohnsIntake.PivotStates.SLIGHTLY_LOWER_PICKUP)),
                        groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.INTAKE)}))
                //go to get the human players clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47, 48.5), Math.toRadians(90), new Vector2d(-2.5, 32), Math.toRadians(90)),
                        groups.delayAction(.12, groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP)),
                        groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.STOP),
                        groups.movePivotAction(JohnsIntake.PivotStates.CHAMBERPOS),
//                        groups.delayActionTimesTwo(.4,groups.movePivotAction(JohnsIntake.PivotStates.CHAMBERPOS)),
                        groups.moveVerticalSlidesAction(highchamber, greaterTolerance),
                        groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN, fullyInEncoderPos)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance), groups.delayAction(.6, new ParallelAction(groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)
                        , groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.OUTTAKE)))}))
                // go to clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-2.5, 32), Math.toRadians(90), new Vector2d(-44.5, 56), Math.toRadians(90))
                        , groups.moveVerticalSlidesAction(0, greaterTolerance)
                        , groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAY),
                        groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),
                        groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.STOP)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveClipMagAction(ClipMech.ArmStates.ALMOST_DOWN)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-44.5, 56), Math.toRadians(90), new Vector2d(-44.5, 54.4), Math.toRadians(90))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveClipMagAction(ClipMech.ArmStates.DOWN)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAY)}));
    }
}

