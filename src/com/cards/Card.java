package com.cards;


public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit suit, Rank value){
        this.rank = value;
        this.suit = suit;
    }

    public String toString(){
        return this.rank.toString() + " of " + this.suit.toString();
    }

    Rank getRank(){
        return this.rank;
    }
}
