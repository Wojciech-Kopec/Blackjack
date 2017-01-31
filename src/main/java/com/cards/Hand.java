package com.cards;

import java.util.ArrayList;

public class Hand extends Deck {

    public Hand() {
        cards = new ArrayList<>();
    }

    public void draw(Deck comingFrom){
        cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int cardsValue(){
        int totalValue = 0;
        for(Card card: cards) {
            totalValue += card.getRank().getValue();
            if (card.getRank() == Card.Rank.ACE && totalValue <= 10)
                totalValue += 11;
            else if(card.getRank() == Card.Rank.ACE && totalValue > 10)
                totalValue += 1;
        }
        return totalValue;
    }

    public void moveAllToDeck(Deck moveTo){
        for (int i = getSize() - 1; i >= 0; i--){
            moveTo.addCard(this.getCard(i));
            removeCard(i);
        }
    }

    public String toString(){
        String handOutput = "";
        for(Card card: cards){
            handOutput += card.toString() + "\n";
        }
        return handOutput;
    }
}