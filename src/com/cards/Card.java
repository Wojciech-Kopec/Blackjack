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

    public enum Rank {

        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
        TEN(10), JACK(10), QUEEN(10), KING(10), ACE(0);

        private int value;

        Rank(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    }

    public enum Suit {
        CLUBS, DIAMONDS, SPADES, HEARTS
    }

}
