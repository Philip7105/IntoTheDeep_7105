package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands;


import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.foldpower;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.handDown;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.hangUp;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging.unfoldpower;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveHorizontalSlidesAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MovePivotAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetGripperAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetIntakeAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetPivotAction;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveArmJohn;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveClipMech;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveGripper;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveGripperAction;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveHang;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveHorizontalSlidesEncoder;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveVerticalSlidesBetter;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveVerticalSlidesWithRR;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;

/** This contains all of the commands used in our robot.
 * Most commands are used in tele.
 */

public class ScoringCommandGroups {
    JohnsIntake intake;  // intake
    ClipMech clipmech;
    HorizontalSlides horizontalSlides;
    VerticalSlides verticalslides;
    JohnHanging hang;
    LinearOpMode opMode;
    public ScoringCommandGroups(JohnsIntake intake, VerticalSlides verticalslides, HorizontalSlides horizontalslides, ClipMech clipmech, JohnHanging hanging,LinearOpMode opMode) {
        this.intake = intake;
        this.verticalslides = verticalslides;
        this.horizontalSlides = horizontalslides;
        this.clipmech = clipmech;
        this.hang = hanging;
        this.opMode = opMode;
    }

    public Command initRobot(){
        return new MultipleCommand(moveGripper(JohnsIntake.GripperStates.clamp),moveArmJohn(JohnsIntake.PivotStates.outback));
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
        return new MultipleCommand(moveArmJohn(JohnsIntake.PivotStates.outback),moveGripper(JohnsIntake.GripperStates.unclamp));
    }

    public Command armOutFront(){
        return new MultipleCommand(moveArmJohn(JohnsIntake.PivotStates.forward),moveGripper(JohnsIntake.GripperStates.clamp));
    }


    public Command moveArmJohn(JohnsIntake.PivotStates pivotStates){
        return new MoveArmJohn(this.intake, pivotStates);
    }

    public Command moveGripper(JohnsIntake.GripperStates gripperStates){
        return new MoveGripper(this.intake, gripperStates);
    }

    public Command moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        return new MoveHorizontalSlidesEncoder(this.horizontalSlides,horizontalslidestates,target);
    }

    public Command fullExtendHorizontalSLides(){
        return new MultipleCommand(moveClipMag(ClipMech.ArmStates.READY), moveGripper(JohnsIntake.GripperStates.unclamp),
                new Delay(.1).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Fully_Out,245)));
//                                .addNext(moveArmJohn(JohnsIntake.ArmStates.forward)));
    }

    public Command clipClip(){
        return new MultipleCommand(moveClipMag(ClipMech.ArmStates.Out_The_Way),moveArmJohn(JohnsIntake.PivotStates.preauto_clip),new Delay(.1).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,190)).addNext(moveArmJohn(JohnsIntake.PivotStates.posauto_clip).addNext(moveGripper(JohnsIntake.GripperStates.unclamp))));
    }

    public Command extendHorizontalSLides(){
        return new MultipleCommand(moveClipMag(ClipMech.ArmStates.READY), moveGripper(JohnsIntake.GripperStates.unclamp)
                ,new Delay(.1).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.Half_Out,190)));
//                .addNext(moveArmJohn(JohnsIntake.ArmStates.forward)));
    }

    public Command extendHorizontalSlides_VerticalSlides(ClipMech.ArmStates clipstate, HorizontalSlides.HorizontalSlideStates horizontalstate, double target, JohnsIntake.PivotStates armstate){
        return new MultipleCommand(moveClipMag(clipstate),new Delay(.4).addNext(moveHorizontalSlides(horizontalstate,target)).addNext(moveArmJohn(armstate)));
    }

    public Command bringInHorizontalSLidesBetter(){
        return new MultipleCommand(moveGripper(JohnsIntake.GripperStates.clamp),new MoveHorizontalSlidesEncoder(this.horizontalSlides,HorizontalSlides.HorizontalSlideStates.Fully_In,170).addNext(moveClipMag(ClipMech.ArmStates.Out_The_Way)),moveArmJohn(JohnsIntake.PivotStates.parallel));
    }

    public Command moveClipMag(ClipMech.ArmStates armstates){
        return new MoveClipMech(clipmech,armstates);
    }

// Below are the Actions for RR we will use these actions to seamlessly flow into the command scheduler.


    public Action moveGripperWithRR(JohnsIntake.GripperStates gripperStates){
        return new SequentialAction(new MoveGripperAction(intake,gripperStates),new ResetGripperAction());
    }

    public Action movePivotWithRR(JohnsIntake.PivotStates pivotStates){
        return new SequentialAction(new MovePivotAction(intake,pivotStates),new ResetPivotAction());
    }

    public Action moveIntakeWithRR(JohnsIntake.IntakeStates intakeStates){
        return new SequentialAction(new MoveIntakeAction(intake,intakeStates),new ResetIntakeAction());
    }

    public Action moveHorizontalSlidesWithRR(HorizontalSlides.HorizontalSlideStates horizontalSlideStates,double target){
        return new MoveHorizontalSlidesAction(horizontalSlides,horizontalSlideStates,target);
    }
//
//    public Action moveIntakeWithRR(JohnsIntake.IntakeStates intakeStates){
//        return new SequentialAction(new MoveGripperAction(intake,intakeStates),new ResetGripperAction());
//    }

    public Action moveVerticalSlidesWithRR(double target){
        return new MoveVerticalSlidesWithRR(verticalslides, target);
    }




    public Command pullUp(){
        return new MoveHang(hang, foldpower, hangUp);
    }

    public Command pullDown(){
        return new MoveHang(hang, unfoldpower, handDown);
    }
}