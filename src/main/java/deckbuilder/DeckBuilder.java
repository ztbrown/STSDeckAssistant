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

            CardStats cardStats = CardStats.getInstance();

            if (AbstractDungeon.player.chosenClass == PlayerClass.THE_SILENT) {
                characterClass = "silent";
            } else if (AbstractDungeon.player.chosenClass == PlayerClass.IRONCLAD) {
                characterClass = "ironclad";
            } else if (AbstractDungeon.player.chosenClass == PlayerClass.DEFECT) {
                characterClass = "defect";
            }

            if (characterClass != "") {
                Card card = cardStats.getSilentCards().get(_instance.originalName);
                if (card != null) {
                    _instance.name = _instance.originalName + " " + card.plus_minus;
                }
            }
        }

    }
}