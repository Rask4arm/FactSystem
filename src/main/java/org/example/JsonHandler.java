package org.example;

import com.google.gson.*;
import java.io.*;

public class JsonHandler {
    public static void addToJsonArray(String filePath, JsonObject newObject) {
        JsonArray jsonArray;
        File file = new File(filePath);

        // Check if file exists and has content
        if (file.exists() && file.length() > 0) {
            try (Reader reader = new FileReader(file)) {
                JsonElement fileElement = JsonParser.parseReader(reader);
                if (fileElement != null && fileElement.isJsonArray()) {
                    jsonArray = fileElement.getAsJsonArray();
                } else {
                    // File exists but isn't an array (e.g., contains {} or "text")
                    jsonArray = new JsonArray();
                }
            } catch (IOException e) {
                jsonArray = new JsonArray(); // Fallback on error
            }
        } else {
            // File doesn't exist or is 0 bytes—initialize correctly
            jsonArray = new JsonArray();
        }

        // Now safe to add
        jsonArray.add(newObject);

        // Save back to file
        try (Writer writer = new FileWriter(file)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(jsonArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
