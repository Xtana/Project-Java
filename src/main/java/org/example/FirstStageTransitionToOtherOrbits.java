package org.example;

import java.util.*;

public class FirstStageTransitionToOtherOrbits {
    private final HashSet<Satellite> satellites;
    private Map<Satellite, ArrayList<Double>> date = new HashMap<>();

    private final static double PI = Math.PI;
    private final static double FORE_YEAR = 4 * 12 * 30;// * 24;// * 60;

    private final static double GRAVITATION_CONSTANT = 398_628;
    private final static double J2 = 0.0010827;
    private final static double Re = 6378.16;
    private final static double EPSILON = 2.0 / 3.0 * J2 * GRAVITATION_CONSTANT * Math.pow(Re, 2);


    public FirstStageTransitionToOtherOrbits(HashSet<Satellite> satellites) {
        this.satellites = satellites;
    }

    public void OmegaWithTimeChange() {
        //double minA = findMinSemiMajorAxis();
        ArrayList<Double> timeList = new ArrayList<>();
        for (int t = 1; t <= FORE_YEAR; t++) {
            timeList.add(Double.valueOf(t));
        }
        date.put(new Satellite(), timeList);

        for (Satellite satellite : satellites) {
            ArrayList<Double> omegaList = new ArrayList<>();
            double deltaOmegaI = satellite.getLongitudeOfAscendingNode();
            for (int t = 1; t <= FORE_YEAR; t++) {
                deltaOmegaI = deltaOmegaI + (countDeltaOmegaDoth(satellite) -
                        countDeltaOmegaDothO(satellite)) * t;
                omegaList.add(deltaOmegaI);
            }
            date.put(satellite, omegaList);
        }
    }

    private double countDeltaOmegaDoth(Satellite satellite) {
        Double deltaOmegaIntermediate = countDeltaOmegaIntermediate(satellite);
        return deltaOmegaIntermediate / satellite.getPeriod();    // δΩi с точкой
    }

    private double countDeltaOmegaIntermediate(Satellite satellite) {
        return -(2 * PI * EPSILON) / (GRAVITATION_CONSTANT * Math.pow(satellite.getSemiMajorAxis(), 2)) * Math.cos(satellite.getInclination());    // δΩ
    }

    private double countDeltaOmegaDothO(Satellite satellite) {
        return  countDeltaOmegaDoth(satellite) / satellite.getInclination();   // δΩo с точкой
    }

    public Map<Satellite, ArrayList<Double>> getDate() {
        return date;
    }
}
