package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.autoposencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;
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
public class PushSamplesAuto extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(-9.5, 63), Math.toRadians(90));
        runpath = new MultipleRRActionsWithPathing(new Action[]{new SequentialAction(groups.moveVerticalSlidesAction(highchamber, 45)), robot.driveTrain.strafeToLinearHeading(new Vector2d(-9.5, 63), Math.toRadians(90), new Vector2d(-6, 32.4), Math.toRadians(90))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(1100,greaterTolerance),groups.moveCoAxialAction(NewIntake.CoaxialStates.RELEASE),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-6, 32.4), Math.toRadians(90), new Vector2d(-35,38),Math.toRadians(90))
                        ,new SequentialAction(groups.moveVerticalSlidesAction(0, greaterTolerance),groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES)),groups.moveCoAxialAction(NewIntake.CoaxialStates.OFFTHEWALL)
                        ,groups.movePivotAction(NewIntake.PivotStates.OFFTHEWALL),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.STOP)}))
                .addNext(new MultipleRRActionsConstraints(new Action[]{robot.driveTrain.getTheSamplesBetter(new Vector2d(-35,38),Math.toRadians(90))},15,3))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-66, 10), Math.toRadians(90),new Vector2d(-66, 50.5), Math.toRadians(90)),
                        new SequentialAction(groups.moveClipMagAction(ClipMech.ArmStates.ALMOST_DOWN),groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos))}))
                .addNext(new MultipleCommand(groups.moveIntakeTime(NewIntake.IntakeStates.INTAKE,1),groups.slidesSetPos(1100,greaterTolerance)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-66, 50.5), Math.toRadians(90), new Vector2d(0,32.4),Math.toRadians(90)),
                        groups.moveClipMagAction(ClipMech.ArmStates.READY),groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos)
                        ,groups.movePivotAction(NewIntake.PivotStates.CHAMBERPOSBOTH),groups.moveIntakeActionTime(NewIntake.IntakeStates.INTAKE,.8)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(1100,greaterTolerance),groups.moveCoAxialAction(NewIntake.CoaxialStates.RELEASE),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.OUTTAKE)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(0,32.4), Math.toRadians(90), new Vector2d(-40,50.5),Math.toRadians(90)),
                        new SequentialAction(groups.moveClipMagAction(ClipMech.ArmStates.ALMOST_DOWN),
                                groups.moveVerticalSlidesAction(0,greaterTolerance),
                                groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.AUTOPOS,autoposencoderpos))
                        ,groups.movePivotAction(NewIntake.PivotStates.OFFTHEWALL),
                        groups.moveCoAxialAction(NewIntake.CoaxialStates.OFFTHEWALL),
                        groups.moveIntakeAction(NewIntake.IntakeStates.STOP)}));

    }
}

