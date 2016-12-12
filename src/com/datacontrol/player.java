package com.datacontrol;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

/**
 * TODO methods:
 * getPlayerName();
 * Initialize duration
 * Attach timestamp
 */
public class Player implements Serializable {
    private int playerMoney = 1000;
    private String playerName;
    private int wins;
    private int pushes;
    private int loses;
    private Date Timestamp;
    private Duration timePlayed;




    public int getPlayerMoney() {
        return playerMoney;
    }

    public void setPlayerMoney(int playerMoney) {
        this.playerMoney = playerMoney;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getPushes() {
        return pushes;
    }

    public void setPushes(int pushes) {
        this.pushes = pushes;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public Date getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(Date timestamp) {
        Timestamp = timestamp;
    }

    public Duration getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(Duration timePlayed) {
        this.timePlayed = timePlayed;
    }
}
