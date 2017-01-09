import com.cards.*;
import com.cards.Card;
import org.junit.Test;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class HandTest {
    private Hand hand = new Hand();
    private Deck deck = new Deck();

    @Test
    public void drawTest() {
        Card drawCard = deck.getCard(0);
        hand.draw(deck);
        Card receivedCard = hand.getCard(0);
        assertThat(drawCard, is(receivedCard));
        assertThat(deck.getSize(), is(51));
        assertThat(hand.getSize(), is(1));
    }


/*
   Card Ace = new Card(Suit.HEARTS, Rank.ACE);
   Nie działa :(((
*/

}
