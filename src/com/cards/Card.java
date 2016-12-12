package com.cards;


public class Card {
    private Suit suit;
    private Rank rank;

    Card(Suit suit, Rank rank){
        this.rank = rank;
        this.suit = suit;
    }

    public String toString(){
        return this.rank.toString() + " of " + this.suit.toString();
    }

    Rank getRank(){
        return this.rank;
    }
}
