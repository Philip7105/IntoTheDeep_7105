package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands;


import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyInEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullyOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.halfOutEncoderPos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.hookclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.preclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.prepreclipencoderpos;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.CommandFrameWork.MultipleCommand;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.DelayAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.EmptyAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.HoldVerticalSlidePosAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveClipMagAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveHorizontalSlidesAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MoveIntakeActionShortTime;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.MovePivotAction;
import org.firstinspires.ftc.teamcode.robot.Commands.RoadRunnerActions.ResetClipMagAction;
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
    //    LinearOpMode opMode;
    public ScoringCommandGroups(NewIntake intake, VerticalSlides verticalslides, HorizontalSlides horizontalslides, ClipMech clipmech, JohnHanging hanging) {
        this.intake = intake;
        this.verticalslides = verticalslides;
        this.horizontalSlides = horizontalslides;
        this.clipmech = clipmech;
        this.hang = hanging;
//        this.opMode = opMode;
    }

    public Command initRobot(){
        return new MultipleCommand(moveGripper(JohnsIntake.GripperStates.CLAMP), movePivot(NewIntake.PivotStates.CHAMBERPOSBOTH),moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos));
    }

    public Command slidesTeleop(){
        return new MoveVerticalSlidesBetter(verticalslides,false,0);
    }

    public Command slidesSetPos(double target){
        return new MoveVerticalSlidesBetter(verticalslides,true,target);
    }

    public Command armChamberPos(){
        return new MultipleCommand(movePivot(NewIntake.PivotStates.CHAMBERPOSBOTH));
    }

    public Command armBasketPos(){
        return movePivot(NewIntake.PivotStates.BASKETPOS);
    }

    public Command armOutFront(){
        return new MultipleCommand(movePivot(NewIntake.PivotStates.FORWARD),new MoveAxial(intake, NewIntake.CoaxialStates.RELEASE));
    }

    public Command movePivot(NewIntake.PivotStates pivotStates){
        return new MovePivot(this.intake, pivotStates);
    }

    public Command moveIntake(NewIntake.IntakeStates intakeStates){
        return new MoveIntakeJohn(this.intake, intakeStates);
//        return null;
    }
    public Command moveGripper(JohnsIntake.GripperStates gripperStates){
        return new MoveGripper(this.intake, gripperStates);
    }

    public Command moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        return new MoveHorizontalSlidesEncoder(this.horizontalSlides,horizontalslidestates,target);
    }

    public Command moveHorizontalSlidesLowerTolerance(HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target){
        return new HorizontalCommandsLowerTolerance(this.horizontalSlides,horizontalslidestates,target);
    }

    public Command fullExtendHorizontalSLides(){
        return new MultipleCommand(moveGripper(JohnsIntake.GripperStates.CLAMP).addNext(moveGripper(JohnsIntake.GripperStates.UNCLAMP)),
                moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_OUT,fullyOutEncoderPos));
    }

    public Command clipClip(){
        return moveHorizontalSlidesLowerTolerance(HorizontalSlides.HorizontalSlideStates.PREPRESELFCLIP,prepreclipencoderpos)
                .addNext(new Delay(.1)).addNext(moveHorizontalSlidesLowerTolerance(HorizontalSlides.HorizontalSlideStates.PRESELFCLIP,preclipencoderpos))
//                .addNext(new Delay(.2)).addNext(moveHorizontalSlidesLowerTolerance(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos))))
//                ,movePivot(JohnsIntake.PivotStates.PRECLIP)))
                .addNext(new Delay(.25))
                .addNext(moveClipMag(ClipMech.ArmStates.READY))
                .addNext(new Delay(.2))
                .addNext(movePivot(NewIntake.PivotStates.HOOKCLIP))
                .addNext(moveClipMag(ClipMech.ArmStates.HOOKCLIP))
//                .addNext(new Delay(.3).addNext(moveHorizontalSlidesLowerTolerance(HorizontalSlides.HorizontalSlideStates.HOOKCLIP,hookclipencoderpos)))
//                .addNext(new Delay(.2))
                .addNext(new MultipleCommand(new MovePivotTimeBased(intake,NewIntake.PivotStates.PRECLIP),new MoveClipMagTimeBased(clipmech,ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK)));
//                .addNext(new MultipleCommand(movePivot(NewIntake.PivotStates.CHAMBERPOS),moveClipMag(ClipMech.ArmStates.ALMOST_DOWN),moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullin)));
//                .addNext(new MultipleCommand(movePivot(JohnsIntake.PivotStates.CHAMBERPOS),moveClipMag(ClipMech.ArmStates.READY)));
    }

    public Command extendHorizontalSLides(){
        return new MultipleCommand(moveGripper(JohnsIntake.GripperStates.UNCLAMP)
                ,new Delay(.1).addNext(moveHorizontalSlides(HorizontalSlides.HorizontalSlideStates.HALF_OUT,halfOutEncoderPos)));
    }

    public Command bringInHorizontalSLidesBetter(){
        return new MultipleCommand(new MoveHorizontalSlidesEncoder(this.horizontalSlides,HorizontalSlides.HorizontalSlideStates.FULLY_IN,fullyInEncoderPos), movePivot(NewIntake.PivotStates.PARALLEL), new MoveAxial(intake, NewIntake.CoaxialStates.CLAMP));
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
        return new SequentialAction(new MoveClipMagAction(clipmech,armStates,Dashboard.packet),new ResetClipMagAction(Dashboard.packet));
//        return null;
    }

    public Action movePivotAction(NewIntake.PivotStates pivotStates){
        return new SequentialAction(new MovePivotAction(intake,pivotStates,Dashboard.packet),new ResetPivotAction(Dashboard.packet));
//        return null;
    }

    public Action moveIntakeAction(NewIntake.IntakeStates intakeStates){
        return new SequentialAction(new MoveIntakeAction(intake,intakeStates,Dashboard.packet),new ResetIntakeAction(Dashboard.packet));
//        return null;
    }

    public Action moveIntakeActionForShortTime(NewIntake.IntakeStates intakeStates){
        return new SequentialAction(new MoveIntakeActionShortTime(intake,intakeStates,Dashboard.packet),new ResetIntakeAction(Dashboard.packet));
//        return null;
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

//    public Command pullUp(){
//        return new MoveHang(hang, foldpower, readyFirstHang);
//    }
//
//    public Command pullDown(){
//        return new MoveHang(hang, unfoldpower, firstHang);
//    }
}