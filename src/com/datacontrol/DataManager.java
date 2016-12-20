package com.datacontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Date;

/**
 * DataReader class will be responsible for following functions:
 * TODO
 * - Save Player.java's name to file along with starting time of the game session
 * ---Check if in file - Appropriate welcome message
 * - Update W/P/L statistics throughout game session - updated after every game
 * - While exiting game-loop write game data -> Player.java's name, time started, time elapsed. W/P/L stats, end balance to appropriate section
 * ---Check if qualified for High-score section - Put in High-score table with printed message.
 *
 * METHODS
 * DataManager()
 * checkIfFileExists()
 * checkIfPlayerPlayedBefore()
 * savePlayerStatsToFile()
 * checkIfQualifiedForHighscore()
 * printFile();
 */

public class DataManager {
    private final String FILE_NAME = "App Statistics.txt";
    private File file = new File(FILE_NAME);
    private BufferedReader reader;


    public void checkIfFileExits() {
        boolean fileExists = file.exists();
        if (!fileExists) {
            try {
                fileExists = file.createNewFile();
            } catch (IOException e) {
                System.out.println("Nie uda?o si? utworzy? pliku");
            }
        }
        if (fileExists)
            System.out.println("Plik " + FILE_NAME + " istnieje lub zosta? utworzony");
    }





    public void printFile() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public DataManager() {
        Instant startTime = Instant.now();
        Date timestamp = new Date();
    }
}
