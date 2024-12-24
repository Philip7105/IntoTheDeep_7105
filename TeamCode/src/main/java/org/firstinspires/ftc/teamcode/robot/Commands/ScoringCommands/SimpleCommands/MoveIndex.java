package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveIndex extends Command {

    ClipMech clipMech;

    ClipMech.IndexState indexState;

    ElapsedTime time = new ElapsedTime();

    public MoveIndex(ClipMech clipMech, ClipMech.IndexState indexState){
        this.clipMech = clipMech;
        this.indexState = indexState;
    }

    @Override
    public void init() {
        clipMech.setIndex(indexState);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return time.seconds() > .1;
    }

    @Override
    public void shutdown() {
        clipMech.setIndex(ClipMech.IndexState.ZEROPOWER);
    }
}
