package com.cards;

import java.util.ArrayList;

public class Hand extends Deck {

    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public String toString(){
        String handOutput = "";
        for(Card card: cards){
            handOutput += card.toString() + "\n";
        }
        return handOutput;
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
