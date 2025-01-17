package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Disabled
@Autonomous
public class RedClipSide4Different extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(-15,63), Math.toRadians(90));
        runpath = new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber, normalTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-15,63), Math.toRadians(90),new Vector2d(1,32),Math.toRadians(90))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.6, groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)),
                        groups.delayAction(.6,groups.moveIntakeAction(JohnsIntake.IntakeStates.OUTTAKE))}))
                // clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-1.5,32),Math.toRadians(90),new Vector2d(-47,45), Math.toRadians(90))
                        ,groups.moveVerticalSlidesAction(0, normalTolerance),groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos),groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,45), Math.toRadians(90),new Vector2d(-47,48.6), Math.toRadians(90)),
                        groups.delayAction(.3,groups.movePivotAction(JohnsIntake.PivotStates.SLIGHTLY_LOWER_PICKUP)),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKE)}))
                //go to get the human players clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,48.6), Math.toRadians(90),new Vector2d(-2.5,33), Math.toRadians(90)),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP),
                        groups.movePivotAction(JohnsIntake.PivotStates.CHAMBERPOS),
                        groups.delayAction(1.1,groups.moveVerticalSlidesAction(lowchamber, normalTolerance)),
                        groups.delayAction(.5,groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.Fully_In,fullyInEncoderPos))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.6, groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)),
                        groups.delayAction(.6,groups.moveIntakeAction(JohnsIntake.IntakeStates.OUTTAKE))}))
                // go to clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-2,33), Math.toRadians(90),new Vector2d(-33,38),Math.toRadians(90))
                        , groups.moveVerticalSlidesAction(0, normalTolerance),
                        groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-33,38),Math.toRadians(90),new Vector2d(-39,10), Math.toRadians(90))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-39,10),Math.toRadians(90),new Vector2d(-45,10), Math.toRadians(90))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45,10),Math.toRadians(90),new Vector2d(-45,53), Math.toRadians(90))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45,53),Math.toRadians(90),new Vector2d(-47, 10), Math.toRadians(90))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,10),Math.toRadians(90),new Vector2d(-54, 53), Math.toRadians(90))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45,53),Math.toRadians(90),new Vector2d(-47,45), Math.toRadians(90))}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos),groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,45), Math.toRadians(90),new Vector2d(-47,48.6), Math.toRadians(90)),
                        groups.delayAction(.3,groups.movePivotAction(JohnsIntake.PivotStates.SLIGHTLY_LOWER_PICKUP)),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKE)}));
    }
}