package com.example.meepmeep;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepBlueClipSide4PathTest extends MeepMeep {
    public MeepMeepBlueClipSide4PathTest(int windowSize) {super( 600);}

    public static void main(String[] args) {
        MeepMeepBlueClipSide4PathTest meepmeep = new MeepMeepBlueClipSide4PathTest(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(70, 60, Math.toRadians(180), Math.toRadians(180), 16.5)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-9.5, 63, Math.toRadians(90)))
                .strafeToLinearHeading(new Vector2d(-10,33),Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-35, 34.5),Math.toRadians(160))
                .strafeToLinearHeading(new Vector2d(-42,18), Math.toRadians(160))
//                .strafeToLinearHeading(new Vector2d(-49, 10), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-49, 53), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-50, 10), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-57, 10), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-57, 53), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-57, 10), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-63, 10), Math.toRadians(90))
                .strafeToLinearHeading(new Vector2d(-63, 53), Math.toRadians(90))
                // push in the second blue sample
//                .splineToConstantHeading(new Vector2d(-60, 10), Math.toRadians(90))
//                .strafeToLinearHeading(new Vector2d(-61, 10), Math.toRadians(180))
//                .strafeToLinearHeading(new Vector2d(-61, 55), Math.toRadians(180))
                // push in the third blue sample
//                .strafeToLinearHeading(new Vector2d(-52, 30), Math.toRadians(90))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(-52, 55), Math.toRadians(90))
                // pick up a blue specimen
//                .strafeToLinearHeading(new Vector2d(-8,33),Math.toRadians(90))
                // place a blue specimen
//                .strafeToLinearHeading(new Vector2d(-52, 40), Math.toRadians(90))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(-52, 55), Math.toRadians(90))
                // pick up a blue specimen
//                .strafeToLinearHeading(new Vector2d(-6,33),Math.toRadians(90))
                // place a blue specimen
//                .strafeToLinearHeading(new Vector2d(-52, 40), Math.toRadians(90))
//                .waitSeconds(1)
//                .strafeToLinearHeading(new Vector2d(-52, 55), Math.toRadians(90))
                // pick up a blue specimen
//                .strafeToLinearHeading(new Vector2d(-4,33),Math.toRadians(90))
                // place a blue specimen
//                .strafeToLinearHeading(new Vector2d(-54, 55), Math.toRadians(90))
                .build());

        meepmeep.setBackground(Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
