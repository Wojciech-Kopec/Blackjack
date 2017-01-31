package com.datacontrol;

import java.util.Scanner;

public class DataReader {
    private final String HIT_REGEX = "(?i)^(1|hit)$";
    private final String STAND_REGEX = "(?i)^(2|stand)$";
    private final String PLAY_REGEX = "(?i)^(1|play)$";
    private final String EXIT_REGEX = "(?i)^(0|exit)$";

    private Scanner input = new Scanner(System.in);



    public String getPlayerName() {
        System.out.println("Please state your name: ");
        return input.nextLine();
        /* TODO Check if in database - Welcome msg */
    }

    public int validateBet() {
        int bet = 0;
            if (!input.hasNextInt()) {
                System.out.println("Your bet must be entered in numerical format!");
                input.nextLine();
                return bet;
            }
            bet = input.nextInt();
            input.nextLine();
            if (bet <= 0)
                System.out.println("Your bet have to be a positive amount!");
        return bet;
    }

    public boolean isHitChosen() {
        String option = "";
        while (!isMatchHitOrStand(option)) {
            System.out.println("Would you like to (1)Hit or (2)Stand?");
            option = input.nextLine();
            if (!isMatchHitOrStand(option)) System.out.println("Looks like you are trying to enter invalid characters!\nTry again.");
        }
        return option.matches(HIT_REGEX);
    }

    private boolean isMatchHitOrStand(String option) {
        return option.matches(HIT_REGEX) || option.matches(STAND_REGEX);
    }

    public boolean willGameBeContinued() {
        String option = "";
        while (!isMatchPlayOrExit(option)) {
            System.out.println("Do you wish to play again?");
            System.out.println("(1)PLAY\t\t\t(0)EXIT");
            option = input.nextLine();
            if (!isMatchPlayOrExit(option)) System.out.println("Looks like you are trying to enter invalid characters!\nTry again.");
        }
        return option.matches(PLAY_REGEX);
    }

    private boolean isMatchPlayOrExit(String option) {
        return option.matches(PLAY_REGEX) || option.matches(EXIT_REGEX);
    }

    public void closeScanner() {
        input.close();
    }
}
