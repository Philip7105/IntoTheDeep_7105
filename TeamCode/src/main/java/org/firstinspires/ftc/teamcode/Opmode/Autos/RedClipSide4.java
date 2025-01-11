package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.autoGreaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.autoTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.holdPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Config
@Autonomous
public class RedClipSide4 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimate(new Vector2d(-14,-63), Rotation2d.exp(Math.toRadians(270)));
        runpath = new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber,autoTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-14, -63), Math.toRadians(270), new Vector2d(-1.5,-32),Math.toRadians(270))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket,autoGreaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.unclamp)),
                        groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.outtake))}))
                //  clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-1.5,-32),Math.toRadians(180), new Vector2d(-37,-37), Math.toRadians(270)), groups.moveVerticalSlidesAction(0, autoTolerance),
                        groups.movePivotAction(JohnsIntake.PivotStates.parallel), groups.moveGripperAction(JohnsIntake.GripperStates.clamp), groups.moveIntakeAction(JohnsIntake.IntakeStates.stop)})
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-37, -37), Math.toRadians(270), new Vector2d(-37,-10), Math.toRadians(270))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-37,-10), Math.toRadians(270), new Vector2d(-45, -10), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, -10), Math.toRadians(0), new Vector2d(-45, -55), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, -55), Math.toRadians(0), new Vector2d(-45, -10), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, -10), Math.toRadians(0), new Vector2d(-54, -10), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-54, -10), Math.toRadians(0), new Vector2d(-54, -55), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-54, -55), Math.toRadians(0), new Vector2d(-54, -10), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-54, -10), Math.toRadians(0), new Vector2d(-64, -10), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-64, -10), Math.toRadians(0), new Vector2d(-64, -55), Math.toRadians(0))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-64, -55), Math.toRadians(0), new Vector2d(-55, -30), Math.toRadians(270))})))
                //  push all of the samples in
                .addNext(new Delay(4))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos),groups.moveGripper(JohnsIntake.GripperStates.unclamp)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-55, -30), Math.toRadians(270), new Vector2d(-47,-49), Math.toRadians(270)),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.intake),
                        groups.movePivotAction(JohnsIntake.PivotStates.slightly_lower_pickup)}))
                //  intake another specimen
                .addNext(new Delay(3))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In, fullin),
                        groups.movePivotJohn(JohnsIntake.PivotStates.parallel), groups.moveIntake(JohnsIntake.IntakeStates.stop))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber,autoTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,-49), Math.toRadians(270), new Vector2d(-0.5,-32),Math.toRadians(270))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket,autoGreaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.unclamp)),
                                groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.outtake))})))
                //    clip second specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-0.5,-32),Math.toRadians(270), new Vector2d(-47,-49), Math.toRadians(270)),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.intake), groups.movePivotAction(JohnsIntake.PivotStates.slightly_lower_pickup)})
                        //         intake another specimen
                        .addNext(new Delay(3))
                        .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In, fullin),
                                groups.movePivotJohn(JohnsIntake.PivotStates.parallel), groups.moveIntake(JohnsIntake.IntakeStates.stop))
                                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber,autoTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,-49), Math.toRadians(270), new Vector2d(0.5,-32),Math.toRadians(270))}))
                                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket,autoGreaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.unclamp)),
                                        groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.outtake))})))
                        //       clip third specimen
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(0.5,-32),Math.toRadians(270), new Vector2d(-47,-49), Math.toRadians(270)),
                                groups.moveIntakeAction(JohnsIntake.IntakeStates.intake), groups.movePivotAction(JohnsIntake.PivotStates.slightly_lower_pickup)})
                                //             intake another specimen
                                .addNext(new Delay(3))
                                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In, fullin),
                                        groups.movePivotJohn(JohnsIntake.PivotStates.parallel), groups.moveIntake(JohnsIntake.IntakeStates.stop))
                                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber,autoTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,-49), Math.toRadians(270), new Vector2d(1.5,-32), Math.toRadians(270))}))
                                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket,autoGreaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.unclamp)),
                                                groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.outtake))}))))
                        //          clip fourth specimen
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(1.5,-32), Math.toRadians(270), new Vector2d(-47, -55), Math.toRadians(270))})));


    }
}