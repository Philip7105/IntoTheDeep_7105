package org.firstinspires.ftc.teamcode.robot.Commands.DrivetrainCommands;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;
import org.firstinspires.ftc.teamcode.robot.Subsystems.HangingMechanism.JohnHanging;

public class MovePTOServos extends Command {
    JohnHanging johnHanging;
    ElapsedTime timer = new ElapsedTime();
    public MovePTOServos(JohnHanging johnHanging){
        this.johnHanging = johnHanging;
    }

    @Override
    public void init(){
        timer.reset();
        johnHanging.setPTO(JohnHanging.PTOSTATES.ENGAGE);
    }

    @Override
    public void periodic() {

    }

    @Override
    public boolean completed() {
        return timer.seconds() > .8;
    }

    @Override
    public void shutdown() {

    }
}
