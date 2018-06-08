package deckbuilder;

public class Card {

    public String name;
    public int picked;
    public float winrate;
    public float expected_winrate;
    public float plus_minus;

    public Card(String name, int picked, float winrate, float expected_winrate, float plus_minus) {
        this.name = name;
        this.picked = picked;
        this.winrate = winrate;
        this.expected_winrate = expected_winrate;
        this.plus_minus = plus_minus;
    }

}