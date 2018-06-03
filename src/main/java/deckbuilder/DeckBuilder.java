package deckbuilder;

import java.net.*;
import java.io.*;

import com.google.gson.*;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(cls="com.megacrit.cardcrawl.cards.AbstractCard", method="initializeTitle")
public class DeckBuilder {
    @SuppressWarnings("unused")
    @SpireInsertPatch(rloc=0)
    public static void Insert(AbstractCard _instance) throws Exception {
        if(AbstractDungeon.player != null) {
            String characterClass = "";
            if (AbstractDungeon.player.chosenClass == PlayerClass.THE_SILENT) {
                characterClass = "silent";
            } else if (AbstractDungeon.player.chosenClass == PlayerClass.IRONCLAD) {
                characterClass = "ironclad";
            } else if (AbstractDungeon.player.chosenClass == PlayerClass.DEFECT) {
                characterClass = "defect";
            }

            String card_name = _instance.originalName.replace("+", "");
            card_name = card_name.replace(" ", "%20");

            if (characterClass != ""){
                String sURL = "https://rocky-journey-26730.herokuapp.com/characters/" + characterClass + "/cards/" + card_name + ".json";
                System.out.println(sURL);
                try {
                URL url = new URL(sURL);
                URLConnection request = url.openConnection();
                request.connect();

                Object content = request.getContent();

                System.out.println(content);


                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) content));
                    JsonObject rootobj = root.getAsJsonObject();
                    String plus_minus = rootobj.get("plus_minus").getAsString();
                    _instance.name = _instance.originalName + " " + plus_minus;
                } catch(Exception exception) {

                }
            }
        }

    }
}