package com.datacontrol;

import java.util.Scanner;

public class DataReader {
    private final Scanner input = new Scanner(System.in);
    private final String HIT_REGEX = "(?i)^1|hit$";
    private final String STAND_REGEX = "(?i)^2|stand$";
    private final String PLAY_REGEX = "(?i)^1|play$";
    private final String EXIT_REGEX = "(?i)^0|exit$";


    public String getPlayerName() {
        System.out.println("Please state your name: ");
        return input.nextLine();
        /* TODO Check if in database - Welcome msg */
    }


//    public String getInputOption() {
//        String option = input.nextLine();
//        return option;
//    }

    public String getResponse() {
        System.out.println("Would you like to (1)Hit or (2)Stand?");
        while (!(input.hasNext(HIT_REGEX) || input.hasNext(STAND_REGEX))) {
            System.out.println("Looks like you are trying to enter invalid characters. Try again.");
            input.next();
        }
        return input.next();
    }
}
