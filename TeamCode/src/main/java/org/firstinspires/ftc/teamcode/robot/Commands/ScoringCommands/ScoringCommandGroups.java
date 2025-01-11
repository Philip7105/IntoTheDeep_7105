package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands;


import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.DelayAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.EmptyAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.HoldVerticalSlidePosAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveHorizontalSlidesAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MovePivotAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetDelayAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetGripperAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetIntakeAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetPivotAction;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveArmJohn;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveClipMech;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveGripper;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveGripperAction;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveLeftHang;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveHorizontalSlidesEncoder;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveIntakeJohn;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveRightHang;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveVerticalSlidesBetter;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveVerticalSlidesAction;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
/** This contains a few of our robot's more frequently used commands.
 *
 */

public class ScoringCommandGroups {
    JohnsIntake intake;
    ClipMech clipmech;
    HorizontalSlides horizontalSlides;
    VerticalSlides verticalslides;
    JohnHanging hang;
//    LinearOpMode opMode;
    public ScoringCommandGroups(JohnsIntake intake, VerticalSlides verticalslides, HorizontalSlides horizontalslides, ClipMech clipmech, JohnHanging hanging) {
        this.intake = intake;
        this.verticalslides = verticalslides;
        this.horizontalSlides = horizontalslides;
        this.clipmech = clipmech;
        this.hang = hanging;
//        this.opMode = opMode;
    }

    public Command initRobot(){
        return new MultipleCommand(moveGripper(JohnsIntake.GripperStates.clamp), movePivotJohn(JohnsIntake.PivotStates.chamberpos),moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_In,fullyInEncoderPos));
    }

    public Command slidesTeleop(){
        return new MoveVerticalSlidesBetter(verticalslides,false,0);
    }

    public Command slidesSetPos(double target){
        return new MoveVerticalSlidesBetter(verticalslides,true,target);
    }

    public Command moveClipMechanismsOut(double verticalSlidesTarget, ClipMech.ArmStates clipstate, HorizontalSlides.HorizontalSlideStates horizontalstate, double target, JohnsIntake.PivotStates armstate){
        return new MultipleCommand(slidesSetPos(verticalSlidesTarget),extendHorizontalSlides_VerticalSlides(clipstate,horizontalstate,target,armstate));
    }

    public Command armOutBack(){
        return new MultipleCommand(movePivotJohn(JohnsIntake.PivotStates.basketpos));
    }

    public Command armOutFront(){
        return new MultipleCommand(movePivotJohn(JohnsIntake.PivotStates.forward),moveGripper(JohnsIntake.GripperStates.clamp));
    }


    public Command movePivotJohn(JohnsIntake.PivotStates pivotStates){
        return new MoveArmJohn(this.intake, pivotStates);
    }

    public Command moveIntake(JohnsIntake.IntakeStates intakeStates){
        return new MoveIntakeJohn(this.intake, intakeStates);
    }
    public Command moveGripper(JohnsIntake.GripperStates gripperStates){
        return new MoveGripper(this.intake, gripperStates);
    }

    public Command moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        return new MoveHorizontalSlidesEncoder(this.horizontalSlides,horizontalslidestates,target);
    }

    public Command fullExtendHorizontalSLides(){
        return new MultipleCommand(moveClipMag(ClipMech.ArmStates.READY), moveGripper(JohnsIntake.GripperStates.unclamp),
                new Delay(.1).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos)));
    }

    public Command clipClip(){
        return new MultipleCommand(moveClipMag(ClipMech.ArmStates.Out_The_Way), movePivotJohn(JohnsIntake.PivotStates.preauto_clip),new Delay(.1).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos)).addNext(movePivotJohn(JohnsIntake.PivotStates.posauto_clip).addNext(moveGripper(JohnsIntake.GripperStates.unclamp))));
    }

    public Command extendHorizontalSLides(){
        return new MultipleCommand(moveClipMag(ClipMech.ArmStates.READY), moveGripper(JohnsIntake.GripperStates.unclamp)
                ,new Delay(.1).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,halfOutEncoderPos)));
    }

    public Command extendHorizontalSlides_VerticalSlides(ClipMech.ArmStates clipstate, HorizontalSlides.HorizontalSlideStates horizontalstate, double target, JohnsIntake.PivotStates armstate){
        return new MultipleCommand(moveClipMag(clipstate),new Delay(.4).addNext(moveHorizontalSlides(horizontalstate,target)).addNext(movePivotJohn(armstate)));
    }

    public Command bringInHorizontalSLidesBetter(){
        return new MultipleCommand(moveGripper(JohnsIntake.GripperStates.clamp),new MoveHorizontalSlidesEncoder(this.horizontalSlides,HorizontalSlides.HorizontalSlideStates.Fully_In,fullyInEncoderPos).addNext(moveClipMag(ClipMech.ArmStates.Out_The_Way)), movePivotJohn(JohnsIntake.PivotStates.parallel));
    }

    public Command moveClipMag(ClipMech.ArmStates armstates){
        return new MoveClipMech(clipmech,armstates);
    }

// Below are the Actions for RR we will use these actions to seamlessly flow into the command scheduler.

    public Action fullExtendHorizontalSLidesAction(){
        return new ParallelAction(moveGripperAction(JohnsIntake.GripperStates.unclamp),
                delayAction(.1, moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates.Fully_Out,fullyOutEncoderPos)));
    }


    public Action emptyAction(){
        return new EmptyAction(Dashboard.packet);
    }

    public Action delayAction(double time,Action action){
        return new SequentialAction(new DelayAction(time,Dashboard.packet), new ResetDelayAction(Dashboard.packet),action);
    }

    public Action moveGripperAction(JohnsIntake.GripperStates gripperStates){
        return new SequentialAction(new MoveGripperAction(intake,gripperStates,Dashboard.packet),new ResetGripperAction(Dashboard.packet));
    }

    public Action movePivotAction(JohnsIntake.PivotStates pivotStates){
        return new SequentialAction(new MovePivotAction(intake,pivotStates,Dashboard.packet),new ResetPivotAction(Dashboard.packet));
    }

    public Action moveIntakeAction(JohnsIntake.IntakeStates intakeStates){
        return new SequentialAction(new MoveIntakeAction(intake,intakeStates,Dashboard.packet),new ResetIntakeAction(Dashboard.packet));
    }

    public Action moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates horizontalSlideStates, double target){
        return new MoveHorizontalSlidesAction(horizontalSlides,horizontalSlideStates,target,Dashboard.packet);
    }

    public Action moveVerticalSlidesAction(double target, double tolerance){
        return new SequentialAction(new MoveVerticalSlidesAction(verticalslides, target, tolerance,Dashboard.packet), new HoldVerticalSlidePosAction(verticalslides,Dashboard.packet));
    }

    public Command moveHang(JohnHanging.LeftHangStates lHangState, JohnHanging.LeftHangStates lEndHangState, JohnHanging.RightHangStates rHangState, JohnHanging.RightHangStates rEndHangState, double ref){
        return new MultipleCommand(new MoveLeftHang(hang,lHangState,lEndHangState,ref), new MoveRightHang(hang,rHangState,rEndHangState,ref));
    }

    public Command moveHangTakeTwo(JohnHanging.LeftHangStates lHangState, JohnHanging.LeftHangStates lEndHangState, JohnHanging.RightHangStates rHangState, JohnHanging.RightHangStates rEndHangState, double ref){
        return new MultipleCommand(new MoveLeftHang(hang,lHangState,lEndHangState,ref), new MoveRightHang(hang,rHangState,rEndHangState,ref));
    }

//    public Command pullUp(){
//        return new MoveHang(hang, foldpower, readyFirstHang);
//    }
//
//    public Command pullDown(){
//        return new MoveHang(hang, unfoldpower, firstHang);
//    }
}