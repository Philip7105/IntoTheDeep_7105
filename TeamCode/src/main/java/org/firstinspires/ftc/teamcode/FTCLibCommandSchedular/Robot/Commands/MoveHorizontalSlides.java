package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.HorizontalSlides;

public class MoveHorizontalSlides extends CommandBase {

    HorizontalSlides horizontalSlides;

    HorizontalSlides.HorizontalSlideStates horizontalslidestates;

    double target;

    public MoveHorizontalSlides(HorizontalSlides horizontalSlides,HorizontalSlides.HorizontalSlideStates horizontalslidestates, double target) {
        this.horizontalSlides = horizontalSlides;
        this.horizontalslidestates = horizontalslidestates;
        this.target = target;
    }

    @Override
    public void initialize() {
        horizontalSlides.setHorizontalSlides(horizontalslidestates);
    }

    @Override
    public boolean isFinished() {
        return Math.abs(target - horizontalSlides.getSlidePos()) <= 8;
    }
}