package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.crazyBigTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highchamber;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsConstraints;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

@Autonomous
public class StatesClipSideAuto extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(-9.5, 63), Math.toRadians(90));
        runpath = new MultipleRRActionsWithPathing(new Action[]{new SequentialAction(groups.moveVerticalSlidesAction(highchamber, 45)), robot.driveTrain.strafeToLinearHeading(new Vector2d(-9.5, 63), Math.toRadians(90), new Vector2d(-6, 32.8), Math.toRadians(90))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesActionVoltage(lowbasket, crazyBigTolerance)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{
                        robot.driveTrain.strafeToLinearHeading(new Vector2d(-6, 32.8), Math.toRadians(90), new Vector2d(-6, 34.5), Math.toRadians(90)),groups.moveVerticalSlidesAction(1100,greaterTolerance),groups.moveCoAxialAction(NewIntake.CoaxialStates.RELEASE),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-6, 34.5), Math.toRadians(90), new Vector2d(-35,34.5),Math.toRadians(90))
                        ,new SequentialAction(groups.moveVerticalSlidesAction(0, greaterTolerance),groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES)),groups.moveCoAxialAction(NewIntake.CoaxialStates.OFFTHEWALL)
                        ,groups.movePivotAction(NewIntake.PivotStates.HOOKCLIP),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.STOP)}))
                .addNext(new MultipleRRActionsConstraints(new Action[]{robot.driveTrain.getTheSamplesBetter(new Vector2d(-35,34.5),Math.toRadians(170))},18,3))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-57, 10), Math.toRadians(90),new Vector2d(-57, 53.7), Math.toRadians(90)),
                        new SequentialAction(groups.moveClipMagAction(ClipMech.ArmStates.ALMOST_DOWN),groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos))
                        , groups.moveIntakeAction(NewIntake.IntakeStates.INTAKE)
                        , groups.movePivotAction(NewIntake.PivotStates.PRECLIP2)}))
                .addNext(new Delay(.17))
                .addNext(groups.movePivot(NewIntake.PivotStates.OFFTHEWALL))
                .addNext(new MultipleCommand(groups.moveIntakeTime(NewIntake.IntakeStates.INTAKE,.6),groups.slidesSetPos(1100,greaterTolerance)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-57, 53.7), Math.toRadians(115), new Vector2d(0,32.4),Math.toRadians(90)),
                        groups.moveClipMagAction(ClipMech.ArmStates.READY),groups.delayAction(.45,groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos))
                        ,new SequentialAction(new ParallelAction(groups.moveCoAxialAction(NewIntake.CoaxialStates.CLAMPPOSOUTBACK),groups.movePivotAction(NewIntake.PivotStates.AUTOPOS)),groups.movePivotAction(NewIntake.PivotStates.CHAMBERPOSBOTH)),groups.moveIntakeActionTime(NewIntake.IntakeStates.INTAKE,.8)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesActionVoltage(lowbasket, crazyBigTolerance)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(1100,greaterTolerance),groups.moveCoAxialAction(NewIntake.CoaxialStates.RELEASE),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(0,32.4), Math.toRadians(90), new Vector2d(-40,54),Math.toRadians(90)),
                        new SequentialAction(groups.moveVerticalSlidesAction(0,greaterTolerance),
                                groups.moveClipMagAction(ClipMech.ArmStates.ALMOST_DOWN),
                                groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos))
                        ,groups.movePivotAction(NewIntake.PivotStates.PRECLIP2)
                        ,groups.moveIntakeAction(NewIntake.IntakeStates.INTAKE),
                        groups.moveCoAxialAction(NewIntake.CoaxialStates.OFFTHEWALL)}))
                .addNext(new Delay(.17))
                .addNext(groups.movePivot(NewIntake.PivotStates.OFFTHEWALL))
                        .addNext(new MultipleCommand(groups.moveIntakeTime(NewIntake.IntakeStates.INTAKE,.6),groups.slidesSetPos(1100,greaterTolerance)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-40,54), Math.toRadians(90), new Vector2d(-2,31.7),Math.toRadians(90)),
                        groups.moveClipMagAction(ClipMech.ArmStates.READY),groups.delayAction(.45,groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos))
                        ,new SequentialAction(new ParallelAction(groups.moveCoAxialAction(NewIntake.CoaxialStates.CLAMPPOSOUTBACK),groups.movePivotAction(NewIntake.PivotStates.AUTOPOS)),groups.movePivotAction(NewIntake.PivotStates.CHAMBERPOSBOTH)),groups.moveIntakeActionTime(NewIntake.IntakeStates.INTAKE,.8)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesActionVoltage(lowbasket, crazyBigTolerance)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(1100,greaterTolerance),groups.moveCoAxialAction(NewIntake.CoaxialStates.RELEASE),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE)}))
        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-2,31.7), Math.toRadians(90), new Vector2d(-40,54.5),Math.toRadians(90)),
                new SequentialAction(groups.moveVerticalSlidesAction(0,greaterTolerance),
                        groups.moveClipMagAction(ClipMech.ArmStates.ALMOST_DOWN),
                        groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos))
                ,groups.movePivotAction(NewIntake.PivotStates.PRECLIP2)
                ,groups.moveIntakeAction(NewIntake.IntakeStates.INTAKE),
                groups.moveCoAxialAction(NewIntake.CoaxialStates.OFFTHEWALL)}))
                .addNext(new Delay(.17))
                .addNext(groups.movePivot(NewIntake.PivotStates.OFFTHEWALL))
                .addNext(new MultipleCommand(groups.moveIntakeTime(NewIntake.IntakeStates.INTAKE,.6),groups.slidesSetPos(1100,greaterTolerance)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-40,54.5), Math.toRadians(90), new Vector2d(-2,31.5),Math.toRadians(90)),
                        groups.moveClipMagAction(ClipMech.ArmStates.READY),groups.delayAction(.45,groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos))
                        ,new SequentialAction(new ParallelAction(groups.moveCoAxialAction(NewIntake.CoaxialStates.CLAMPPOSOUTBACK),groups.movePivotAction(NewIntake.PivotStates.AUTOPOS)),groups.movePivotAction(NewIntake.PivotStates.CHAMBERPOSBOTH)),groups.moveIntakeActionTime(NewIntake.IntakeStates.INTAKE,.8)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesActionVoltage(lowbasket, crazyBigTolerance)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(1100,greaterTolerance),groups.moveCoAxialAction(NewIntake.CoaxialStates.RELEASE),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE)}));
    }
}

