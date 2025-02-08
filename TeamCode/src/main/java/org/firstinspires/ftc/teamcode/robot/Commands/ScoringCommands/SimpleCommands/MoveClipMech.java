package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.almostdownencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.clippity_clappity_clickity_clickencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.downencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.fully_up;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.hookclipencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.outthewayencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.readyencoderpos;
import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.target;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveClipMech extends Command {

    ClipMech clipmech;

    ClipMech.ArmStates armstates;

    public MoveClipMech(ClipMech clipmech, ClipMech.ArmStates armstates){
        this.clipmech = clipmech;
        this.armstates = armstates;
    }

    @Override
    public void init() {
        if (armstates == ClipMech.ArmStates.DOWN){
            target = downencoderpos;
       } else if (armstates == ClipMech.ArmStates.ALMOST_DOWN) {
            target = almostdownencoderpos;
        } else if (armstates == ClipMech.ArmStates.READY) {
            target = readyencoderpos;
        } else if (armstates == ClipMech.ArmStates.OUT_THE_WAYOFHORIZONTALSLIDES) {
            target = outthewayencoderpos;
        }else if (armstates == ClipMech.ArmStates.CLIPPITY_CLAPPITY_CLICKITY_CLICK) {
            target = clippity_clappity_clickity_clickencoderpos;
        }else if (armstates == ClipMech.ArmStates.HOOKCLIP) {
            target = hookclipencoderpos;
        }
        clipmech.setArmStates(armstates);
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
    }
}
