package com.wojci.blackjack;

import com.wojci.cards.Deck;

import java.util.Scanner;

/**
 * Created by testuj on 2016-11-21.
 */
class Blackjack {
    private Scanner input = new Scanner(System.in);
    private int playerMoney = 1000;
    private int playerBet;
    private int response;
    private Deck playingDeck = new Deck();
    private Deck playerHand = new Deck();
    private Deck dealerHand = new Deck();
    private boolean isBust;
    private boolean hasDealerWon;


    void run() {
        System.out.println("Welcome to Blackjack console application!\n");
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        gameLoop();
    }

    private void gameLoop() {
        while (playerMoney > 0) {
            getPlayerBet(playerMoney);
            firstDeal();
            playersTurn();
            //boolean vars to not load boolean methods once more
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

    private void firstDeal() {
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
        if (playerHand.cardsValue() > 21) {
            System.out.println("BUST!\nYour total value is currently: " + playerHand.cardsValue());
            System.out.println("Dealer wins.\n");
            playerMoney -= playerBet;
            isBust = true;
        }
        return isBust;
    }

    private void dealersTurn() {
        System.out.println("Dealer hand:\n" + dealerHand.toString());
        System.out.println("Dealer's hand is valued at: " + dealerHand.cardsValue() + "\n");
        if (checkIfDealerWinWithoutDrawing())
        return;
        while (dealerHand.cardsValue() < 17) {
            dealerHand.draw(playingDeck);
            System.out.println("Dealer draws: " + dealerHand.getCard(dealerHand.handSize() - 1).toString());
            System.out.println("Dealer's hand is valued at: " + dealerHand.cardsValue() + "\n");
        }

    }

    private boolean checkIfDealerWinWithoutDrawing() {
        hasDealerWon = false;
        if (dealerHand.cardsValue() > playerHand.cardsValue()) {
            System.out.println("Dealer wins. You lose " + playerBet + "\n");
            playerMoney -= playerBet;
            hasDealerWon = true;
        }
        return hasDealerWon;
    }

    private void determineWinner() {
        if (dealerHand.cardsValue() > 21) {
            System.out.println("Dealer busts! YOU WIN!\n");
            playerMoney += playerBet;
            return;
        }
        if (playerHand.cardsValue() == dealerHand.cardsValue()) {
            System.out.println("PUSH. No one wins this round.\n");

        }
        if (playerHand.cardsValue() > dealerHand.cardsValue()) {
            System.out.println("YOU WIN!\n");
            playerMoney += playerBet;
        }
        if (dealerHand.cardsValue() > playerHand.cardsValue()) {
            System.out.println("Dealers wins. You lose." + playerBet + "\n");
            playerMoney -= playerBet;
        }
    }

    private void clearHands() {
        playerHand.moveAllToDeck(playingDeck);
        dealerHand.moveAllToDeck(playingDeck);
    }
}
