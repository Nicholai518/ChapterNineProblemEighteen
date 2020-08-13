/*
    Chapter Nine Problem Eighteen
    18. Gas Prices
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {


         //sbauer data
        ChapterNineProblemEighteen cn = new ChapterNineProblemEighteen();
        cn.run();
        System.out.println("----------------------------------------------------------------------------");


        // Variables
        ArrayList<String> allFileData = new ArrayList<String>();
        ArrayList<String> onlyDatesArrayList = new ArrayList<String>();
        ArrayList<Double> onlyGasPriceArrayList = new ArrayList<>();
        ArrayList<Integer> onlyTheYears = new ArrayList<>();

        // Greeting
        greeting();

        // Read and store all of the lines from the file into an arraylist
        allFileData = readAllFileLines();

        // Store all the dates into one ArrayList
        onlyDatesArrayList = separateDates(allFileData);

        // Store all of the weekly average prices
        onlyGasPriceArrayList = separateGasPrices(allFileData);

        // Store all of the years into an ArrayList
        onlyTheYears = OnlyTheYears(allFileData);

        // Display average yearly prices
        printAllAverageYearlyPrices(onlyTheYears, onlyGasPriceArrayList);

        // Display the average Price Per Month
        printAllMonthlyAverages(onlyDatesArrayList,onlyGasPriceArrayList);

        // Closing
        closeProgram();
    }

    //Method
    public static void greeting()
    {
        JOptionPane.showMessageDialog(null, "This program used Gas Price data from 1993 to 2013, designed by YARASAREYIS PRODUCTIONS");
    }

    // Close the Program
    public static void closeProgram()
    {
        JOptionPane.showMessageDialog(null, "Program complete. Now closing.");
        System.exit(0);
    }

    // File Reader method
    public static ArrayList<String> readAllFileLines()
    {
        // Array
        String[] arr = new String[2];

        // ArrayList
        ArrayList<String> allFileLines = new ArrayList<String>();

        try
        {
            // File Path and Scanner to read file
            String filePath = "C:\\dev\\saveFilesHere\\GasPrices.txt";
            File inputFile = new File(filePath);
            Scanner fileReader = new Scanner(inputFile);

            while (fileReader.hasNextLine())
            {
                // If you know there is a line of text in the file, we know the delimiter is :
                // This is separate the date from the price
                arr = fileReader.nextLine().split(":");

                // Add these values to your array list
                for (int i = 0; i < arr.length; i++)
                {
                    allFileLines.add(arr[i]);
                }
            }
        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "The try catch broke!");
        }

        // Return arraylist
        return allFileLines;
    }

    // Create ArrayList for just the dates
    public static ArrayList separateDates(ArrayList<String> allFileLines)
    {
        ArrayList<String> datesArrayList = new ArrayList<String>();

        // Step through the ArrayList Parameter argument
        for (int i = 0; i < allFileLines.size(); i++)
        {
            if (i % 2 == 0)
            {
                datesArrayList.add(allFileLines.get(i));
            }
            // Split each String at each index of the ArrayList parameter argument
            // String[] datesArray = allFileLines.get(i).split(":");

            // Add the String value in the String Arrays to ArrayList
            //datesArrayList.add(datesArray[0]);
        }
        return datesArrayList;
    }

    // Create an ArrayList for just the prices
    public static ArrayList separateGasPrices(ArrayList<String> allFileLines)
    {
        ArrayList<Double> gasPriceArrayList = new ArrayList<>();

        // Step through the ArrayList Parameter argument
        for (int i = 0; i < allFileLines.size(); i++)
        {
            // All of the prices are stored at odd indexes
            if (i % 2 != 0)
            {
                gasPriceArrayList.add( Double.parseDouble(allFileLines.get(i)));
            }
        }

        // Return gas prices
        return gasPriceArrayList;
    }

    // Return only the years
    public static ArrayList OnlyTheYears(ArrayList<String> allFileLines)
    {
        // ArrayList for only years
        ArrayList<Integer> OnlyYears = new ArrayList<>();


        for (int i = 0; i < allFileLines.size(); i++)
        {
            if (i % 2 == 0)
            {
                // Split delimiter = -
                String[] monthDayYear = allFileLines.get(i).split("-");

                // parse int from the String year value
                int year = Integer.parseInt(monthDayYear[2]);

                // Add value to onlyYears ArrayList
                OnlyYears.add(year);
            }
        }

        return OnlyYears;

    }

    // Display the average price per year
    public static void printAllAverageYearlyPrices(ArrayList<Integer> years, ArrayList<Double> prices)
    {
        // Variables
        double sum = 0.0;
        double average = 0;
        int totalMonthsForThatYear = 0;

        // if it does match, that index is special, The index at prices ArrayList will be associated with that year so add it to the          price accumulatoraccumulator and add          1 to the totalMonthsForThatYear
        // Once no more matches are found, divide the accumulator by

        // Header
        System.out.println("              AVERAGE PRICE PER YEAR \n ----------------------------------------");
        // Step through the years arraylist starting with 1993 until 2016
        for (int y = 1993; y <= 2013; y++)
        {
            // If 1993 matches the element at index i within the years ArrayList   - Hard coded could be a problem
            for (int i = 0; i < years.size(); i++)
            {
                if (y == years.get(i))
                {
                    // if it does match, that index is special,The index at prices ArrayList will be associated with that year
                    //add it to the price accumulator
                    sum += prices.get(i);

                    // add 1 to the totalMonthsForThatYear
                    totalMonthsForThatYear++;
                }
                average = sum / ((double) totalMonthsForThatYear);  // double / int


            }

            // Display the average for that year
            System.out.printf("                  %d : S%.2f", y, average);
            //System.out.printf("The average for the year " + y + " is S%.2f" + average);
            System.out.println();

            // Reset the values
            totalMonthsForThatYear = 0;
            sum = 0;
            average = 0;
        }

        // Divider Line
        System.out.println("-----------------------------------------");


    }

    public static void printAllMonthlyAverages(ArrayList<String> dates, ArrayList<Double> prices)
    {
        // Variables
        String currentMonth, currentYear;
        double sum = 0;
        double average;
        double howManyWeeks = 0;

        // step through the dates arraylist
        for (int i = 0; i < dates.size(); i+=howManyWeeks)
        {
            // clear how many weeks accumulator
             howManyWeeks =0;

            // tokenize the string, delimiter is "-", store all values into datePieces
            // mm, dd, yyyy
            String[] monthDayYear = dates.get(i).split("-");

            // Store element 0 and element 2 of the datePieces array (mm and yyyy)
            currentMonth = monthDayYear[0];
            currentYear = monthDayYear[2];

            for (int j = 0; j < dates.size(); j++)
            {
                String[] monthDayYearSecondArray = dates.get(i).split("-");

                if (currentMonth.equalsIgnoreCase(monthDayYearSecondArray[0]) &&
                        currentYear.equalsIgnoreCase(monthDayYearSecondArray[2]))
                {
                    // Add one to the howManyWeeksAccumulator
                    howManyWeeks++;

                    // add gas price to the sum
                    sum += prices.get(i);
                }

            }
            if(howManyWeeks == 0)
            {
                howManyWeeks =1;
            }
            else
            {
                // Calculate average
                average = sum / howManyWeeks;

                //System.out.printf("The average for the year " + y + " is S%.2f" + average);
                System.out.printf("For Month " + currentMonth + " year " + currentYear + " average price was " + String.format("%.2f", average) + "\n");

            }
            
            // Reset accumulators
            sum  = 0;

        }
    }
}
