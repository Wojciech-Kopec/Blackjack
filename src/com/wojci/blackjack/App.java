package com.wojci.blackjack;

/** DataReader class will be responsible for following functions:
 * TODO
 * - Save Player's name to file along with starting time of the game session
 * ---Check if in file - Appropriate welcome message
 * - Update W/P/L statistics throughout game session - updated after every game
 * - While exiting game-loop write game data -> player's name, time started, time elapsed. W/P/L stats, end balance to appropriate section
 * ---Check if qualified for High-score section - Put in High-score table with printed message.
 */

public class App {
    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack();
        blackjack.run();
    }
}
