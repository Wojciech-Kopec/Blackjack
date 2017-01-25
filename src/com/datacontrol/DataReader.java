package com.datacontrol;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataReader {
    private final Scanner input = new Scanner(System.in);

    public String getPlayerName() {
        System.out.println("Please state your name: ");
        return input.nextLine();
        /* TODO Check if in database - Welcome msg */
    }

    public int validateBet() {
        int bet = 0;
        while (bet <= 0) {
            while (!input.hasNextInt()) {
                System.out.println("Your bet must be entered in numerical format!");
                input.nextLine();
            }
            bet = input.nextInt();
            input.nextLine();
            if (bet <= 0)
                System.out.println("Your bet have to be positive!");
        }
        return bet;
    }

    public boolean isHitChosen() {
        System.out.println("Would you like to (1)Hit or (2)Stand?");
        String HIT_REGEX = "(?i)^([1]|hit)$";
        String STAND_REGEX = "(?i)^([2]|stand)$";
        Pattern HIT_PATTERN = Pattern.compile(HIT_REGEX);
        Pattern STAND_PATTERN = Pattern.compile(STAND_REGEX);
        while (!input.hasNext(HIT_PATTERN) && !input.hasNext(STAND_PATTERN)) {
            System.out.println("Looks like you are trying to enter invalid characters. Try again.");
            input.nextLine();
        }
        String option = input.next();
        Matcher m = HIT_PATTERN.matcher(option);
        return m.find();
    }

    public boolean willGameBeContinued() {
        System.out.println("Do you wish to play again?");
        System.out.println("(1)PLAY\t\t(0)EXIT");
        String PLAY_REGEX = "(?i)^1|play$";
        String EXIT_REGEX = "(?i)^0|exit$";
        while (!input.hasNext(PLAY_REGEX) && !input.hasNext(EXIT_REGEX)) {
            System.out.println("Looks like you are trying to enter invalid characters. Try again.");
            input.nextLine();
        }
        String option = input.next();
        input.nextLine();
        return option.equals(PLAY_REGEX);
    }


    public void closeScanner() {
        input.close();
    }
}
