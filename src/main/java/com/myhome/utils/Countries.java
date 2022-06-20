package com.myhome.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Countries {

    private String pathToJsonFile = Paths.get(".").toAbsolutePath().toString();

    public Countries() {
        pathToJsonFile = pathToJsonFile.substring(0, pathToJsonFile.length() - 1) + "src/main/resources/static/json/countries.json";
    }

    public String getCountryNameByDialCodeFromJSON(String dialCode) {

        String countryName = null;

        try {
            Object obj = new JSONParser().parse(new FileReader(pathToJsonFile));
            JSONArray countriesJson = (JSONArray) obj;
            Iterator iterator = countriesJson.iterator();
            while (iterator.hasNext()) {
                JSONObject countryJson = (JSONObject) iterator.next();
                if (((String) countryJson.get("dial_code")).equals(dialCode)) {
                    countryName = (String) countryJson.get("name");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return countryName;
    }

    public List<String> loadCountryNamesFromJSON() {

        List<String> countries = new ArrayList<>();

        try {
            Object obj = new JSONParser().parse(new FileReader(pathToJsonFile));
            JSONArray countriesJson = (JSONArray) obj;
            Iterator iterator = countriesJson.iterator();
            while (iterator.hasNext()) {
                JSONObject countryJson = (JSONObject) iterator.next();
                countries.add((String) countryJson.get("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return countries;
    }

}
