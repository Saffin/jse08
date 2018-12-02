package cz.ucl.javase.xmljsonparsing;


import javax.json.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class JsonMountainsMain {

    private static List<Mountain> mountainsList = new ArrayList<>();

    public static void main(String[] arg) {

        try (FileReader fr = new FileReader(new File(JsonCountriesMain.class.getResource("/world_min.json").toURI()))) {
            JsonReader reader = Json.createReader(fr);

            JsonObject jsonObject = reader.readObject();
            reader.close();

            JsonArray mountains = (JsonArray) ((JsonObject) ((JsonObject) jsonObject.get("world")).get("mountains")).get("mountain");
            for (int i = 0; i < mountains.size(); i++) {
                JsonObject mountain = (JsonObject) mountains.get(i);
                String name =  ((JsonString) mountain.get("-mountainName")).getString();
                Long height = new Long(((JsonString) mountain.get("-height")).getString());

                mountainsList.add(new Mountain(name, height));
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

        JsonObjectBuilder mountains = Json.createObjectBuilder();
        JsonArrayBuilder mountainArray = Json.createArrayBuilder();

        for (Mountain m : mountainsList) {
            JsonObject mountain = Json.createObjectBuilder()
                    .add("mountainName", m.name)
                    .add("height", m.height).build();
            mountainArray.add(mountain);
        }
        JsonObject mountainsJson = mountains.add("mountains", mountainArray.build()).build();
        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("mountains.json")) {
            JsonWriter writer = Json.createWriter(file);
            writer.writeObject(mountainsJson);
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
