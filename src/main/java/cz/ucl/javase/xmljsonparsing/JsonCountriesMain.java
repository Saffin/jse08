package cz.ucl.javase.xmljsonparsing;

import javax.json.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JsonCountriesMain {

    private static Map<String, String> countriesById = new HashMap<>();
    private static Set<String> countriesInOrganization = new HashSet<>();

    public static void main(String[] arg) {

        try (FileReader fr = new FileReader(new File(JsonCountriesMain.class.getResource("/world_min.json").toURI()))) {
            JsonReader reader = Json.createReader(fr);

            JsonObject jsonObject = reader.readObject();
            reader.close();

            JsonObject world = (JsonObject) jsonObject.get("world");
            JsonArray countries = (JsonArray) ((JsonObject) world.get("countries")).get("country");
            for (int i = 0; i < countries.size(); i++) {
                JsonObject country = (JsonObject) countries.get(i);
                String name =  ((JsonString) country.get("-countryName")).getString();
                String id = ((JsonString) country.get("-id")).getString();

                countriesById.put(id, name);
            }

            JsonArray organizations = (JsonArray) ((JsonObject) world.get("organizations")).get("organization");

            for (int i = 0; i < organizations.size(); i++) {
                JsonObject organization = (JsonObject) organizations.get(i);
                String name = ((JsonString) organization.get("-organizationName")).getString();
                if (!"Food and Agriculture Organization".equals(name)) {
                    continue;
                }
                JsonArray members = (JsonArray) organization.get("members");
                for (int j = 0; j < members.size(); j++) {
                    JsonObject member = (JsonObject) members.get(j);
                    String countryId = ((JsonString) member.get("-country")).getString();
                    countriesInOrganization.add(countriesById.get(countryId));
                }
                break;

            }

            printResult();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


    }

    public static void printResult() {

        JsonObjectBuilder countries = Json.createObjectBuilder();
        JsonArrayBuilder countryArray = Json.createArrayBuilder();

        for (String c : countriesInOrganization) {
            JsonObject country = Json.createObjectBuilder().
            add("country", c).build();
            countryArray.add(country);
        }

        JsonObject countriesJson = countries.add("countries", countryArray.build()).build();
        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("countries.json")) {
            JsonWriter writer = Json.createWriter(file);
            writer.writeObject(countriesJson);
            writer.close();

            System.out.println("Successfully Copied Json Object to File...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static class Mountain {
        String name;
        Long height;

        public Mountain(String name, Long height) {
            this.name = name;
            this.height = height;
        }
    }
}
