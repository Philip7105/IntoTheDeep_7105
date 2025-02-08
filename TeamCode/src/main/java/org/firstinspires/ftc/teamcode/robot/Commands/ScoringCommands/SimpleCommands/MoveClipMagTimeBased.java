package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import static org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech.cliparmdone;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveClipMagTimeBased extends Command {

    ElapsedTime time = new ElapsedTime();

    ClipMech clipmech;

    ClipMech.ArmStates armstates;

    public MoveClipMagTimeBased(ClipMech clipmech, ClipMech.ArmStates armstates){
        this.clipmech = clipmech;
        this.armstates = armstates;
    }

    @Override
    public void init() {
        time.reset();
        clipmech.setArmStates(armstates);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return time.seconds() > .7;
//        return cliparmdone;
    }

    @Override
    public void shutdown() {
    }
}
