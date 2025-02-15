package org.firstinspires.ftc.teamcode.Control;

import java.util.function.DoubleSupplier;

public abstract class RedoneEstimator {

    public double measurement;

    /**
     * Set up Double Supplier for recurring measurement obtainment.
     * @param measurement measurement we want to obtain.
     */
    public RedoneEstimator(double measurement) {
        this.measurement = measurement;
    }

    /**
     * Run estimation algorithm
     * @return system state as determined by estimation algorithm.
     */
    public abstract double update();

}
