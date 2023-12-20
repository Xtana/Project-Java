package org.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Satellite implements Comparable<Satellite> {
    private String name;
    private String id;
    private Date lastTime;
    private Double eccentricity;
    private Double meanMotion;
    private Double inclination;                 // i - наклонение (Rad)
    private Double longitudeOfAscendingNode;    // Ω - долгота восходящего узла
    private Double recenterArgument;            // ω - аргумент перецентра
    private Double meanAnomaly;                 // частота обращения
    private Double period;                      // T - период
    private Double semiMajorAxis;               // a - большая полуось

    private static final Double GRAVITATION_CONSTANT = 398_628.0;
    private static final Double PI = Math.PI;
    private static final Integer HOURS_IN_DAY = 24;
    private static final Integer MINUTES_IN_HOURS = 60;

    public Satellite() {
    }

    public Satellite(String name, String id, String lastTime,
                     Double meanMotion, Double eccentricity, Double inclination,
                     Double longitudeOfAscendingNode, Double recenterArgument, Double meanAnomaly) {
        this.name = name;
        this.id = id;
        this.lastTime = parseTime(lastTime);
        this.eccentricity = eccentricity;
        this.meanMotion = meanMotion;
        this.inclination = Math.toRadians(inclination);
        this.longitudeOfAscendingNode = Math.toRadians(longitudeOfAscendingNode);
        this.recenterArgument = recenterArgument;
        this.meanAnomaly = meanAnomaly;

        this.period = countPeriod();
        this.semiMajorAxis = countSemiMajorAxis();
    }

    private Date parseTime(String lastTimeStr) throws NullPointerException {
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            date = dateFormat.parse(lastTimeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private Double countSemiMajorAxis() {
        return Math.cbrt(Math.pow(period, 2) * GRAVITATION_CONSTANT / (4 * Math.pow(PI, 2)));
    }

    private Double countPeriod() {
        return HOURS_IN_DAY * MINUTES_IN_HOURS / meanMotion;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public Double getEccentricity() {
        return eccentricity;
    }

    public Double getMeanMotion() {
        return meanMotion;
    }

    public Double getInclination() {
        return inclination;
    }

    public Double getRecenterArgument() {
        return recenterArgument;
    }

    public Double getLongitudeOfAscendingNode() {
        return longitudeOfAscendingNode;
    }

    public Double getMeanAnomaly() {
        return meanAnomaly;
    }

    public Double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public Double getPeriod() {
        return period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Satellite satellite = (Satellite) o;
        return Objects.equals(name, satellite.name) && Objects.equals(lastTime, satellite.lastTime) && Objects.equals(eccentricity, satellite.eccentricity) && Objects.equals(meanMotion, satellite.meanMotion) && Objects.equals(inclination, satellite.inclination) && Objects.equals(longitudeOfAscendingNode, satellite.longitudeOfAscendingNode) && Objects.equals(recenterArgument, satellite.recenterArgument) && Objects.equals(meanAnomaly, satellite.meanAnomaly) && Objects.equals(period, satellite.period) && Objects.equals(semiMajorAxis, satellite.semiMajorAxis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastTime, eccentricity, meanMotion, inclination, longitudeOfAscendingNode, recenterArgument, meanAnomaly, period, semiMajorAxis);
    }

    @Override
    public int compareTo(Satellite satellite) {
        int res = name.compareTo(satellite.getName());
        if (res == 0) {
            res = id.compareTo(satellite.getId());
        }
        return res;
    }
}
