package org.example;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.*;

public class Main {

    public static void writeToJson() throws FileNotFoundException {
        String factString = "30 million people in [China] live in caves.\n" +
                "\n" +
                "In 1986, there was a program in [China] to relocate cave-dwelling people into better caves rather than houses.\n" +
                "\n" +
                "15,000 people stayed in caves in [Kent] in [England] during the [Second World War].\n" +
                "\n" +
                "There were rules in the caves in [Kent] enforcing quiet hours and bedtimes.\n" +
                "\n" +
                "The President of [China], Xi Jinping, lived in a cave for seven years during the [Cultural Revolution].";

        String[] factArray = factString.split("\\n\\n");

        JsonObject jsonObject = new JsonObject();

        for (String fact : factArray) {
            long count = fact.chars().filter(ch -> ch == '[').count();
            JsonArray placesArray = new JsonArray();
            for (int i = 0; i < count; i++) {
                int startIndex = fact.indexOf("[") + 1;
                int endIndex = fact.indexOf("]");
                String place = fact.substring(startIndex, endIndex);
                placesArray.add(place);
                fact = fact.replace("[" + place + "]", place); // Remove brackets from the fact string
            }
            jsonObject.addProperty("fact", fact);
            // Add the array to the JSON object
            jsonObject.add("places", placesArray);
            JsonHandler.addToJsonArray("facts.json", jsonObject);
        }


    }
    public static void main(String[]args) throws FileNotFoundException {
        writeToJson();
    }
}

