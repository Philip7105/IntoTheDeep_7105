package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveClipMech extends Command {

    ElapsedTime time = new ElapsedTime();

    ClipMech clipmech;

    ClipMech.ArmStates armstates;

    public MoveClipMech(ClipMech clipmech, ClipMech.ArmStates armstates){
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
    }

    @Override
    public void shutdown() {

    }
}
