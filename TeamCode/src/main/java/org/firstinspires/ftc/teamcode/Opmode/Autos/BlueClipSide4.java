package org.firstinspires.ftc.teamcode.Opmode.Autos;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.lowbasket;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Rotation2d;
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
public class BlueClipSide4 extends BaseAuto {
    @Override
    public void runAuto() {
        robot.driveTrain.setPoseEstimate(new Vector2d(-13,63), Rotation2d.exp(Math.toRadians(90)));
        runpath = new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-9,33),Rotation2d.exp(Math.toRadians(90)))})
                .addNext(new MultipleCommand(groups.slidesSetPos(lowbasket),new Delay(.5).addNext(groups.moveGripper(JohnsIntake.GripperStates.unclamp))))
                // clip first specimen
                .addNext(new)
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,47), Rotation2d.exp(Math.toRadians(90)))
//                ,groups.moveHorizontalSlidesWithRR(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos)}))


                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-47,49), Rotation2d.exp(Math.toRadians(90)))}))
                //go to get the human players clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-10,33), Rotation2d.exp(Math.toRadians(90)))}))
                // go to clip
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-33,38), Rotation2d.exp(Math.toRadians(90)))
                ,groups.moveVerticalSlidesWithRR(0)}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-39,10), Rotation2d.exp(Math.toRadians(90)))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, 10), Rotation2d.exp(Math.toRadians(180)))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-45, 55), Rotation2d.exp(Math.toRadians(180)))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-49, 10), Rotation2d.exp(Math.toRadians(180)))}))
                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(-52, 30), Rotation2d.exp(Math.toRadians(90)))}))
                .addNext(new Delay(2));









//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-39,10),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-45,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-45,55),Rotation2d.exp(Math.toRadians(180)))))
                // push in the first blue sample
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-45,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,55),Rotation2d.exp(Math.toRadians(180)))))
                // push in the second blue sample
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-61,10),Rotation2d.exp(Math.toRadians(180)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-61,55),Rotation2d.exp(Math.toRadians(180)))))
                // push in the final blue sample
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,30),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)))))
                // pick up a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-4,33),Rotation2d.exp(Math.toRadians(90)))))
                // place a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,40),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)))))
                // pick up a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-2,33),Rotation2d.exp(Math.toRadians(90)))))
                // place a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,40),Rotation2d.exp(Math.toRadians(90)))))
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-52,55),Rotation2d.exp(Math.toRadians(90)))))
                // pick up a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(0,33),Rotation2d.exp(Math.toRadians(90)))))
                // place a blue specimen
//                .addNext(new StrafetoLinearHeadingMultiActions(robot.driveTrain.strafeToLinearHeadingEvenBetter(new Vector2d(-54,55),Rotation2d.exp(Math.toRadians(90)))));
                // park

    }
}