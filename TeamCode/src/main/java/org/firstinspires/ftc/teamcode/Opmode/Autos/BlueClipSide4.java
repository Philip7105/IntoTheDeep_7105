package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Rotation2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Disabled
@Autonomous
public class BlueClipSide4 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimate(new Vector2d(-14,63), Rotation2d.exp(Math.toRadians(90)));
        runpath = new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber, normalTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-14, 63), Math.toRadians(90), new Vector2d(-1.5,32),Math.toRadians(90))})
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)),
                        groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.OUTTAKE))}))
               //  clip first specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-1.5,32),Math.toRadians(90), new Vector2d(-37,37), Math.toRadians(90)), groups.moveVerticalSlidesAction(0, normalTolerance),
                        groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL), groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP), groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)})
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-37, 37), Math.toRadians(90), new Vector2d(-37,10), Math.toRadians(90))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-37,10), Math.toRadians(90), new Vector2d(-45, 10), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, 10), Math.toRadians(180), new Vector2d(-45, 55), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, 55), Math.toRadians(180), new Vector2d(-45, 10), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, 10), Math.toRadians(180), new Vector2d(-54, 10), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-54, 10), Math.toRadians(180), new Vector2d(-54, 55), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-54, 55), Math.toRadians(180), new Vector2d(-54, 10), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-54, 10), Math.toRadians(180), new Vector2d(-64, 10), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-64, 10), Math.toRadians(180), new Vector2d(-64, 55), Math.toRadians(180))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-64, 55), Math.toRadians(180), new Vector2d(-55, 30), Math.toRadians(90))})))
               //  push all of the samples in
                .addNext(new Delay(4))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos),groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-55, 30), Math.toRadians(90), new Vector2d(-47,49), Math.toRadians(90)),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKE),
                        groups.movePivotAction(JohnsIntake.PivotStates.SLIGHTLY_LOWER_PICKUP)}))
               //  intake another specimen
                .addNext(new Delay(3))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In, fullin),
                        groups.movePivot(JohnsIntake.PivotStates.PARALLEL), groups.moveIntake(JohnsIntake.IntakeStates.STOP))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber, normalTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,49), Math.toRadians(90), new Vector2d(-0.5,32),Math.toRadians(90))}))
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)),
                                groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.OUTTAKE))})))
             //    clip second specimen
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-0.5,32),Math.toRadians(90), new Vector2d(-47,49), Math.toRadians(90)),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKE), groups.movePivotAction(JohnsIntake.PivotStates.SLIGHTLY_LOWER_PICKUP)})
                //         intake another specimen
                        .addNext(new Delay(3))
                        .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In, fullin),
                                groups.movePivot(JohnsIntake.PivotStates.PARALLEL), groups.moveIntake(JohnsIntake.IntakeStates.STOP))
                                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber, normalTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,49), Math.toRadians(90), new Vector2d(0.5,32),Math.toRadians(90))}))
                                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)),
                                        groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.OUTTAKE))})))
                  //       clip third specimen
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(0.5,32),Math.toRadians(90), new Vector2d(-47,49), Math.toRadians(90)),
                                groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKE), groups.movePivotAction(JohnsIntake.PivotStates.SLIGHTLY_LOWER_PICKUP)})
                    //             intake another specimen
                                .addNext(new Delay(3))
                                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In, fullin),
                                        groups.movePivot(JohnsIntake.PivotStates.PARALLEL), groups.moveIntake(JohnsIntake.IntakeStates.STOP))
                                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowchamber, normalTolerance),robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,49), Math.toRadians(90), new Vector2d(1.5,32), Math.toRadians(90))}))
                                        .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.3, groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)),
                                                groups.delayAction(.3,groups.moveIntakeAction(JohnsIntake.IntakeStates.OUTTAKE))}))))
               //          clip fourth specimen
                        .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(1.5,32), Math.toRadians(90), new Vector2d(-47, 55), Math.toRadians(90))})));


    }
}