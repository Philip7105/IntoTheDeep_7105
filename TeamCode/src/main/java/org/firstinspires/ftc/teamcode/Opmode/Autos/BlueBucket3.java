package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.Fully_In;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullout;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfout;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.GripperStates.clamp;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.GripperStates.unclamp;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.IntakeStates.intake;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.IntakeStates.outtake;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.IntakeStates.stop;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.basketpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.forward;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.parallel;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.DelayAction;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Config
@Autonomous
public class BlueBucket3 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(32, 60), Math.toRadians(270));
        runpath = new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(32, 60), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.movePivotAction(parallel), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)})
                .addNext(groups.movePivot(basketpos))
                .addNext(new MultipleCommand(groups.moveGripper(unclamp), groups.moveIntake(JohnsIntake.IntakeStates.outtake)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveIntakeAction(JohnsIntake.IntakeStates.stop), robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5, 56.5), Math.toRadians(225), new Vector2d(53, 53), Math.toRadians(225)), groups.moveGripperAction(clamp), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, normalTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.parallel)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(53, 53), Math.toRadians(225), new Vector2d(48.2, 49.2), Math.toRadians(270))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.Fully_Out, fullyOutEncoderPos), groups.moveGripperAction(unclamp), groups.moveIntakeAction(intake)}))
                .addNext(groups.movePivot(forward))
                .addNext(new Delay(.3))
                .addNext(new MultipleCommand(groups.movePivot(parallel), groups.moveGripper(clamp), groups.moveIntake(JohnsIntake.IntakeStates.stop), groups.moveHorizontalSlides(Fully_In, fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(48.2, 49.2), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)}))
                .addNext(new MultipleCommand(groups.movePivot(basketpos),groups.moveGripper(unclamp), new Delay(.6).addNext(groups.moveIntake(JohnsIntake.IntakeStates.outtake))))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveGripperAction(clamp),robot.driveTrain.strafeToLinearHeading(new Vector2d(57, 55), Math.toRadians(225), new Vector2d(52.3, 56), Math.toRadians(225)), groups.moveIntakeAction(JohnsIntake.IntakeStates.stop), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, greaterTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.parallel)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(52.3, 56),Math.toRadians(225),new Vector2d(58.5,52),Math.toRadians(270))}))
                .addNext(new Delay(.5))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos),groups.moveGripper(unclamp),groups.moveIntake(intake)))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos)))
                .addNext(new MultipleCommand(groups.movePivot(forward)))
                .addNext(new Delay(.3))
                .addNext(new MultipleCommand(groups.movePivot(parallel),groups.moveGripper(clamp),groups.moveIntake(stop),groups.moveHorizontalSlides(Fully_In,fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5,56.4),Math.toRadians(270),new Vector2d(53,53),Math.toRadians(225)),groups.delayAction(.5,groups.moveVerticalSlidesAction(highbasket,greaterTolerance)),groups.movePivotAction(basketpos)}))
                .addNext(new MultipleCommand(groups.moveGripper(unclamp),groups.moveIntake(outtake)))
                .addNext(new Delay(.3))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveGripperAction(clamp),robot.driveTrain.strafeToLinearHeading(new Vector2d(57, 55), Math.toRadians(225), new Vector2d(52.3, 56), Math.toRadians(225)), groups.moveIntakeAction(JohnsIntake.IntakeStates.stop), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, greaterTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.parallel)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(52.3, 56), Math.toRadians(225), new Vector2d(56.2, 47), Math.toRadians(305))}))
                .addNext(new Delay(.5))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(fullout,fullyOutEncoderPos),groups.moveGripper(unclamp),groups.moveIntake(intake)))
                .addNext(groups.movePivot(forward))
                .addNext(new Delay(.3))
                .addNext(new MultipleCommand(groups.movePivot(parallel),groups.moveGripper(clamp),groups.moveIntake(stop),groups.moveHorizontalSlides(Fully_In,fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(56.2,47),Math.toRadians(305),new Vector2d(53,53),Math.toRadians(225)),groups.delayAction(.5,groups.moveVerticalSlidesAction(highbasket,greaterTolerance)),groups.movePivotAction(basketpos)}))
                .addNext(new MultipleCommand(groups.moveGripper(unclamp),groups.moveIntake(outtake)))
        ;

    }
}
