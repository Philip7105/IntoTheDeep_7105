
package org.firstinspires.ftc.teamcode.Opmode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TestTridentArm extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor TurnTridentArm;
        DcMotor ExtendTridentArm;
        CRServoImplEx IntakeOne;
        CRServoImplEx IntakeTwo;

        DcMotor LeftHang;
        DcMotor RightHang;

        Boolean isReverse = false;

        init();

        TurnTridentArm =hardwareMap.get(DcMotor.class, "TurnTridentArm");
        ExtendTridentArm = hardwareMap.get(DcMotor.class, "ExendTridentArm");
        IntakeOne = hardwareMap.get(CRServoImplEx.class, "IntakeLeft");
        IntakeTwo =hardwareMap.get(CRServoImplEx.class, "IntakeRight");
        IntakeTwo.setDirection(DcMotorSimple.Direction.REVERSE);
        LeftHang = hardwareMap.get(DcMotor.class, "LeftHang");
        RightHang=hardwareMap.get(DcMotor.class, "RightHang");

        waitForStart();
        while(opModeIsActive()){

            if (gamepad1.a){
                isReverse = true;
            }
            if (gamepad1.b){
                isReverse = false;
            }

            if (!isReverse){
                ExtendTridentArm.setDirection(DcMotorSimple.Direction.FORWARD);
                TurnTridentArm.setDirection(DcMotorSimple.Direction.FORWARD);
                ExtendTridentArm.setPower(gamepad1.left_trigger);
                TurnTridentArm.setPower(gamepad1.right_trigger);
            }
            if (isReverse){
                ExtendTridentArm.setDirection(DcMotorSimple.Direction.REVERSE);
                TurnTridentArm.setDirection(DcMotorSimple.Direction.REVERSE);
                ExtendTridentArm.setPower(gamepad1.left_trigger);
                TurnTridentArm.setPower(gamepad1.right_trigger);;
            }

            if (gamepad1.right_bumper){
                IntakeOne.setPower(0.75);
                IntakeTwo.setPower(0.75);
            }


            if (gamepad1.left_bumper){
                IntakeOne.setPower(-0.75);
                IntakeTwo.setPower(-0.75);
            }


        }

    }
}
