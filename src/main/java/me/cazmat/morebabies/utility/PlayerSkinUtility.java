package me.cazmat.morebabies.utility;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class PlayerSkinUtility {
    private static final Gson gson = new Gson();
    public static String getHeadValue(UUID uuid) {
        try {
            String jsonText = getURLContent("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.toString());
            JsonObject jsonObject = gson.fromJson(jsonText, JsonObject.class);
            return jsonObject.getAsJsonArray("properties").get(0).getAsJsonObject().get("value").getAsString();
        } catch(Exception e) {
            return "nil";
        }
    }
    private static String getURLContent(String urlString) throws IOException {
        InputStream inputStream = new URL(urlString).openStream();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return readAll(bufferedReader);
        } finally {
            inputStream.close();
        }
    }
    private static String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while((cp = reader.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }
}
