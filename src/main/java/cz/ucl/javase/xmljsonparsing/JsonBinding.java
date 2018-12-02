package cz.ucl.javase.xmljsonparsing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ucl.javase.xmljsonparsing.json.City;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonBinding {

    public static void main (String[] args) {
        writeJson();
        readJson();
    }

    static void writeJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<City> cities = new ArrayList<>();
            City city = new City();
            city.setCityName("Praha");
            city.setPopulation(1200000);
            cities.add(city);

            city = new City();
            city.setCityName("Brno");
            city.setPopulation(400000);
            cities.add(city);

            mapper.writeValue(new File("cities.json"), cities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     static void readJson() {
         ObjectMapper mapper = new ObjectMapper();
         try {
             List<City> cities = mapper.readValue(new File("cities.json"),
                     new TypeReference<List<City>>() {});
             cities.size();

             for (City city: cities) {
                 System.out.println(city.getCityName() + ":" + city.getPopulation());
             }

         } catch (IOException e) {
             e.printStackTrace();
         }
     }




}
