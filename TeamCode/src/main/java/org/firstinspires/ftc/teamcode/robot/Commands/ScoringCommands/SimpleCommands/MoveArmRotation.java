package org.firstinspires.ftc.teamcode.robot.Commands.ScoringCommands.SimpleCommands;

import org.firstinspires.ftc.teamcode.CommandFrameWork.Command;

public class MoveArmRotation extends Command {

    ArmRotation armRotation;
    ArmRotation.ArmRotationStates armRotationStates;
    public MoveArmRotation(ArmRotation armRotation, ArmRotation.ArmRotationStates armRotationStates){
        this.armRotation = armRotation;
        this.armRotationStates = armRotationStates;
    }

    @Override
    public void init() {
        armRotation.setArmRotateStates(armRotationStates);
    }

    @Override
    public void periodic() {
        armRotation.setArmRotateStates(armRotationStates);
    }

    @Override
    public boolean completed() {
        return armRotation.getArmRotateError() < 15;
    }

    @Override
    public void shutdown() {

    }
}
