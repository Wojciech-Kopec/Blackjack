package com.cards;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    protected ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        createFullDeck();
        shuffle();
    }

    private void createFullDeck() {
        for (Card.Suit cardSuit : Card.Suit.values()) {
            for (Card.Rank cardRank : Card.Rank.values()) {
                cards.add(new Card(cardRank, cardSuit));
            }
        }
    }

    private void shuffle(){
        Collections.shuffle(cards);
    }

    public void addCard(Card addCard){
        cards.add(addCard);
    }

    void removeCard(int i){
        cards.remove(i);
    }

    public Card getCard(int i){
        return cards.get(i);
    }

    public int getSize(){
        return cards.size();
    }

}