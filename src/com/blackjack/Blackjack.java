package com.blackjack;

import com.cards.Deck;
import com.cards.Hand;
import com.datacontrol.Player;

import java.util.Scanner;

/**
 * TODO
 * Option menu - Play(1) & Quit(0)
 * Full tests coverage
 */

public class Blackjack {
    private Player player = new Player();
    private Deck playingDeck = new Deck();
    private Hand playerHand = new Hand();
    private Hand dealerHand = new Hand();
    private Scanner input = new Scanner(System.in);

    private int bet;
    private String response;
    private boolean isBust;
    private boolean hasDealerWon;
    private final int BLACKJACK_LIMIT = 21;

    private final String HIT_REGEX = "(?i)^1|hit$";
    private final String STAND_REGEX = "(?i)^2|stand$";
    private final String PLAY_REGEX = "(?i)^1|play$";
    private final String EXIT_REGEX = "(?i)^1|exit$";

    public void run() {
        System.out.println("Welcome to Blackjack console com.application!\n");
        getPlayerName();
        gameLoop();
    }

    private void getPlayerName() {
        System.out.println("Please state your name: ");
        player.setPlayerName(input.nextLine());
        /* TODO Check if in database - Welcome msg */
    }

    private void gameLoop() {
        while (player.getBalance() > 0) {
            getPlayerBet();
            initialDraw();
            playersTurn();
            if (!isBust) {
                dealersTurn();
                if (!hasDealerWon)
                    determineWinner();
            }
            clearHands();
        }
        printPlayerStatistics();
    }

    private int getPlayerBet() {
        do {
            System.out.println("Currently you have $" + player.getBalance() + "\nHow much would you like to bet?");
            while (!input.hasNextInt()) {
                System.out.println("Please enter your bet in numerical format.");
                input.next();
            }
            bet = input.nextInt();
            if (bet > player.getBalance())
                System.out.println("You cannot bet more than you have!\n");
        } while (bet <= 0 && bet > player.getBalance());
        return bet;
    }

    private void initialDraw() {
        playerHand.draw(playingDeck);
        playerHand.draw(playingDeck);
        dealerHand.draw(playingDeck);
        dealerHand.draw(playingDeck);
    }

    private void playersTurn() {
        do {
            System.out.println("Your hand:\n" + playerHand.toString());
            System.out.println("Your cards are valued at: " + playerHand.cardsValue() + "\n");
            System.out.println("Dealer hand: " + dealerHand.getCard(0).toString() + " and [HIDDEN]");
            getPlayersResponse();
        } while (!(response.equals(STAND_REGEX) || hasPlayerBusted()));
    }

    private void getPlayersResponse() {
        System.out.println("Would you like to (1)Hit or (2)Stand?");
        while (!(input.hasNext(HIT_REGEX) || input.hasNext(STAND_REGEX))) {
            System.out.println("Looks like you are trying to enter invalid characters. Try again.");
        }
        response = input.next();
        if (response.matches(HIT_REGEX))
            playerHit();
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
            player.decrementPlayerBalanceByBet(bet);
            player.incrementLosesByOne();
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
            player.decrementPlayerBalanceByBet(bet);
            player.incrementLosesByOne();
            hasDealerWon = true;
        }
        return hasDealerWon;
    }

    private void determineWinner() {
        if (dealerHand.cardsValue() > BLACKJACK_LIMIT) {
            System.out.println("Dealer busts! YOU WIN!\n");
            player.incrementPlayerBalanceByBet(bet);
            player.incrementWinsByOne();
            return;
        }
        if (playerHand.cardsValue() == dealerHand.cardsValue()) {
            System.out.println("PUSH. No one wins this round.\n");
            player.incrementPushesByOne();
        }
        if (playerHand.cardsValue() > dealerHand.cardsValue()) {
            System.out.println("YOU WIN!\n");
            player.incrementPlayerBalanceByBet(bet);
            player.incrementWinsByOne();
        }
        if (dealerHand.cardsValue() > playerHand.cardsValue()) {
            System.out.println("Dealers wins. You lose $" + bet + "\n");
            player.decrementPlayerBalanceByBet(bet);
            player.incrementLosesByOne();
        }
    }

    private void clearHands() {
        playerHand.moveAllToDeck(playingDeck);
        dealerHand.moveAllToDeck(playingDeck);
    }

    private void printPlayerStatistics() {
        System.out.println("\nYou are out of funds!\nUnfortunately you cannot continue to play.\n" +
                "Hope you had a good time though!\nHere are your statistics from the game\n");
        player.endGame();
        System.out.println(player.toString());
        input.close();
    }
}
