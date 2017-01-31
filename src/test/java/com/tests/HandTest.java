package com.tests;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import com.cards.*;
import com.cards.Card;

public class HandTest {
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;

    @Before
    public void setUp() {
        playerHand = new Hand();
        dealerHand = new Hand();
        deck = new Deck();
    }

    @Test
    public void drawTest() {
        Card drawCard = deck.getCard(0);
        playerHand.draw(deck);
        Card receivedCard = playerHand.getCard(0);
        assertThat(drawCard, is(receivedCard));
        assertThat(deck.getSize(), is(51));
        assertThat(playerHand.getSize(), is(1));
    }

    @Test
    public void casesWithAcesTest() {
        playerHand.addCard(new Card(Card.Rank.NINE, Card.Suit.HEARTS));
        playerHand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        assertThat(playerHand.cardsValue(), is(20));
        playerHand.addCard(new Card(Card.Rank.ACE, Card.Suit.HEARTS));
        assertThat(playerHand.cardsValue(), is(21));
    }

    @Test
    public void moveAllToDeckTest() {
        playerHand.draw(deck);
        dealerHand.draw(deck);
        playerHand.draw(deck);
        dealerHand.draw(deck);
        playerHand.moveAllToDeck(deck);
        dealerHand.moveAllToDeck(deck);
        assertThat(playerHand.getSize(), is(0));
        assertThat(dealerHand.getSize(), is(0));
        assertThat(deck.getSize(), is(52));
    }
}
