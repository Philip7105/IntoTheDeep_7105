package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highchamber;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

@Autonomous
public class ClipAutoPushWithSelfClip extends BaseAuto{
        @Override
        public void runAuto() {
            robot.driveTrain.setPoseEstimateBetter(new Vector2d(-9.5,63), Math.toRadians(90));
            runpath = new MultipleRRActionsWithPathing(new Action[]{groups.delayAction(.3,groups.moveVerticalSlidesAction(highchamber, greaterTolerance)),robot.driveTrain.strafeToLinearHeading(new Vector2d(-9.5,63), Math.toRadians(90),new Vector2d(-4,31),Math.toRadians(90)), groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAY)})
                    .addNext(new Delay(.2))
                    .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveVerticalSlidesAction(lowbasket, greaterTolerance),groups.delayAction(.6, new ParallelAction(groups.moveGripperAction(JohnsIntake.GripperStates.UNCLAMP)
                            ,groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.OUTTAKE)))}))
                    // go to clip
                    .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.getTheSamples(new Vector2d(-4,31), Math.toRadians(90))
                            , groups.moveVerticalSlidesAction(0, greaterTolerance)
                            ,groups.moveClipMagAction(ClipMech.ArmStates.OUT_THE_WAY),
                            groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL),
                            groups.moveGripperAction(JohnsIntake.GripperStates.CLAMP),
                            groups.moveIntakeActionForShortTime(JohnsIntake.IntakeStates.STOP)}));
    }
}
