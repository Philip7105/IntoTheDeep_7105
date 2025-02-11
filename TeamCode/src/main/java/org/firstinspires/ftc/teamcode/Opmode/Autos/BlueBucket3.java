package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.FULLY_IN;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.FULLY_OUT;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.GripperStates.UNCLAMP;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.CoaxialStates.CLAMP;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.CoaxialStates.RELEASE;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.INTAKE;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.OUTTAKE;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.OUTTAKEAUTO;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.IntakeStates.STOP;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.PivotStates.CHAMBERPOS;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.PivotStates.PARALLEL;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake.PivotStates.SHOVELPIVOTPOS;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

@Autonomous
public class BlueBucket3 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(32, 60), Math.toRadians(270));
        runpath = new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(32, 60), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.movePivotAction(PARALLEL), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)})
                .addNext(groups.movePivot(CHAMBERPOS))
                .addNext(new MultipleCommand(groups.moveCoAxial(CLAMP), groups.moveIntakeTime(OUTTAKE,.2)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveIntakeAction(STOP), robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5, 56.5), Math.toRadians(225), new Vector2d(53, 53), Math.toRadians(225)), groups.moveCoAxialAction(CLAMP), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, normalTolerance)), groups.movePivotAction(PARALLEL)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(53, 53), Math.toRadians(225), new Vector2d(48.2, 49.3), Math.toRadians(270))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(FULLY_OUT, fullyOutEncoderPos), groups.movePivotAction(SHOVELPIVOTPOS), groups.moveIntakeAction(INTAKE)}))
                .addNext(new MultipleCommand(groups.movePivot(PARALLEL), groups.moveCoAxial(CLAMP), groups.moveIntakeTime(STOP,.1), groups.moveHorizontalSlides(FULLY_IN, fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(48.2, 49.3), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)}))
                .addNext(new MultipleCommand(groups.movePivot(CHAMBERPOS),groups.moveCoAxial(RELEASE), new Delay(.6).addNext(groups.moveIntakeTime(NewIntake.IntakeStates.OUTTAKE,.2))))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{(Action) groups.moveCoAxial(CLAMP),robot.driveTrain.strafeToLinearHeading(new Vector2d(57, 55), Math.toRadians(225), new Vector2d(52.3, 56), Math.toRadians(225)), groups.moveIntakeAction(STOP), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, greaterTolerance)), groups.movePivotAction(PARALLEL)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(52.3, 56),Math.toRadians(225),new Vector2d(58.5,49.7),Math.toRadians(270))}))
                .addNext(new Delay(.5))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(FULLY_OUT,fullyOutEncoderPos),groups.moveCoAxial(UNCLAMP),groups.moveIntakeTime(INTAKE,.2)))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(FULLY_OUT,fullyOutEncoderPos)))
                .addNext(new Delay(.1))
                .addNext(new MultipleCommand(groups.movePivot(SHOVELPIVOTPOS)))
                .addNext(groups.moveIntakeTime(INTAKE,.2))
                .addNext(new MultipleCommand(groups.movePivot(PARALLEL),groups.moveCoAxial(CLAMP),groups.moveIntakeTime(STOP,.1),groups.moveHorizontalSlides(FULLY_IN,fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5,49.7),Math.toRadians(270),new Vector2d(57,56),Math.toRadians(225)),groups.delayAction(.5,groups.moveVerticalSlidesAction(highbasket,greaterTolerance)),groups.movePivotAction(CHAMBERPOS)}))
                .addNext(new MultipleCommand(groups.moveCoAxial(UNCLAMP),groups.moveIntakeTime(OUTTAKE,.2)))
                .addNext(new Delay(.3))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{(Action) groups.moveCoAxial(CLAMP),robot.driveTrain.strafeToLinearHeading(new Vector2d(57, 56), Math.toRadians(225), new Vector2d(52.3, 56), Math.toRadians(225)), groups.moveIntakeAction(STOP), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, greaterTolerance)), groups.movePivotAction(PARALLEL)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(52.3, 56), Math.toRadians(225), new Vector2d(56.2, 47), Math.toRadians(305))}))
                .addNext(new Delay(.5))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(FULLY_OUT,fullyOutEncoderPos),groups.moveCoAxial(UNCLAMP),groups.movePivot(SHOVELPIVOTPOS),groups.moveIntakeTime(INTAKE,.2)))
                .addNext(new Delay(.3))
                .addNext(new MultipleCommand(groups.movePivot(PARALLEL),groups.moveCoAxial(CLAMP),groups.moveIntakeTime(STOP,.1),groups.moveHorizontalSlides(FULLY_IN,fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(56.2,47),Math.toRadians(305),new Vector2d(53,53),Math.toRadians(225)),groups.delayAction(.5,groups.moveVerticalSlidesAction(highbasket,greaterTolerance)),groups.movePivotAction(CHAMBERPOS)}))
                .addNext(new MultipleCommand(groups.moveCoAxial(UNCLAMP),groups.moveIntakeTime(OUTTAKE,.2)));
    }
}
