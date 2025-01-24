package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.autopos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.autoposencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.clipoffwall;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.intakeslidesup;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Autonomous
public class RedClipSide3StatesPath extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(-9.5,63), Math.toRadians(90));
        runpath = new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber, normalTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-9.5,63), Math.toRadians(90),new Vector2d(-.5,32),Math.toRadians(90))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.6, new ParallelAction(groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)
                        ,groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.OUTTAKE)))}))
                // clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-1.5,32),Math.toRadians(90),new Vector2d(-47,45), Math.toRadians(90))
                        ,groups.moveVerticalSlidesAction(clipoffwall, greaterTolerance),groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos),groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,47), Math.toRadians(90),new Vector2d(-47,51.5), Math.toRadians(90)),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKESLOW)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(700, greaterTolerance)}))
                //go to get the human players clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,51.5), Math.toRadians(90),new Vector2d(-2.5,32), Math.toRadians(90)),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP),
                        groups.movePivotAction(JohnsIntake.PivotStates.CHAMBERPOS),
                        groups.moveVerticalSlidesAction(lowchamber, greaterTolerance),
                        groups.delayAction(.3,groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.6, new ParallelAction(groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)
                        ,groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.OUTTAKE)))}))
                // go to clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-2,32), Math.toRadians(90),new Vector2d(-48,45.5), Math.toRadians(270))
                        , groups.moveVerticalSlidesAction(intakeslidesup, normalTolerance),
                        groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.AUTOPOS,autoposencoderpos),groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP),groups.movePivot(JohnsIntake.PivotStates.FORWARD),groups.moveIntake(JohnsIntake.IntakeStates.INTAKE)))
                .addNext(new Delay(.2))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos),groups.movePivot(JohnsIntake.PivotStates.CHAMBERPOS),groups.moveIntake(JohnsIntake.IntakeStates.STOP)))
                .addNext(groups.moveIntake(JohnsIntake.IntakeStates.OUTTAKE))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-48,45.5), Math.toRadians(270),new Vector2d(-56,45.5), Math.toRadians(270)),groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.AUTOPOS,autoposencoderpos),groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP),groups.movePivotAction(JohnsIntake.PivotStates.FORWARD),groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKE)}))
                .addNext(new Delay(.2))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-56,45.5), Math.toRadians(270),new Vector2d(-62,45.5), Math.toRadians(270)),groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos),groups.movePivotAction(JohnsIntake.PivotStates.CHAMBERPOS),groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(groups.moveIntake(JohnsIntake.IntakeStates.OUTTAKE));
    }
}
