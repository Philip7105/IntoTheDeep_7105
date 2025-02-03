package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.ClipMech.ClipMech;

public class MoveIndex extends Command {

    ClipMech clipMech;

    ClipMech.LeftIndexState leftIndexState;

    ElapsedTime time = new ElapsedTime();

    public MoveIndex(ClipMech clipMech, ClipMech.LeftIndexState leftIndexState){
        this.clipMech = clipMech;
        this.leftIndexState = leftIndexState;
    }

    @Override
    public void init() {
//        clipMech.setLeftIndex(leftIndexState);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return time.seconds() > .4;
    }

    @Override
    public void shutdown() {

    }
}
