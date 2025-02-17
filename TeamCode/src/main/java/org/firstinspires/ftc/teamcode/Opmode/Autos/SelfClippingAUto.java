package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.autoposencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
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
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

@Autonomous
public class SelfClippingAUto extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(-9.5, 63), Math.toRadians(90));
        runpath = new MultipleRRActionsWithPathing(new Action[]{new SequentialAction(groups.moveClipMagAction(ClipMech.ArmStates.READY),groups.moveVerticalSlidesAction(highchamber, greaterTolerance)), robot.driveTrain.strafeToLinearHeading(new Vector2d(-9.5, 63), Math.toRadians(90), new Vector2d(-6, 31), Math.toRadians(90))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance)}))
                .addNext(new MultipleCommand(groups.slidesSetPos(1100,greaterTolerance),groups.moveCoAxial(NewIntake.CoaxialStates.RELEASE),groups.moveIntakeTime(NewIntake.IntakeStates.OUTTAKE,.3)))
                // clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-6, 31), Math.toRadians(90), new Vector2d(-43, 56), Math.toRadians(90))
                        ,groups.moveVerticalSlidesAction(0, greaterTolerance),groups.moveIntakeActionForShortTime(NewIntake.IntakeStates.STOP)
                        ,groups.moveCoAxialAction(NewIntake.CoaxialStates.OFFTHEWALL),groups.movePivotAction(NewIntake.PivotStates.OFFTHEWALL)}))
                .addNext(new MultipleCommand(groups.moveClipMag(ClipMech.ArmStates.ALMOST_DOWN),(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.AUTOPOS, autoposencoderpos))))
                .addNext(new Delay(.2))
                .addNext(new MultipleCommand(groups.slidesSetPos(750,greaterTolerance),groups.moveIntakeTime(NewIntake.IntakeStates.INTAKE,.6)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-43, 56), Math.toRadians(90), new Vector2d(-43, 55), Math.toRadians(90)),
                        groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos), groups.moveIntakeAction(NewIntake.IntakeStates.STOP),
                groups.movePivotAction(NewIntake.PivotStates.CHAMBERPOSBOTH),groups.moveClipMagAction(ClipMech.ArmStates.DOWN)}))
                //go to get the human players clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-43, 55), Math.toRadians(90), new Vector2d(-2.5, 32), Math.toRadians(90))}))
                .addNext(groups.slidesSetPos(highbasket,greaterTolerance))
                .addNext(new MultipleCommand(groups.moveIntakeTime(NewIntake.IntakeStates.OUTTAKE,.4),groups.moveCoAxial(NewIntake.CoaxialStates.RELEASE)));
    }
}

