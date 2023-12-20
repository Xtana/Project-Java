package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class Test extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public void test() {
        launch();
    }
}

//package org.example;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.Map;
//
//
//public class App extends Application {
//    private Map<Satellite, ArrayList<Double>> date;
//
//    public App(Map<Satellite, ArrayList<Double>> date) {
//        this.date = date;
//    }
//
//    @Override
//    public void start(Stage stage) {
//        stage.setTitle("Line Chart Sample");
//        final NumberAxis xAxis = new NumberAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("ΔΩ (rad)");
//        xAxis.setLabel("time (min)");
//        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
//
//        lineChart.setTitle("Change in ΔΩ over time");
//
//        stage.setScene(build(lineChart));
//        stage.show();
//
//    }
//
//    private Scene build(LineChart lineChart) {
//        ArrayList<Double> timeArr = date.get(new Satellite());
//
//        for (Satellite satellite : date.keySet()) {
//            ArrayList<Double> omegaArr = date.get(satellite);
//
//            XYChart.Series series = new XYChart.Series();
//            series.setName(satellite.getName());
//
//            for (int i = 0; i < timeArr.size(); i++) {
//                series.getData().add(new XYChart.Data(timeArr.get(i), omegaArr.get(i)));
//            }
//            lineChart.getData().add(series);
//        }
//
//        Scene scene = new Scene(lineChart, 800, 600);
//        return scene;
//    }
//
//
//    public void app() {
//        launch();
//    }
//}