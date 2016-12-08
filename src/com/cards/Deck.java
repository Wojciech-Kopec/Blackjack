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
            for (Rank cardValue : Rank.values()) {
                this.cards.add(new Card(cardSuit, cardValue));
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

    private void removeCard(int i){
        cards.remove(i);
    }

    public Card getCard(int i){
        return cards.get(i);
    }

    public void addCard(Card addCard){
        cards.add(addCard);
    }

    public void draw(Deck comingFrom){
        cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int handSize(){
        return this.cards.size();
    }

    public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card card: cards){
            switch(card.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case ACE: aces += 1; break;
            }
        }
        for (int i = 0; i < aces; i++){
            if(totalValue > 10){
                totalValue += 1;
            } else {
                totalValue += 11;
            }
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