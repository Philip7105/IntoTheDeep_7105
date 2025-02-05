//package org.firstinspires.ftc.teamcode.Opmode.Autos;
//
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.FULLY_IN;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.HorizontalSlideStates.FULLY_OUT;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.greaterTolerance;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.normalTolerance;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides.highbasket;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.GripperStates.CLAMP;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.GripperStates.UNCLAMP;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.IntakeStates.INTAKE;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.IntakeStates.OUTTAKE;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.IntakeStates.STOP;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.BASKETPOS;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.FORWARD;
//import static org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake.PivotStates.PARALLEL;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.Action;
//import com.acmerobotics.roadrunner.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//
//import org.firstinspires.ftc.teamcode.CommandFrameWork.BaseAuto;
//import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
//import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
//import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MultipleRRActionsWithPathing;
//import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
//import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
//
//@Autonomous
//public class BlueBucket3 extends BaseAuto {
//    @Override
//    public void runAuto() {
//        robot.driveTrain.setPoseEstimateBetter(new Vector2d(32, 60), Math.toRadians(270));
//        runpath = new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(32, 60), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.movePivotAction(PARALLEL), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)})
//                .addNext(groups.movePivot(BASKETPOS))
//                .addNext(new MultipleCommand(groups.moveGripper(UNCLAMP), groups.moveIntake(JohnsIntake.IntakeStates.OUTTAKE)))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP), robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5, 56.5), Math.toRadians(225), new Vector2d(53, 53), Math.toRadians(225)), groups.moveGripperAction(CLAMP), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, normalTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL)}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(53, 53), Math.toRadians(225), new Vector2d(48.2, 49.3), Math.toRadians(270))}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.FULLY_OUT, fullyOutEncoderPos), groups.moveGripperAction(UNCLAMP), groups.moveIntakeAction(INTAKE)}))
//                .addNext(groups.movePivot(FORWARD))
//                .addNext(new Delay(.3))
//                .addNext(new MultipleCommand(groups.movePivot(PARALLEL), groups.moveGripper(CLAMP), groups.moveIntake(JohnsIntake.IntakeStates.STOP), groups.moveHorizontalSlides(FULLY_IN, fullyInEncoderPos)))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(48.2, 49.3), Math.toRadians(270), new Vector2d(58.5, 56.5), Math.toRadians(225)), groups.moveVerticalSlidesAction(highbasket, greaterTolerance)}))
//                .addNext(new MultipleCommand(groups.movePivot(BASKETPOS),groups.moveGripper(UNCLAMP), new Delay(.6).addNext(groups.moveIntake(JohnsIntake.IntakeStates.OUTTAKE))))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveGripperAction(CLAMP),robot.driveTrain.strafeToLinearHeading(new Vector2d(57, 55), Math.toRadians(225), new Vector2d(52.3, 56), Math.toRadians(225)), groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, greaterTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL)}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(52.3, 56),Math.toRadians(225),new Vector2d(58.5,49.7),Math.toRadians(270))}))
////                .addNext(new Delay(.5))
//                .addNext(new MultipleCommand(groups.moveHorizontalSlides(FULLY_OUT,fullyOutEncoderPos),groups.moveGripper(UNCLAMP),groups.moveIntake(INTAKE)))
////                .addNext(new MultipleCommand(groups.moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos)))
//                .addNext(new Delay(.1))
//                .addNext(new MultipleCommand(groups.movePivot(FORWARD)))
//                .addNext(new Delay(.3))
//                .addNext(new MultipleCommand(groups.movePivot(PARALLEL),groups.moveGripper(CLAMP),groups.moveIntake(STOP),groups.moveHorizontalSlides(FULLY_IN,fullyInEncoderPos)))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(58.5,49.7),Math.toRadians(270),new Vector2d(57,56),Math.toRadians(225)),groups.delayAction(.5,groups.moveVerticalSlidesAction(highbasket,greaterTolerance)),groups.movePivotAction(BASKETPOS)}))
//                .addNext(new MultipleCommand(groups.moveGripper(UNCLAMP),groups.moveIntake(OUTTAKE)))
//                .addNext(new Delay(.3))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveGripperAction(CLAMP),robot.driveTrain.strafeToLinearHeading(new Vector2d(57, 56), Math.toRadians(225), new Vector2d(52.3, 56), Math.toRadians(225)), groups.moveIntakeAction(JohnsIntake.IntakeStates.STOP), groups.delayAction(.5, groups.moveVerticalSlidesAction(0, greaterTolerance)), groups.movePivotAction(JohnsIntake.PivotStates.PARALLEL)}))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(52.3, 56), Math.toRadians(225), new Vector2d(56.2, 47), Math.toRadians(305))}))
//                .addNext(new Delay(.5))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{groups.moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos)}))
//                .addNext(new MultipleCommand(groups.moveHorizontalSlides(FULLY_OUT,fullyOutEncoderPos),groups.moveGripper(UNCLAMP),groups.moveIntake(INTAKE)))
//                .addNext(groups.movePivot(FORWARD))
//                .addNext(new Delay(.3))
//                .addNext(new MultipleCommand(groups.movePivot(PARALLEL),groups.moveGripper(CLAMP),groups.moveIntake(STOP),groups.moveHorizontalSlides(FULLY_IN,fullyInEncoderPos)))
//                .addNext(new MultipleRRActionsWithPathing(new Action[]{robot.driveTrain.strafeToLinearHeading(new Vector2d(56.2,47),Math.toRadians(305),new Vector2d(53,53),Math.toRadians(225)),groups.delayAction(.5,groups.moveVerticalSlidesAction(highbasket,greaterTolerance)),groups.movePivotAction(BASKETPOS)}))
//                .addNext(new MultipleCommand(groups.moveGripper(UNCLAMP),groups.moveIntake(OUTTAKE)));
//    }
//}
