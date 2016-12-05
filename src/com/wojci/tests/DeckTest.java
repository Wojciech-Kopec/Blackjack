package com.wojci.tests;

import com.wojci.cards.Card;
import com.wojci.cards.Deck;
import com.wojci.cards.Rank;
import com.wojci.cards.Suit;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DeckTest {

    Deck deck = new Deck();

    @Test
    public void test(){
        deck.addCard(new Card(Suit.CLUBS, Rank.FIVE));
        assertThat(deck.cardsValue(), is(5));
    }

    @Test
    public void test2(){
        deck.addCard(new Card(Suit.CLUBS, Rank.ACE));
        deck.addCard(new Card(Suit.CLUBS, Rank.TEN));
        deck.addCard(new Card(Suit.CLUBS, Rank.TEN));
        assertEquals(21, deck.cardsValue());
    }
}
