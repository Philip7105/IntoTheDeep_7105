package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.ProfileParams;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilderParams;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting extends MeepMeep {
    public MeepMeepTesting(int windowSize) {
        super(600);
    }

    public static void main(String[] args) {
        MeepMeepTesting meepmeep = new MeepMeepTesting(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 30, Math.toRadians(180), Math.toRadians(180), 16.5)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(13, -63, Math.toRadians(270)))
                .strafeToLinearHeading(new Vector2d(10,-33),Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(18,-41),Math.toRadians(270))
                .turnTo(Math.toRadians(40))
                .strafeToLinearHeading(new Vector2d(36,-37),Math.toRadians(40))
                .strafeToLinearHeading(new Vector2d(39,-43),Math.toRadians(290))
                .turnTo(Math.toRadians(40))
                .strafeToLinearHeading(new Vector2d(45,-36),Math.toRadians(40))
                .strafeToLinearHeading(new Vector2d(47,-47),Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(10,-47),Math.toRadians(270))
                .strafeToLinearHeading(new Vector2d(10,-33),Math.toRadians(270))
                .build());

        meepmeep.setBackground(Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}