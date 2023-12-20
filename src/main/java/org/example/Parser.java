package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Parser {

    private static ArrayList<String> groups = new ArrayList<>();
    private static LinkedHashSet<Satellite> finalSatellites = new LinkedHashSet<>();

    private static final String URL1 = "https://celestrak.org/NORAD/elements/index.php?FORMAT=json-pretty";
    private static final String URL2 = "https://celestrak.org/NORAD/elements/";

    public void getSatellites() {

        try {
            Document doc = Jsoup.connect(URL1).ignoreContentType(true).get();
            Elements satellitesGroups = doc.getElementsByAttributeValue("title", "JSON-PRETTY Data");
            satellitesGroups.forEach(satellitesGroup -> groups.add(URL2 + satellitesGroup.attr("href")));

            for (int i = 5; i < 18; i++) { //groups.size(); i++) {
                if (i == 3 || i == 26) {
                    continue;
                }
                doc = Jsoup.connect(groups.get(i)).ignoreContentType(true).get();
                Elements satellites = doc.getElementsByTag("body");

                System.out.println(i + "==========");

                JSONParser parser = new JSONParser();
                JSONArray jsonData = (JSONArray) parser.parse(satellites.text());
                parseSatellites(jsonData);
            }
            //System.out.println("\n\n");

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void parseSatellites(JSONArray satellitesArr) {
        satellitesArr.forEach(satellitesObj -> {
            JSONObject satellitJsonObject = (JSONObject) satellitesObj;
            Satellite satellite = new Satellite(
                    (String) satellitJsonObject.get("OBJECT_NAME"),
                    (String) satellitJsonObject.get("OBJECT_ID"),
                    (String) satellitJsonObject.get("EPOCH"),
                    Double.parseDouble(satellitJsonObject.get("MEAN_MOTION").toString()),
                    Double.parseDouble(satellitJsonObject.get("ECCENTRICITY").toString()),
                    Double.parseDouble(satellitJsonObject.get("INCLINATION").toString()),
                    Double.parseDouble(satellitJsonObject.get("RA_OF_ASC_NODE").toString()),
                    Double.parseDouble(satellitJsonObject.get("ARG_OF_PERICENTER").toString()),
                    Double.parseDouble(satellitJsonObject.get("MEAN_ANOMALY").toString())
            );
            if ((satellite.getEccentricity() <= 0.0001) && (finalSatellites.size() == 0 ||
                    Math.abs(satellite.getInclination() - finalSatellites.iterator().next().getInclination()) <= 0.1)) {
                finalSatellites.add(satellite);
                System.out.println(satellite.getName() + " " + satellite.getId());
            }
        });
    }

    public LinkedHashSet<Satellite> getFinalSatellites() {
        return finalSatellites;
    }
}
