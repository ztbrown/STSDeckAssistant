package deckbuilder;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;

@SpirePatch(cls="com.megacrit.cardcrawl.cards.AbstractCard", method="initializeTitle")
public class DeckBuilder {
    @SuppressWarnings("unused")
    @SpireInsertPatch(rloc=0)
    public static void Insert(AbstractCard _instance) {
        _instance.name = "Entry Point";
        System.out.println("=========== initializeTitle ===========");
    }
}