package com.blackjack;

import com.cards.Deck;
import com.cards.Hand;
import com.datacontrol.DataReader;
import com.datacontrol.Player;

public class Blackjack {
    private final Player player = new Player();
    private final Deck playingDeck = new Deck();
    private final Hand playerHand = new Hand();
    private final Hand dealerHand = new Hand();
    private final DataReader dataReader = new DataReader();

    private int bet = 0;
    private boolean isBust;
    private boolean hasDealerWon;
    private final int BLACKJACK_LIMIT = 21;

    public void run() {
        System.out.println("Welcome to Blackjack console application!\n");
        getPlayerName();
        gameLoop();
    }

    private void getPlayerName() {
        player.setPlayerName(dataReader.getPlayerName());
    }

    private void gameLoop() {
        do {
            getPlayerBet();
            initialDraw();
            playersTurn();
            if (!isBust) {
                dealersTurn();
                if (!hasDealerWon)
                    determineWinner();
            }
            clearHands();
            displayPlayersBalance();
        } while (player.getBalance() > 0 && dataReader.willGameBeContinued());
        printPlayerStatistics();
    }

    private int getPlayerBet() {
        System.out.println("How much would you like to bet?");
        do {
            bet = dataReader.validateBet();
            if (bet > player.getBalance())
                System.out.println("You cannot bet more than you have!\n");
        } while (bet > player.getBalance() || bet > 0);
        return bet;
    }

    private void initialDraw() {
        playerHand.draw(playingDeck);
        playerHand.draw(playingDeck);
        dealerHand.draw(playingDeck);
        dealerHand.draw(playingDeck);
    }

    private void playersTurn() {
        while (!hasPlayerBusted()) {
            System.out.println("Your hand:\n" + playerHand.toString());
            System.out.println("Your cards are valued at: " + playerHand.cardsValue() + "\n");
            System.out.println("Dealer hand: " + dealerHand.getCard(0).toString() + " and [HIDDEN]");
            if (dataReader.isHitChosen()) playerHit();
            else break;
        }
    }

    private void playerHit() {
        playerHand.draw(playingDeck);
        System.out.println("You draw a: " + playerHand.getCard(playerHand.getSize() - 1).toString());
    }

    private boolean hasPlayerBusted() {
        isBust = false;
        if (playerHand.cardsValue() > BLACKJACK_LIMIT) {
            System.out.println("BUST!\nYour total value is currently: " + playerHand.cardsValue());
            System.out.println("Dealer wins.\n");
            player.lose(bet);
            isBust = true;
        }
        return isBust;
    }

    private void dealersTurn() {
        System.out.println("Dealer hand:\n" + dealerHand.toString());
        System.out.println("Dealer's hand is valued at: " + dealerHand.cardsValue() + "\n");
        if (checkIfDealerWinWithoutDrawing())
            return;
        final int DEALERS_DRAW_LIMIT = 17;
        while (dealerHand.cardsValue() < DEALERS_DRAW_LIMIT) {
            dealerHand.draw(playingDeck);
            System.out.println("Dealer draws: " + dealerHand.getCard(dealerHand.getSize() - 1).toString());
            System.out.println("Dealer's hand is valued at: " + dealerHand.cardsValue() + "\n");
        }
    }

    private boolean checkIfDealerWinWithoutDrawing() {
        hasDealerWon = false;
        if (dealerHand.cardsValue() > playerHand.cardsValue()) {
            System.out.println("Dealer wins. You lose $" + bet + "\n");
            player.lose(bet);
            hasDealerWon = true;
        }
        return hasDealerWon;
    }

    private void determineWinner() {
        if (dealerHand.cardsValue() > BLACKJACK_LIMIT) {
            System.out.println("Dealer busts! YOU WIN $" + bet + "\n");
            player.win(bet);
            return;
        }
        if (playerHand.cardsValue() == dealerHand.cardsValue()) {
            System.out.println("PUSH. No one wins this round.\n");
            player.push();
        }
        if (playerHand.cardsValue() > dealerHand.cardsValue()) {
            System.out.println("YOU WIN $" + bet + "\n");
            player.win(bet);
        }
        if (dealerHand.cardsValue() > playerHand.cardsValue()) {
            System.out.println("Dealers wins. You lose $" + bet + "\n");
            player.lose(bet);
        }
    }

    private void clearHands() {
        playerHand.moveAllToDeck(playingDeck);
        dealerHand.moveAllToDeck(playingDeck);
    }

    private void displayPlayersBalance() {
        System.out.println("Currently you have $" + player.getBalance());
    }

    private void printPlayerStatistics() {
        if(player.getBalance() == 0)
        System.out.println("\nYou are out of funds!\nUnfortunately you cannot continue to play.\n");
        System.out.println("Hope you had a good time though!\nHere are your statistics from the game\n");
        player.endGame();
        System.out.println(player.toString());
        dataReader.closeScanner();
    }
}