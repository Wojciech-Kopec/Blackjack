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

    public String toString(){
        String deckOutput = "";
        for(Card card: cards){
            deckOutput += card.toString() + "\n";
        }
        return deckOutput;
    }

    private void addCard(Card addCard){
        cards.add(addCard);
    }

    private void removeCard(int i){
        cards.remove(i);
    }

    public Card getCard(int i){
        return cards.get(i);
    }

    public int getSize(){
        return this.cards.size();
    }

    public void draw(Deck comingFrom){
        cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int cardsValue(){
        int totalValue = 0;
        for(Card card: cards) {
            totalValue += card.getRank().getValue();
            if (card.getRank() == Rank.ACE && totalValue <= 11)
                totalValue += 10;
        }
        return totalValue;
    }

    public void moveAllToDeck(Deck moveTo){
        int thisDeckSize = cards.size();
        for (int i = thisDeckSize - 1; i >= 0; i--){
            moveTo.addCard(getCard(i));
            removeCard(i);
        }
    }
}