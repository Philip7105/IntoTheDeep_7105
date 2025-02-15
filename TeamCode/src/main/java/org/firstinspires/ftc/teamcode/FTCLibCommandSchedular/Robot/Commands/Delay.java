//package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Commands;
//
//import com.arcrobotics.ftclib.command.CommandBase;
//import com.arcrobotics.ftclib.command.InstantCommand;
//import com.qualcomm.robotcore.util.ElapsedTime;
//
//import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Robit;
//
//public class Delay extends CommandBase {
//    double delay;
//    ElapsedTime time = new ElapsedTime();
//    public Delay(double delay){
//        this.delay = delay;
//    }
//
//    @Override
//    public void initialize() {
//        time.reset();
//    }
//
//    @Override
//    public boolean isFinished() {
//        return time.seconds() > delay;
//    }
//}
