package org.firstinspires.ftc.teamcode.Control;


import com.ThermalEquilibrium.homeostasis.Controllers.Feedback.FeedbackController;
import com.ThermalEquilibrium.homeostasis.Parameters.PIDCoefficients;
import com.ThermalEquilibrium.homeostasis.Utils.Timer;

public class PDController implements FeedbackController {
    PDCoefficients coefficients;
    protected boolean hasRun = false;
    protected Timer timer = new Timer();
    protected double previousError = 0;
    protected double derivative = 0;
    public PDController(PDCoefficients coefficients) {
        this.coefficients = coefficients;
    }
    /**
     * calculate PID output
     * @param reference the target position
     * @param state current system state
     * @return PID output
     */
    @Override
    public double calculate(double reference, double state) {
        double dt = getDT();
        double error = calculateError(reference, state);
        double derivative = calculateDerivative(error,dt);
        previousError = error;
        return error * coefficients.Kp
                + derivative * coefficients.Kd;
    }

    /**
     * get the time constant
     * @return time constant
     */
    public double getDT() {
        if (!hasRun) {
            hasRun = true;
            timer.reset();
        }
        double dt = timer.currentTime();
        timer.reset();
        return dt;
    }
    protected double calculateError(double reference, double state) {
        return reference - state;
    }
    protected double calculateDerivative(double error, double dt) {
        derivative = (error - previousError) / dt;
        return derivative;
    }

}
