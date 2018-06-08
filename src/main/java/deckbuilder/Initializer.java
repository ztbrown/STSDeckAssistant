package deckbuilder;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;

@SpireInitializer
public class Initializer {
    public static void initialize()
    {
        CardStats.getInstance();
    }
}