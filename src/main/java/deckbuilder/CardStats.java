package deckbuilder;

import java.util.HashMap;
import java.util.Map;
import java.net.*;
import java.io.*;

import com.google.gson.*;

public class CardStats {
    private static CardStats instance = null;
    public Map<String, Card> silentCards;
    public Map<String, Card> ironcladCards;
    public Map<String, Card> defectCards;
    protected CardStats() {
    }
    public static CardStats getInstance() {
        if(instance == null) {
            System.out.println("Instantiating CardStats");
            instance = new CardStats();
            instance.silentCards = new HashMap<String, Card>();
            instance.ironcladCards = new HashMap<String, Card>();
            instance.defectCards = new HashMap<String, Card>();

            instance.fetchRemoteSpireData("silent", instance.silentCards);
            instance.fetchRemoteSpireData("ironclad", instance.silentCards);
            instance.fetchRemoteSpireData("defect", instance.silentCards);

        }
        System.out.println("Returning Instance of CardStats");

        return instance;
    }

    public void fetchRemoteSpireData(String character, Map<String, Card> cardMap) {
        try {
            String sURL = "https://rocky-journey-26730.herokuapp.com/characters/" + character + "/cards.json";
            URL url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            Object content = request.getContent();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) content));
            JsonArray rootarr = root.getAsJsonArray();
            for(JsonElement s: rootarr){
                JsonObject obj = s.getAsJsonObject();
                Card card = new Card(obj.get("name").getAsString(),
                        obj.get("picked").getAsInt(),
                        obj.get("winrate").getAsFloat(),
                        obj.get("expected_winrate").getAsFloat(),
                        obj.get("plus_minus").getAsFloat());

                cardMap.put(obj.get("name").getAsString(), card);
            }
            System.out.println("================== " + character.toUpperCase() + " CARDS ====================");
        } catch(Exception exception) {

        }
    }

    public Map<String,Card> getSilentCards() {
        return silentCards;
    }
    public Map<String,Card> getIroncladCards() {
        return ironcladCards;
    }
    public Map<String,Card> getDefectCards() {
        return defectCards;
    }

}