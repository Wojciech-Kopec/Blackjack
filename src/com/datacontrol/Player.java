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
    private final Date currentDate;
    private final Instant startTime;

    public int getBalance() {
        return balance;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void endGame() {
        final Instant endTime = Instant.now();
        timePlayed = Duration.between(startTime, endTime);
    }

    public void win(int bet) {
        balance += bet;
        wins++;
    }

    public void lose(int bet) {
        balance -= bet;
        loses++;
    }

    public void push() {
        pushes++;
    }
    @Override
    public String toString() {
        return "\nYour game statistics:" +
                "\nName\t\t" + playerName +
                "\nBalance\t\t" + balance +
                "\nWins\t\t" + wins +
                "\nPushes\t\t" + pushes +
                "\nLoses\t\t" + loses +
                "\nTime elapsed\t\t" + timePlayed +
                "\nDate\t\t" + currentDate;
    }

    public Player() {
        startTime = Instant.now();
        currentDate = new Date();
    }
}
