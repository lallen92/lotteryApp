package com.rapttouch.lotteryApp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int[] manualPick() {
        int[] manualPickArr = new int[6];
        boolean inValidEntry = true;

        System.out.println("Enter 6 unique numbers between 1-48");
        while (inValidEntry)
        {
            BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
            String[] strNums = new String[0];

            String invalidEntry = "Invalid entry, please enter 6 valid numbers between 1-48!\n";
            try
            {
                strNums = bi.readLine().split("\\s");
            }
            catch (IOException e)
            {
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
                            if(checkForDuplicates(manualPickArr, Integer.parseInt(strNums[i])))
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
                    catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        inValidEntry = true;
                        System.out.println(invalidEntry);
                        break;
                    }
                }
            }
            else
            {
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
        return !duplicate;
    }


    private static int[] getRandomNumbers(byte loopSize)
    {
        int[] quickPickArr = new int[6];
        int max = 48;
        int min = 1;
        int randomValue;
        boolean inValidEntry = true;

        while (inValidEntry)
        {
            for(int i = 0; i<=loopSize; i++)
            {
                randomValue = getRandomInteger(max, min);
                if(checkForDuplicates(quickPickArr, randomValue))
                {
                    quickPickArr[i] = randomValue;
                    inValidEntry = false;
                }
                else
                {
                    inValidEntry = true;
                    break;
                }
            }
        }
        return quickPickArr;
    }

    private static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }

    private static int selectOption()
    {
        String optionsMessage = "For 'Quick Pick' selection, please enter the Number: 1\n" +
                "For manual selection, please the number: 2,\n" +
                "To exit the application, please press the number: 3";

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

    private static boolean checkResults(ArrayList<ArrayList<Integer>> resultLists, ArrayList<ArrayList<Integer>> listOLists)
    {
        ArrayList<Integer> checkArr1;
        ArrayList<Integer> checkArr2;
        checkArr1 = resultLists.get(0);

        for (ArrayList<Integer> listOList : listOLists) {
            boolean winner = false;
            checkArr2 = listOList;

            for (Integer integer : checkArr1) {
                if (checkArr2.contains(integer))
                    winner = true;
                else
                    break;
            }
            return winner;
        }
        return false;
    }
    private static void writeToJsonFile(String fileName, ArrayList<ArrayList<Integer>> listOLists)
    {
        LocalDate localDate = LocalDate.now();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String JsonFile = gson.toJson(listOLists);
        String JsonDate = gson.toJson(DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate)) + "\n";

        try
        {
            FileWriter file = new FileWriter(fileName + ".json");
            file.write(JsonDate);
            file.write(JsonFile);
            file.close();
        }
        catch (IOException e)
        {
            System.out.println("Failed to create Json file: " + fileName);
        }
    }

    public static void main(String[] args)
    {
        byte quickPickSize = 5;
        String resultsFileName = "results";
        int inputChoice;
        int[] returnedArr;
        boolean anotherPick = true;
        boolean invalidEntrySaveNumbers = true;
        boolean resultLotto;

        ArrayList<ArrayList<Integer>> listOLists = new ArrayList<>();
        ArrayList<ArrayList<Integer>> resultLists = new ArrayList<>();

        String welcomeMessage = "Welcome to Lotto Online!";
        System.out.println(welcomeMessage);

        while(anotherPick)
        {
            boolean invalidEntry = true;
            inputChoice = selectOption();
            switch (inputChoice)
            {
                case 1:
                    int quickNumberLines = 0;
                    System.out.println("Quick Pick");
                    while (quickNumberLines < 4)
                    {
                        var storeAllArrayQuick = new ArrayList<Integer>();
                        returnedArr = getRandomNumbers(quickPickSize);
                        for (int value : returnedArr) storeAllArrayQuick.add(value);
                        listOLists.add(storeAllArrayQuick);
                        quickNumberLines += 1;
                    }
                    break;
                case 2:
                    System.out.println("Manual Pick");
                    var storeAllArrayManual = new ArrayList<Integer>();
                    returnedArr = manualPick();
                    for (int value : returnedArr) storeAllArrayManual.add(value);
                    listOLists.add(storeAllArrayManual);
                    break;
                case 3:
                    System.exit(0);
            }

            System.out.println("Would you like to make another selection?\n Enter: 'Yes' or 'No'");
            Scanner scanner = new Scanner(System. in);
            while(invalidEntry)
            {
                String anotherSelectionEntered = scanner.nextLine();
                if (anotherSelectionEntered.toLowerCase().contains("yes"))
                {
                    invalidEntry = false;
                }
                else if (anotherSelectionEntered.toLowerCase().contains("no"))
                {
                    invalidEntry = false;
                    anotherPick = false;
                }
                else
                    System.out.println("Invalid entry, Enter: 'Yes' or 'No'");
            }
        }
        System.out.println("Would you like to save your picks to a file? \n Enter: 'Yes' or 'No'");
        Scanner scanner = new Scanner(System .in);
        while(invalidEntrySaveNumbers)
        {
            String anotherSelectionEntered = scanner.nextLine();
            if (anotherSelectionEntered.toLowerCase().contains("yes"))
            {
                System.out.println("Please enter a file name:");
                String fileName = scanner.nextLine();
                invalidEntrySaveNumbers = false;
                writeToJsonFile(fileName, listOLists);
            }
            else if (anotherSelectionEntered.toLowerCase().contains("no"))
            {
                invalidEntrySaveNumbers = false;
            } else
                System.out.println("Invalid entry, Enter: 'Yes' or 'No'");
        }

        var storeLottoResults = new ArrayList<Integer>();
        returnedArr = getRandomNumbers(quickPickSize);
        for (int value : returnedArr) storeLottoResults.add(value);
        resultLists.add(storeLottoResults);
        writeToJsonFile(resultsFileName, resultLists);

        resultLotto = checkResults(resultLists, listOLists);

        if(resultLotto)
            System.out.println("You're a winner!!");
        else
            System.out.println("Unlucky, try again!");
    }
}


