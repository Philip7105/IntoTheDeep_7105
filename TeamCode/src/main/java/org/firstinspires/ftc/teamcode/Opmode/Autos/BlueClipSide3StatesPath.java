package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowchamber;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Disabled
public class BlueClipSide3StatesPath extends BaseAuto {
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
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-2,33), Math.toRadians(90),new Vector2d(-47,45), Math.toRadians(90))
                        , groups.moveVerticalSlidesAction(0, normalTolerance),
                        groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                        groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),
                        groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos),groups.moveGripper(JohnsIntake.GripperStates.UNCLAMP),groups.movePivot(JohnsIntake.PivotStates.FORWARD),groups.moveIntake(JohnsIntake.IntakeStates.INTAKE)))
//                .addNext(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos))
                .addNext(new Delay(.2))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In,fullin),groups.movePivot(JohnsIntake.PivotStates.CHAMBERPOS),groups.moveIntake(JohnsIntake.IntakeStates.STOP)))
                .addNext(groups.moveIntake(JohnsIntake.IntakeStates.OUTTAKE))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-2,33), Math.toRadians(90),new Vector2d(-48,45), Math.toRadians(90)),groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos),groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP),groups.movePivotAction(JohnsIntake.PivotStates.FORWARD),groups.moveIntakeAction(JohnsIntake.IntakeStates.INTAKE)}))
//                .addNext(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos))
                .addNext(new Delay(.2))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-48,45), Math.toRadians(90),new Vector2d(-58.5,45), Math.toRadians(90)),groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.Fully_In,fullin),groups.movePivotAction(JohnsIntake.PivotStates.CHAMBERPOS),groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP)}))
                .addNext(groups.moveIntake(JohnsIntake.IntakeStates.OUTTAKE));
    }
}
