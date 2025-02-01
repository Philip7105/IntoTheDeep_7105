package org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.configuration.LynxConstants;

import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.BetterSubsystems;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.ClipMech;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.Dashboard;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.HorizontalSlides;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.JohnHanging;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.Limelight;
import org.firstinspires.ftc.teamcode.FTCLibCommandSchedular.Robot.Subsystems.VerticalSlides;
import org.firstinspires.ftc.teamcode.NewRR.PinpointDrive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Config
public class Robit {
    public Drivetrain drivetrain;
    public HorizontalSlides horizontalSlides;
    public VerticalSlides verticalSlides;
    public ClipMech clipMech;
    public JohnHanging johnHanging;
    public Intake intake;
    public Limelight limelight;
    public Dashboard dashboard;

    CommandScheduler commandScheduler;

    private HardwareMap hardwareMap;

    private static Robit instance = null;

    List<LynxModule> allHubs;

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

        allHubs= hardwareMap.getAll(LynxModule.class);

        for (LynxModule hub : allHubs) {
            hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }

        subsystems = new ArrayList<>();
        verticalSlides = new VerticalSlides(hardwareMap);
        horizontalSlides = new HorizontalSlides(hardwareMap);
        drivetrain = new Drivetrain(hardwareMap);
        clipMech = new ClipMech(hardwareMap);
        johnHanging = new JohnHanging(hardwareMap);
        limelight = new Limelight(drivetrain,hardwareMap);
        intake = new Intake(hardwareMap);
        dashboard = new Dashboard(drivetrain.mecanumDrive);
//        this.rightHang.setDirection(DcMotorSimple.Direction.REVERSE);
//        if (Globals.IS_AUTO) {

//        } else {
//            drone = new DroneSubsystem();
//            hang = new HangSubsystem();
//        }
    }

    public void periodic() {
        drivetrain.periodic();
        horizontalSlides.periodic();
        verticalSlides.periodic();
        clipMech.periodic();
        johnHanging.periodic();
        intake.periodic();
        limelight.periodic();
        dashboard.periodic();
    }

    public void clearBulkCache() {
        for (LynxModule hub : allHubs) {
            hub.clearBulkCache();
        }
    }

    public void addSubsystem(BetterSubsystems... subsystems) {
        this.subsystems.addAll(Arrays.asList(subsystems));
    }

    public void kill() {
        instance = null;
    }
}