package org.firstinspires.ftc.teamcode.Control;

public class PDCoefficients {

    public double Kp;
    public double Kd;

    /**
     * Standard PID coefficients
     * @param Kp proportional term, multiplied directly by the state error
     * @param Kd derivative term, multiplied directly by the state error rate of change.
     */
    public PDCoefficients(double Kp,double Kd) {
        this.Kp = Kp;
        this.Kd = Kd;
    }

}
