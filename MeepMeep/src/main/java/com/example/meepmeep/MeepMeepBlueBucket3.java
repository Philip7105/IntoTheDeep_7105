package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepBlueBucket3 extends MeepMeep {
    public MeepMeepBlueBucket3(int windowSize) {
        super(600);
    }

    public static void main(String[] args) {
        MeepMeepBlueBucket3 meepmeep = new MeepMeepBlueBucket3(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 30, Math.toRadians(180), Math.toRadians(180), 16.5)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(32, 62, Math.toRadians(0)))
//                .strafeToLinearHeading(new Vector2d(48.2,47), Math.toRadians(270))
//                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(57,55), Math.toRadians(45))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(58.2,47), Math.toRadians(270))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(57,55), Math.toRadians(225))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(56.2,47), Math.toRadians(305))
                .waitSeconds(1)
                .strafeToLinearHeading(new Vector2d(57,55), Math.toRadians(225))
                .waitSeconds(1)
                .build());

        meepmeep.setBackground(Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}