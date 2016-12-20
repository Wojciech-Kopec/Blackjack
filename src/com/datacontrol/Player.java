package com.datacontrol;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Player implements Serializable {
    private int balance = 1000;
    private String playerName;
    private int wins;
    private int pushes;
    private int loses;
    private Duration timePlayed;
    private Date timestamp;
    private Instant startTime;

    public int incrementLosesByOne() {
        return loses++;
    }

    public int incrementWinsByOne() {
        return wins++;
    }

    public int incrementPushesByOne() {
        return pushes++;
    }

    public int incrementPlayerBalanceByBet(int bet) {
        return balance += bet;
    }

    public int decrementPlayerBalanceByBet(int bet) {
        return balance -= bet;
    }

    public int getBalance() {
        return balance;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void endGame() {
        Instant endTime = Instant.now();
        timePlayed = Duration.between(startTime, endTime);
    }

    @Override
    public String toString() {
        return "\nYour game statistics:\n" +
                "Name\t" + playerName + "\n" +
                "Balance\t" + balance + "\n" +
                "Wins\t" + wins + "\n" +
                "Pushes\t" + pushes + "\n" +
                "Loses\t" + loses + "\n" +
                "Time elapsed\t" + timePlayed + "\n" +
                "Timestamp\t" + timestamp;
    }

    public Player() {
        startTime = Instant.now();
        timestamp = new Date();
    }
}
