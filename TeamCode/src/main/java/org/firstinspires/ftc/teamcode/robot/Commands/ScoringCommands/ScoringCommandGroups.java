package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands;


import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.hookclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.preclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.prepselfclipencoderpos;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.DelayAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.EmptyAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.HoldVerticalSlidePosAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveClipMagAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveCoAxialAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveHorizontalSlidesAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeActionShortTime;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MovePivotAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetCoAxialAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetDelayAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetIntakeAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetPivotAction;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.Delay;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.HorizontalCommandsLowerTolerance;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveAxial;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveClipMagTimeBased;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MovePivot;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveClipMech;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveGripper;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveLeftHang;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveHorizontalSlidesEncoder;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveIntakeJohn;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MovePivotTimeBased;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveRightHang;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.MoveVerticalSlidesBetter;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveVerticalSlidesAction;
import org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands.NoSamples;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.VerticalSlides;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.JohnsIntake;
import org.firstinspires.ftc.teamcode.robot.Subsystems.Intake.NewIntake;

/** This contains a few of our robot's more frequently used commands.
 *
 */

public class ScoringCommandGroups {
    NewIntake intake;
    ClipMech clipmech;
    HorizontalSlides horizontalSlides;
    VerticalSlides verticalslides;
    JohnHanging hang;
    public ScoringCommandGroups(NewIntake intake, VerticalSlides verticalslides, HorizontalSlides horizontalslides, ClipMech clipmech, JohnHanging hanging) {
        this.intake = intake;
        this.verticalslides = verticalslides;
        this.horizontalSlides = horizontalslides;
        this.clipmech = clipmech;
        this.hang = hanging;
    }

    public Command initRobot(){
        return new MultipleCommand(movePivot(NewIntake.PivotStates.CHAMBERPOSBOTH),moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos));
    }

    public Command slidesTeleop(){
        return moveClipMag(ClipMech.ArmStates.READY).addNext(new MoveVerticalSlidesBetter(verticalslides,false,0,10));
    }

    public Command slidesSetPos(double target,double tolerance){
        return new MoveVerticalSlidesBetter(verticalslides,true,target,tolerance);
    }

    public Command armChamberPos(){
        return new MultipleCommand(movePivot(NewIntake.PivotStates.CHAMBERPOSBOTH));
    }

    public Command armOutFront(){
        return new MultipleCommand(movePivot(NewIntake.PivotStates.SHOVELPIVOTPOS),new MoveAxial(intake, NewIntake.CoaxialStates.RELEASE));
    }

    public Command movePivot(NewIntake.PivotStates pivotStates){
        return new MovePivot(this.intake, pivotStates);
    }

    public Command moveIntakeTime(NewIntake.IntakeStates intakeStates,double time){
        return new MoveIntakeJohn(this.intake, intakeStates,time);
    }
    public Command moveCoAxial(JohnsIntake.GripperStates gripperStates){
        return new MoveGripper(this.intake, gripperStates);
    }

    public Command moveCoAxial(NewIntake.CoaxialStates coaxialStates){
        return new MoveAxial(this.intake,coaxialStates);
    }

    public Command moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        return new MoveHorizontalSlidesEncoder(this.horizontalSlides,horizontalslidestates,target);
    }

    public Command moveHorizontalSlidesLowerTolerance(HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        return new HorizontalCommandsLowerTolerance(this.horizontalSlides,horizontalslidestates,target);
    }

    public Command fullExtendHorizontalSLides(){
        return new MultipleCommand(new NoSamples(), moveClipMag(ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_OUT,fullyOutEncoderPos)));
    }

    public Command prepSelfClip(){
        return new MultipleCommand(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.PREPSELFCLIP, prepselfclipencoderpos)
                ,movePivot(NewIntake.PivotStates.PRECLIP),new MoveAxial(intake, NewIntake.CoaxialStates.PRECLIP),
                moveClipMag(ClipMech.ArmStates.PRECLIP), moveIntakeTime(NewIntake.IntakeStates.STOP,.1));
    }
//0.565

    public Command clipClip2(){
        return moveHorizontalSlidesLowerTolerance(HorizontalSlides.HorizontalSlideStates.PRESELFCLIP,preclipencoderpos)
                .addNext(moveClipMag(ClipMech.ArmStates.PRECLIP2))
                .addNext(new Delay(.2))
                .addNext(moveClipMag(ClipMech.ArmStates.PRECLIP3))
                .addNext(new Delay(.2))
                .addNext(new MultipleCommand(movePivot(NewIntake.PivotStates.PRECLIP2),new MoveAxial(intake, NewIntake.CoaxialStates.CLAMP)))
                .addNext(new Delay(.2))
                .addNext(new HorizontalCommandsLowerTolerance(horizontalSlides,HorizontalSlides.HorizontalSlideStates.HOOKCLIP,hookclipencoderpos))
                .addNext(new MultipleCommand(new MoveClipMagTimeBased(clipmech,ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK),new MovePivotTimeBased(intake,NewIntake.PivotStates.SNAPCLIP)))
                .addNext(new MultipleCommand(new MoveClipMagTimeBased(clipmech,ClipMech.ArmStates.READY),new MovePivotTimeBased(intake,NewIntake.PivotStates.CHAMBERPOSBOTH)));
    }


    public Command extendHorizontalSLides(){
        return new MultipleCommand(new NoSamples()
                ,moveClipMag(ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos)));
    }

    public Command bringInHorizontalSLidesBetter(){
        return new MultipleCommand(moveIntakeTime(NewIntake.IntakeStates.STOP,.1),moveClipMag(ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos)), movePivot(NewIntake.PivotStates.PARALLEL), new MoveAxial(intake, NewIntake.CoaxialStates.CLAMP));
    }

    public Command moveClipMag(ClipMech.ArmStates armstates){
        return new MoveClipMech(clipmech,armstates);
    }

// Below are the Actions for RR we will use these actions to seamlessly flow into the command scheduler.

    public Action emptyAction(){
        return new EmptyAction(Dashboard.packet);
    }

    public Action delayAction(double time,Action action){
        return new SequentialAction(new DelayAction(time,Dashboard.packet), new ResetDelayAction(Dashboard.packet),action);
    }

    public Action moveClipMagAction(ClipMech.ArmStates armStates){
        return new MoveClipMagAction(clipmech,armStates,Dashboard.packet);
    }

    public Action movePivotAction(NewIntake.PivotStates pivotStates){
        return new SequentialAction(new MovePivotAction(intake,pivotStates,Dashboard.packet),new ResetPivotAction(Dashboard.packet));
    }

    public Action moveIntakeAction(NewIntake.IntakeStates intakeStates){
        return new SequentialAction(new MoveIntakeAction(intake,intakeStates,Dashboard.packet),new ResetIntakeAction(Dashboard.packet));
    }

    public Action moveIntakeActionForShortTime(NewIntake.IntakeStates intakeStates){
        return new SequentialAction(new MoveIntakeActionShortTime(intake,intakeStates,Dashboard.packet),new ResetIntakeAction(Dashboard.packet));
    }

    public Action moveHorizontalSlidesAction(HorizontalSlides.HorizontalSlideStates horizontalSlideStates, double target){
        return new MoveHorizontalSlidesAction(horizontalSlides,horizontalSlideStates,target,Dashboard.packet);
    }

    public Action moveVerticalSlidesAction(double target, double tolerance){
        return new SequentialAction(moveClipMagAction(ClipMech.ArmStates.READY),new MoveVerticalSlidesAction(verticalslides, target, tolerance,Dashboard.packet), new HoldVerticalSlidePosAction(verticalslides,Dashboard.packet));
    }

    public Action moveCoAxialAction(NewIntake.CoaxialStates coaxialStates){
        return new SequentialAction(new MoveCoAxialAction(this.intake,coaxialStates,Dashboard.packet),new ResetCoAxialAction(Dashboard.packet));
    }

    public Command moveHang(JohnHanging.LeftHangStates lHangState, JohnHanging.LeftHangStates lEndHangState, JohnHanging.RightHangStates rHangState, JohnHanging.RightHangStates rEndHangState, double ref){
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