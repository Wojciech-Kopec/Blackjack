package com.blackjack;

import com.cards.Deck;
import com.datacontrol.Player;

import java.util.Scanner;

/**
 * TODO
 * (?)  Response validation -> As String, REGEX validation OR ENUM(?)
 *                  -> HIT, STAND, DOUBLE DOWN, SPLIT, INSURANCE (?)
 * (?)  FINAL variables
 *      Full tests coverage
 */

class Blackjack {
    private Scanner input = new Scanner(System.in);
    private int playerBet;
    private int response;
    private boolean isBust;
    private boolean hasDealerWon;

    private final int BLACKJACK_LIMIT = 21;

    private Player player = new Player();
    private Deck playingDeck = new Deck();
    private Deck playerHand = new Deck();
    private Deck dealerHand = new Deck();

    void run() {
        System.out.println("Welcome to Blackjack console application!\n");
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        gameLoop();
    }

    private void gameLoop() {
        while (player.getPlayerMoney() > 0) {
            getPlayerBet(player.getPlayerMoney());
            initialDraw();
            playersTurn();
            if (!isBust) {
                dealersTurn();
                if(!hasDealerWon)
                determineWinner();
            }
            clearHands();
        }
        System.out.println("\nYou are out of money!\nGAME OVER.");
        input.close();
    }

    private int getPlayerBet(int playerMoney) {
        do {
            System.out.println("Currently you have $" + playerMoney + "\nHow much would you like to bet?");
            playerBet = input.nextInt();
            if (playerBet > playerMoney)
                System.out.println("You cannot bet more than you have\n");
        } while (playerBet > playerMoney);
        return playerBet;
    }

    private void initialDraw() {
        playerHand.draw(playingDeck);
        playerHand.draw(playingDeck);
        dealerHand.draw(playingDeck);
        dealerHand.draw(playingDeck);
    }

    private void playersTurn() {
        while (response != 2 && !hasPlayerBusted()) {
            System.out.println("Your hand:\n" + playerHand.toString());
            System.out.println("Your cards are valued at: " + playerHand.cardsValue() + "\n");
            System.out.println("Dealer hand: " + dealerHand.getCard(0).toString() + " and [HIDDEN]");
            getPlayersResponse();
        }
        response = 0;
    }

    private void getPlayersResponse() {
        System.out.println("Would you like to (1)Hit or (2)Stand?");
        response = input.nextInt();
        //TODO input validation
        if (response == 1)
            playerHit();
    }

    private void playerHit() {
        playerHand.draw(playingDeck);
        System.out.println("You draw a: " + playerHand.getCard(playerHand.handSize() - 1).toString());
    }

    private boolean hasPlayerBusted() {
        isBust = false;
        if (playerHand.cardsValue() > BLACKJACK_LIMIT) {
            System.out.println("BUST!\nYour total value is currently: " + playerHand.cardsValue());
            System.out.println("Dealer wins.\n");
            player.setPlayerMoney(player.getPlayerMoney() - playerBet);
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
            System.out.println("Dealer draws: " + dealerHand.getCard(dealerHand.handSize() - 1).toString());
            System.out.println("Dealer's hand is valued at: " + dealerHand.cardsValue() + "\n");
        }
    }

    private boolean checkIfDealerWinWithoutDrawing() {
        hasDealerWon = false;
        if (dealerHand.cardsValue() > playerHand.cardsValue()) {
            System.out.println("Dealer wins. You lose " + playerBet + "\n");
            player.setPlayerMoney(player.getPlayerMoney() - playerBet);
            hasDealerWon = true;
        }
        return hasDealerWon;
    }

    private void determineWinner() {
        if (dealerHand.cardsValue() > BLACKJACK_LIMIT) {
            System.out.println("Dealer busts! YOU WIN!\n");
            player.setPlayerMoney(player.getPlayerMoney() + playerBet);
            return;
        }
        if (playerHand.cardsValue() == dealerHand.cardsValue()) {
            System.out.println("PUSH. No one wins this round.\n");

        }
        if (playerHand.cardsValue() > dealerHand.cardsValue()) {
            System.out.println("YOU WIN!\n");
            player.setPlayerMoney(player.getPlayerMoney() + playerBet);
        }
        if (dealerHand.cardsValue() > playerHand.cardsValue()) {
            System.out.println("Dealers wins. You lose." + playerBet + "\n");
            player.setPlayerMoney(player.getPlayerMoney() - playerBet);
        }
    }

    private void clearHands() {
        playerHand.moveAllToDeck(playingDeck);
        dealerHand.moveAllToDeck(playingDeck);
    }
}
