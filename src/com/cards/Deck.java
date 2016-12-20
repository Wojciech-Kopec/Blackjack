package com.cards;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void createFullDeck() {
        for (Suit cardSuit : Suit.values()) {
            for (Rank cardRank : Rank.values()) {
                this.cards.add(new Card(cardSuit, cardRank));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    void addCard(Card addCard){
        cards.add(addCard);
    }

    void removeCard(int i){
        cards.remove(i);
    }

    public Card getCard(int i){
        return cards.get(i);
    }
}