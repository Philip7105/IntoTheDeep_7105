package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.FULLY_IN;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.FULLY_OUT;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.HALF_OUT;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.crazyBigTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.CoaxialStates.CLAMP;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.CoaxialStates.OVERHEADPOS;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.CoaxialStates.RELEASE;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.INTAKE;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.INTAKEOUTTAKE;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.OUTTAKE;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.STOP;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.PivotStates.CHAMBERPOS;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.PivotStates.PARALLEL;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.PivotStates.SHOVELPIVOTPOS;

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
public class BlueBucket3 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(32, 60), Math.toRadians(180));
        runpath = new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(32, 60), Math.toRadians(180), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.moveCoAxialAction(CLAMP),groups.movePivotAction(PARALLEL), new SequentialAction(groups.moveClipMagAction(ClipMech.ArmStates.READY),new ParallelAction(groups.moveVerticalSlidesActionWITHOUTSAFETY(highbasket, crazyBigTolerance),groups.moveClipMagAction(ClipMech.ArmStates.OUTOFTHEWAYFORSAMPLESIDEAUTO)))})
                .addNext(new MultipleCommand(groups.movePivot(CHAMBERPOS),groups.moveCoAxial(RELEASE),groups.moveIntakeTime(OUTTAKE,.5)))

                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveIntakeAction(STOP), robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5, 56.5), Math.toRadians(225), new Vector2d(55, 55), Math.toRadians(225)), groups.moveCoAxialAction(CLAMP), groups.delayAction(.5, groups.moveVerticalSlidesActionWITHOUTSAFETY(0, greaterTolerance)), groups.movePivotAction(PARALLEL)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(55, 55), Math.toRadians(225), new Vector2d(48.2, 48.7), Math.toRadians(266)),
                groups.moveHorizontalSlidesAction(FULLY_OUT, fullyOutEncoderPos), groups.movePivotAction(SHOVELPIVOTPOS), groups.moveIntakeAction(INTAKE)}))
                .addNext(new MultipleCommand(groups.moveCoAxial(OVERHEADPOS),groups.movePivot(NewIntake.PivotStates.OVERHEADPOS)))
                .addNext(new Delay(.4))

                .addNext(new MultipleCommand(groups.movePivot(PARALLEL), groups.moveCoAxial(CLAMP), groups.moveHorizontalSlides(FULLY_IN, fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(48.2, 48.7), Math.toRadians(266), new Vector2d(58.5, 57.2), Math.toRadians(225)), groups.moveIntakeAction(STOP),groups.moveVerticalSlidesActionWITHOUTSAFETY(highbasket, crazyBigTolerance)}))
                .addNext(new MultipleCommand(groups.movePivot(CHAMBERPOS),groups.moveCoAxial(RELEASE),groups.moveIntakeTime(INTAKEOUTTAKE,.6)))

                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveIntakeAction(STOP), robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5, 57.2), Math.toRadians(225), new Vector2d(55, 55), Math.toRadians(225)), groups.moveCoAxialAction(CLAMP), groups.delayAction(.5, groups.moveVerticalSlidesActionWITHOUTSAFETY(0, greaterTolerance)), groups.movePivotAction(PARALLEL)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(55, 55), Math.toRadians(225), new Vector2d(57.6, 47.2), Math.toRadians(270)),
                       groups.moveHorizontalSlidesAction(FULLY_OUT, fullyOutEncoderPos), groups.movePivotAction(SHOVELPIVOTPOS), groups.moveIntakeAction(INTAKE)}))
                .addNext(new MultipleCommand(groups.moveCoAxial(OVERHEADPOS),groups.movePivot(NewIntake.PivotStates.OVERHEADPOS)))
                .addNext(new Delay(.4))

                .addNext(new MultipleCommand(groups.movePivot(PARALLEL),groups.moveCoAxial(CLAMP),groups.moveIntakeTime(INTAKE,.3),groups.moveHorizontalSlides(FULLY_IN,fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(57.6,47.2),Math.toRadians(270),new Vector2d(58.5,57),Math.toRadians(225)),groups.delayAction(.5,groups.moveVerticalSlidesActionWITHOUTSAFETY(highbasket,crazyBigTolerance))}))
                .addNext(new MultipleCommand(groups.movePivot(CHAMBERPOS),groups.moveCoAxial(RELEASE),groups.moveIntakeTime(INTAKEOUTTAKE,.6)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveCoAxialAction(CLAMP),robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5,57), Math.toRadians(225), new Vector2d(56.5,45.2), Math.toRadians(295)), groups.moveIntakeAction(INTAKE), groups.delayAction(.5, groups.moveVerticalSlidesActionWITHOUTSAFETY(0, greaterTolerance)), groups.movePivotAction(PARALLEL)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(FULLY_OUT, fullyOutEncoderPos), groups.movePivotAction(SHOVELPIVOTPOS), groups.moveIntakeAction(INTAKE)}))
                .addNext(new MultipleCommand(groups.moveCoAxial(OVERHEADPOS),groups.movePivot(NewIntake.PivotStates.OVERHEADPOS)))
                .addNext(new Delay(.4))

                .addNext(new MultipleCommand(groups.movePivot(PARALLEL),groups.moveCoAxial(CLAMP),groups.moveIntakeTime(INTAKE,.4),groups.moveHorizontalSlides(FULLY_IN,fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(56.5,45.2),Math.toRadians(295),new Vector2d(59,57), Math.toRadians(225)),groups.moveIntakeAction(STOP),groups.delayAction(.5,groups.moveVerticalSlidesActionWITHOUTSAFETY(highbasket,crazyBigTolerance))}))
                .addNext(new MultipleCommand(groups.movePivot(CHAMBERPOS),groups.moveCoAxial(RELEASE),groups.moveIntakeTime(INTAKEOUTTAKE,.6)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(59,57), Math.toRadians(225),new Vector2d(38,10),Math.toRadians(200)),groups.moveIntakeAction(STOP),groups.delayAction(.4,new ParallelAction(groups.moveVerticalSlidesActionWITHOUTSAFETY(0,crazyBigTolerance),groups.moveClipMagAction(ClipMech.ArmStates.READY))),groups.movePivotAction(PARALLEL)}));
    }
}
