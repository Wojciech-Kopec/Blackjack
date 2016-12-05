package com.wojci.cards;

/**
 * Created by testuj on 2016-11-21.
 */
public class Card {

    private Suit suit;
    private Rank value;
    private int realValue;

    public Card(Suit suit, Rank value){
        this. value = value;
        this.suit = suit;
    }

    public String toString(){
        return this.value.toString() + " of " + this.suit.toString();
    }

    Rank getValue(){
        return this.value;
    }
}
