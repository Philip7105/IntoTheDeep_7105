package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.LynxConstants;

import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.BetterSubsystems;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.NewRR.PinpointDrive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Config
public class Robit {
    public DcMotorEx leftHang, rightHang,leftslide,rightslide;

    public Drivetrain drivetrain;

    public Intake intake;

    private HardwareMap hardwareMap;

    private static Robit instance = null;

    public List<LynxModule> modules;
    public LynxModule CONTROL_HUB;


    private ArrayList<BetterSubsystems> subsystems;

    public static Robit getInstance() {
        if (instance == null) {
            instance = new Robit();
        }
//        instance = true;
        return instance;
    }

    /**
     * Created at the start of every OpMode.
     *
     * @param hardwareMap The HardwareMap of the robot, storing all hardware devices
     */
    public void init(final HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;

        // UWUXTENSION
        leftHang = hardwareMap.get(DcMotorEx.class, "lefthang");
        rightHang = hardwareMap.get(DcMotorEx.class, "righthang");
        leftslide = hardwareMap.get(DcMotorEx.class, "leftslide");
        rightslide = hardwareMap.get(DcMotorEx.class, "rightslide");

        rightHang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftHang.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightHang.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftHang.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftHang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightHang.setDirection(DcMotorSimple.Direction.REVERSE);


//        intakePivotActuator

//        this.rightHang.setDirection(DcMotorSimple.Direction.REVERSE);

        modules = hardwareMap.getAll(LynxModule.class);

        for (LynxModule m : modules) {
            m.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
            if (m.isParent() && LynxConstants.isEmbeddedSerialNumber(m.getSerialNumber())) CONTROL_HUB = m;
        }


        subsystems = new ArrayList<>();
        drivetrain = new Drivetrain(hardwareMap);
        intake = new Intake(hardwareMap);
//        if (Globals.IS_AUTO) {
//
//        } else {
//            drone = new DroneSubsystem();
//            hang = new HangSubsystem();
//        }
    }

    public void periodic() {
        intake.periodic();
        drivetrain.periodic();

    }

    public void clearBulkCache() {
        CONTROL_HUB.clearBulkCache();
    }

    public void addSubsystem(BetterSubsystems... subsystems) {
        this.subsystems.addAll(Arrays.asList(subsystems));
    }

    public void kill() {
        instance = null;
    }
}