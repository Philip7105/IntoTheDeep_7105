package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.almostdownencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.backbeforeclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.clippity_clappity_clickity_clickencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.downencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.hookclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.preclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.outthewayencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.readyencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.target;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.DepositingMechanisms.HorizontalSlides.fullin;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveClipMech extends Command {

    ClipMech clipmech;
    boolean underneathslides = false;
    ClipMech.ArmStates armstates;
    double horizontalservopos;
    public MoveClipMech(ClipMech clipmech, ClipMech.ArmStates armstates, double horizontalservopos){
        this.clipmech = clipmech;
        this.armstates = armstates;
        this.horizontalservopos = horizontalservopos;
    }

    @Override
    public void init() {
        if (armstates == ClipMech.ArmStates.DOWN){
            target = downencoderpos;
       } else if (armstates == ClipMech.ArmStates.ALMOST_DOWN) {
            target = almostdownencoderpos;
        } else if (armstates == ClipMech.ArmStates.READY) {
            target = readyencoderpos;
        } else if (armstates == ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES && clipmech.getClipMagPos() > 200 && horizontalservopos != fullin ) {
            underneathslides = true;
            target = almostdownencoderpos;
            clipmech.setArmStates(ClipMech.ArmStates.ALMOST_DOWN);
        } else if (armstates == ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES) {
            target = outthewayencoderpos;
        }else if (armstates == ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK) {
            target = clippity_clappity_clickity_clickencoderpos;
        }else if (armstates == ClipMech.ArmStates.PRECLIP) {
            target = preclipencoderpos;
        } else if (armstates == ClipMech.ArmStates.HOOKCLIP) {
            target = hookclipencoderpos;
        } else if (armstates == ClipMech.ArmStates.BACKBEFORECLIP) {
            target = backbeforeclipencoderpos;
        } else if (armstates == ClipMech.ArmStates.PRECLIP2) {
            target = 116;
        } else if (armstates == ClipMech.ArmStates.PRECLIP3) {
            target = 160;
        }
        if (!underneathslides) {
            clipmech.setArmStates(armstates);
        }
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
//        return time.seconds() > .7;
        return Math.abs(target - clipmech.getClipMagPos()) < 4;
    }

    @Override
    public void shutdown() {
        underneathslides = false;
    }
}
