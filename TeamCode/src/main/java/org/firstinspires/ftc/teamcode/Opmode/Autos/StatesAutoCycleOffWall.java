package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highchamber;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

@Autonomous
public class StatesAutoCycleOffWall extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(-9.5, 63), Math.toRadians(90));
        runpath = new MultipleRRActionsWithPathing(new Action[]{groups.delayAction(.3, groups.moveVerticalSlidesAction(highchamber, greaterTolerance)), robot.driveTrain.strafeToLinearHeading(new Vector2d(-9.5, 63), Math.toRadians(90), new Vector2d(-4, 31), Math.toRadians(90))})
                .addNext(new Delay(.2))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance), groups.delayAction(.6
                        , groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE))}))
                // clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-4, 31), Math.toRadians(90), new Vector2d(-47, 45), Math.toRadians(90))
                        , groups.moveClipMagAction(ClipMech.ArmStates.ALMOST_DOWN)
                        , groups.moveVerticalSlidesAction(0, greaterTolerance), groups.movePivotAction(NewIntake.PivotStates.PARALLEL),
                        groups.moveIntakeAction(NewIntake.IntakeStates.STOP)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT, halfOutEncoderPos), groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47, 45), Math.toRadians(90), new Vector2d(-47, 48.5), Math.toRadians(90)),
                        groups.delayAction(.3, groups.movePivotAction(NewIntake.PivotStates.SLIGHTLY_LOWER_PICKUP)),
                        groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.INTAKE)}))
                //go to get the human players clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47, 48.5), Math.toRadians(90), new Vector2d(-2.5, 32), Math.toRadians(90)),
                        groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.STOP),
                        groups.movePivotAction(NewIntake.PivotStates.CHAMBERPOS),
//                        groups.delayActionTimesTwo(.4,groups.movePivotAction(JohnsIntake.PivotStates.CHAMBERPOS)),
                        groups.moveVerticalSlidesAction(highchamber, greaterTolerance),
                        groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN, fullyInEncoderPos)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance), groups.delayAction(.6
                        , groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE))}))
                // go to clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.getTheSamples(new Vector2d(-2.5, 32), Math.toRadians(90))
                        , groups.moveVerticalSlidesAction(0, greaterTolerance)
                        , groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES),
                        groups.movePivotAction(NewIntake.PivotStates.PARALLEL),
                        groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.STOP)}));
    }
}