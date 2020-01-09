package com.rapttouch.lotteryApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static String invalidEntry = "Invalid entry, please enter 6 valid numbers between 1-48!\n";

    static int[] manualPick() {
        //String invalidEntry = "Invalid entry, please enter 6 valid numbers between 1-48!\n";
        int manualPickArr[] = new int[6];
        boolean inValidEntry = true;
        int numberEntered = 0;

        System.out.println("Enter 6 unique numbers between 1-48");
        while (inValidEntry)
        {
            BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
            String[] strNums = new String[0];

            try
            {
                strNums = bi.readLine().split("\\s");
            }
            catch (IOException e)
            {
                inValidEntry = true;
                System.out.println(invalidEntry);
            }

            if(strNums.length == 6)
            {
                for (int i = 0; i < strNums.length; i++)
                {
                    try
                    {

                        if ((Integer.parseInt(strNums[i]) >= 1) && (Integer.parseInt(strNums[i]) <= 48))
                        {
                                if(!checkForDuplicates(manualPickArr, Integer.parseInt(strNums[i])))
                                {
                                    manualPickArr[i] = Integer.parseInt(strNums[i]);
                                    inValidEntry = false;
                                }
                                else
                                {
                                    inValidEntry = true;
                                    System.out.println(invalidEntry);
                                    break;
                                }
                        }
                        else
                        {
                            inValidEntry = true;
                            System.out.println(invalidEntry);
                            break;
                        }

                    }
                    catch (NumberFormatException e) {
                        inValidEntry = true;
                        System.out.println(invalidEntry);
                        break;
                    }
                    catch (ArrayIndexOutOfBoundsException e)
                    {
                        inValidEntry = true;
                        System.out.println(invalidEntry);
                        break;
                    }
                }
            }
            else
            {
                inValidEntry = true;
                System.out.println(invalidEntry);
            }
        }
        return manualPickArr;

    }

    private static boolean checkForDuplicates(int[] manualPickArr, int valueEntered)
    {
        boolean duplicate = false;
        for (int element : manualPickArr)
        {
            if (element == valueEntered)
            {
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }



    static int[] quickPick()
    {
        int quickPickArr[] = new int[6];
        int max = 48;
        int min = 1;
        int randomValue = 0;
        boolean inValidEntry = true;

        while (inValidEntry)
        {
            for(int i = 0; i<=5; i++)
            {
                randomValue = getRandomInteger(max, min);
                if(!checkForDuplicates(quickPickArr, randomValue))
                {
                    quickPickArr[i] = randomValue;
                    inValidEntry = false;
                }
                else
                {
                    inValidEntry = true;
                    System.out.println(invalidEntry);
                    break;
                }
            }
        }

        return quickPickArr;
    }

    private static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    static int selectOption()
    {
        String optionsMessage = new StringBuilder()
                .append("For 'Quick Pick' selection, please enter the Number: 1\n")
                .append("For manual selection, please the number: 2,\n")
                .append("To exit the application, please press the number: 3").toString();

        boolean inValidEntry = true;
        int numberEntered = 0;

        while (inValidEntry)
        {
            System.out.println(optionsMessage);
            Scanner input = new Scanner(System.in);
            try
            {
                numberEntered = input.nextInt();
                if ((numberEntered >=1) &&( numberEntered <= 3)) {
                    inValidEntry = false;
                }
                else
                {
                    inValidEntry = true;
                    System.out.println("Invalid entry, please enter a valid option!\n");
                }
            }
            catch (InputMismatchException e)
            {
                inValidEntry = true;
                System.out.println("Invalid entry, please enter a valid option!\n");
            }
        }
        System.out.println();
        return numberEntered;
    }

    public static void main(String[] args) throws IOException {
        int inputChoice = 0;
        int returnedArr[] = new int[6];
        int quickNumberLines = 0;
        ArrayList<ArrayList<Integer>> listOLists = new ArrayList<ArrayList<Integer>>();
        String welcomeMessage = "Welcome to Lotto Online!";
        System.out.println(welcomeMessage);


        inputChoice = selectOption();

        switch (inputChoice) {
            case 1:
                System.out.println("Quick Pick");
                while(quickNumberLines < 4)
                {
                    ArrayList<Integer> storeAllArrayQuick = new ArrayList<Integer>();
                    returnedArr = quickPick();
                    for (int i = 0; i < returnedArr.length; i++)
                        storeAllArrayQuick.add(returnedArr[i]);
                    listOLists.add(storeAllArrayQuick);
                    quickNumberLines += 1;
                }
                break;
            case 2:
                System.out.println("Manual Pick");
                ArrayList<Integer> storeAllArrayManual = new ArrayList<Integer>();
                returnedArr = manualPick();
                for (int i = 0; i < returnedArr.length; i++)
                    storeAllArrayManual.add(returnedArr[i]);
                listOLists.add(storeAllArrayManual);
                break;
            case 3:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Exit");
        }
        //printLinesToScreen(listOLists);
        System.out.println(Arrays.toString(listOLists.toArray()));

    }
}


