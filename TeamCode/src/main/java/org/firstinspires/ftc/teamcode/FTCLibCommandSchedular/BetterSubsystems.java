package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SubsystemBase;

public abstract class BetterSubsystems extends SubsystemBase {
//    SubsystemBase base;
    public abstract void init();
    public abstract void periodic();
    public abstract void shutdown();

    public BetterSubsystems() {
        CommandScheduler.getInstance().registerSubsystem(this);
    }

}
