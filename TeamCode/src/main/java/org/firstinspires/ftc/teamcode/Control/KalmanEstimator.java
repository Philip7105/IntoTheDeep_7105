package org.firstinspires.ftc.teamcode.Control;

import com.ThermalEquilibrium.homeostasis.Filters.Estimators.Estimator;
import com.ThermalEquilibrium.homeostasis.Filters.FilterAlgorithms.KalmanFilter;

import java.util.function.DoubleSupplier;

public class KalmanEstimator extends RedoneEstimator {

    KalmanFilter kalmanFilter;

    public KalmanEstimator(double measurement, double Q, double R, int N) {
        super(measurement);
        kalmanFilter = new KalmanFilter(Q,R,N);
    }

    @Override
    public double update() {
        return kalmanFilter.estimate(measurement);
    }
}