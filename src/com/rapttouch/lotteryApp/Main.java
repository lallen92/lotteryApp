package com.rapttouch.lotteryApp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String welcomeMessage = "Welcome to Lotto Online!";
        String optionsMessage = new StringBuilder()
                .append("For 'Quick Pick' selection, please enter the Number: 1\n")
                .append("For manual selection, please the number: 2,\n")
                .append("To exit the application, please press the number: 3").toString();
        int[] possibleNumbers = {1, 2, 3};
        boolean validEntry = false;
        boolean chooseOption = true;
        int numberEntered = 0;

        System.out.println(welcomeMessage);

        while (chooseOption)
        {
            System.out.println(optionsMessage);
            Scanner input = new Scanner(System.in);
            numberEntered = input.nextInt();

            for (int n : possibleNumbers) {
                validEntry = (n == numberEntered);
                if (validEntry) {
                    chooseOption = false;
                    break;
                }
            }

            System.out.println("Invalid entry, please enter a valid option!\n");
        }
        System.out.println("You entered " + numberEntered);
    }
}
