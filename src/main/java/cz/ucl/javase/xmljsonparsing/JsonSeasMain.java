package cz.ucl.javase.xmljsonparsing;


import javax.json.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class JsonSeasMain {

    private static List<String> seas = new ArrayList<>();

    public static void main(String[] arg) {

        try (FileReader fr = new FileReader(new File(JsonCountriesMain.class.getResource("/world_min.json").toURI()))) {
            JsonReader reader = Json.createReader(fr);

            JsonObject jsonObject = reader.readObject();
            reader.close();

            JsonObject world = (JsonObject) jsonObject.get("world");
            String indiaId = getIndiaCountryId(world);

            JsonArray seas = (JsonArray) ((JsonObject) world.get("seas")).get("sea");

            for (int i = 0; i < seas.size(); i++) {
                JsonObject sea = (JsonObject) seas.get(i);

                JsonArray locateds = (JsonArray) sea.get("located");
                if (locateds == null) { continue; }
                for (int j = 0; j < locateds.size(); j++) {
                    JsonObject located = (JsonObject) locateds.get(j);
                    String countryId = ((JsonString) located.get("-country")).getString();
                    if (indiaId.equals(countryId)) {
                        seas.add(sea.get("-seaName"));
                    }
                }
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

    private static String getIndiaCountryId(JsonObject world) {

        JsonArray countries = (JsonArray) ((JsonObject) world.get("countries")).get("country");
        for (int i = 0; i < countries.size(); i++) {
            JsonObject country = (JsonObject) countries.get(i);
            String name = ((JsonString) country.get("-countryName")).getString();
            if ("India".equals(name)) {
                return ((JsonString) country.get("-id")).getString();
            }
        }
        return null;
    }

    public static void printResult() {


        JsonObjectBuilder seasBuilder = Json.createObjectBuilder();
        JsonArrayBuilder seaArray = Json.createArrayBuilder();

        for (String c : seas) {
            JsonObject sea = Json.createObjectBuilder()
                    .add("seaName", c).build();
            seaArray.add(sea);
        }
        JsonObject seasJson = seasBuilder.add("seas", seaArray.build()).build();
        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("seas.json")) {
            JsonWriter writer = Json.createWriter(file);
            writer.writeObject(seasJson);
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
