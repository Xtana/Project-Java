package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;


public class App extends Application {
    private Map<Satellite, ArrayList<Double>> date;

    public App() {
    }

    public App(Map<Satellite, ArrayList<Double>> date) {
        this.date = date;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("ΔΩ (rad)");
        xAxis.setLabel("time (min)");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Change in ΔΩ over time");

        stage.setScene(build(lineChart));
        stage.show();

    }

    private Scene build1(LineChart lineChart) {

        for (int i = 1; i < 3; i++) {

            XYChart.Series series = new XYChart.Series();
            series.setName("My portfolio " + i);

            for (int j = 1; j < 5; j++) {
                series.getData().add(new XYChart.Data(j, i));
            }

            lineChart.getData().add(series);

        }

        Scene scene = new Scene(lineChart, 800, 600);
        return scene;
    }

        private Scene build(LineChart lineChart) {

            Parser parser = new Parser();
            parser.getSatellites();
            HashSet<Satellite> satellites = parser.getFinalSatellites();

            for (Satellite satellite : satellites) {
                System.out.println(satellite.getName() + " " + satellite.getId());
            }

            FirstStageTransitionToOtherOrbits firstStage = new FirstStageTransitionToOtherOrbits(satellites);
            firstStage.OmegaWithTimeChange();
            date = firstStage.getDate();

        ArrayList<Double> timeArr = date.get(new Satellite());

        for (Satellite satellite : date.keySet()) {
            ArrayList<Double> omegaArr = date.get(satellite);

            XYChart.Series series = new XYChart.Series();
            series.setName(satellite.getName());

            for (int i = 0; i < timeArr.size(); i++) {
                series.getData().add(new XYChart.Data(timeArr.get(i), omegaArr.get(i)));
            }
            lineChart.getData().add(series);
        }

        Scene scene = new Scene(lineChart, 800, 600);
        return scene;
    }


    public void app() {
        launch();
    }
}