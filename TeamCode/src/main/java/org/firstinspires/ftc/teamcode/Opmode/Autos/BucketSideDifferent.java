package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Autonomous
public class BucketSideDifferent extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimateBetter(new Vector2d(32, 62), Math.toRadians(0));
        runpath = new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(32,62), Math.toRadians(0),new Vector2d(57,55), Math.toRadians(45)), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)})
                .addNext(new MultipleCommand(groups.moveGripper(JohnsIntake.GripperStates.unclamp),groups.moveIntake(JohnsIntake.IntakeStates.outtake)))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveIntakeAction(JohnsIntake.IntakeStates.stop),groups.moveGripperAction(JohnsIntake.GripperStates.clamp),robot.driveTrain.strafeToLinearHeading(new Vector2d(57,55),Math.toRadians(45),new Vector2d(48.2,47), Math.toRadians(270)),groups.delayAction(.7,groups.moveVerticalSlidesAction(0, greaterTolerance)),groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL)}))
                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out, halfOutEncoderPos),groups.movePivot(JohnsIntake.PivotStates.FORWARD)));
    }
}
