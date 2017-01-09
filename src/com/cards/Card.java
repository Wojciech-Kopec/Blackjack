package com.cards;


public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Rank rank, Suit suit){
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
