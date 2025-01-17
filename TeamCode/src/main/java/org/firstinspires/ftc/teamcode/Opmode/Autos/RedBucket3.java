package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.Fully_In;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.GripperStates.clamp;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.GripperStates.unclamp;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.BASKETPOS;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.FORWARD;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.PARALLEL;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Config
@Autonomous
public class RedBucket3 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(32, 60), Math.toRadians(270));
        runpath = new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(32, 60), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.movePivotAction(PARALLEL), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)})
                .addNext(groups.movePivot(BASKETPOS))
                .addNext(new MultipleCommand(groups.moveGripper(unclamp), groups.moveIntake(JohnsIntake.IntakeStates.outtake)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveIntakeAction(JohnsIntake.IntakeStates.stop), robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5, 56.5), Math.toRadians(225), new Vector2d(53, 53), Math.toRadians(225)), groups.moveGripperAction(clamp), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, normalTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(53, 53), Math.toRadians(225), new Vector2d(48.2, 49.2), Math.toRadians(270))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.Fully_Out, fullyOutEncoderPos), groups.moveGripperAction(unclamp), groups.moveIntakeAction(JohnsIntake.IntakeStates.intake)}))
                .addNext(groups.movePivot(FORWARD))
                .addNext(new Delay(.3))
                .addNext(new MultipleCommand(groups.movePivot(PARALLEL), groups.moveGripper(clamp), groups.moveIntake(JohnsIntake.IntakeStates.stop), groups.moveHorizontalSlides(Fully_In, fullyInEncoderPos)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(48.2, 49.2), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)}))
                .addNext(new MultipleCommand(groups.movePivot(BASKETPOS),groups.moveGripper(unclamp), new Delay(.6).addNext(groups.moveIntake(JohnsIntake.IntakeStates.outtake))))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveGripperAction(clamp),robot.driveTrain.strafeToLinearHeading(new Vector2d(57, 55), Math.toRadians(225), new Vector2d(52.3, 56), Math.toRadians(225)), groups.moveIntakeAction(JohnsIntake.IntakeStates.stop), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, greaterTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL)}));
    }
}
