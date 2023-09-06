package ru.Yakovlev.Task5;

import ru.Yakovlev.Task4.Task4n5;

import java.util.ArrayList;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the biggest number of the computational system: ");
        int maxNumber = scanner.nextInt();
        System.out.printf("Enter the checkable ticket: ");
        scanner = new Scanner(System.in);
        String ticket = scanner.nextLine();
        if(isHappy(ticket, maxNumber))
                System.out.println("This ticket is in fact a happy ticket in style of Kazan");
        else
            System.out.println("Unfortunately, this is NOT a happy ticket in style of Kazan. " +
                    "Quitting the program.");
    }

    public static boolean isHappy(String ticket, int maxNumber){
        int sum = sumOfNumbers(ticket);
        if (sum % 2 == 0){
            int[] ticketNumbersCount = countNumbers(ticket, maxNumber + 1);
//            Integer[][] numberCount = new Integer[sum + 1][maxNumber + 1];
            ArrayList<Integer[]>[] numberCount = new ArrayList[sum + 1];
            for (int i = 0; i < numberCount.length; i ++)
                numberCount[i] = new ArrayList<>();
            for(int i = 0; i < ticket.length(); i ++){
                int currentValue = Task4n5.rawValue(ticket.charAt(i));
                if (currentValue != 0){
                    for(int j = currentValue; j < sum + 1; j ++){
                        int k;
                        try{
                            k = j;
                            while (k < sum + 1){
                                if(allZeroesList(numberCount[k])){
                                    ArrayList<Integer> indexes = countPlausibleList(currentValue,
                                            numberCount[k - currentValue],
                                            ticketNumbersCount,
                                            (currentValue - k) == 0,
                                            maxNumber);
                                    if(indexes.size() > 0) {
                                        ArrayList<Integer[]> sums;
                                        if(currentValue - k == 0){
                                            sums = new ArrayList<>();
                                            Integer[] newSum = new Integer[maxNumber + 1];
                                            for (int p = 0; p < newSum.length; p++)
                                                newSum[p] = 0;
                                            sums.add(newSum);
                                        }
                                        else {
                                            sums = numberCount[k - currentValue];
                                        }
                                        ArrayList<Integer[]> solutions = new ArrayList<>();
                                        for(int p = 0; p < indexes.size(); p++){
                                            Integer[] solution = new Integer[maxNumber + 1];
                                            for (int l = 0; l < solution.length; l++){
                                                solution[l] = 0;
                                            }
                                            for (int l = 0; l < solution.length; l++){
                                                solution[l] = sums.get(indexes.get(p))[l];
                                            }
                                            solution[currentValue] ++;
                                            solutions.add(solution);

                                        }
                                        numberCount[k].addAll(solutions);
//                                        numberCount[k][l] = numberCount[k - currentValue][l];
//                                        numberCount[k][currentValue] ++;
                                    }
                                }
                                k += currentValue;
                            }
                        }
                        catch (IndexOutOfBoundsException e){
                        }
                    }
                }
            }
//            for(int i = 0; i < sum + 1; i ++){
//                System.out.printf("%d: ", i);
//                for(int j = 0; j < maxNumber + 1; j ++){
//                    System.out.printf("%d ", numberCount[i][j]);
//                }
//                System.out.printf("\n");
//            }
            return (!allZeroesList(numberCount[sum / 2]));
        }
        else {
            return false;
        }
    }

    static int sumOfNumbers(String value){
        int sum = 0;
        for (int i = 0; i < value.length(); i++){
            sum += Task4n5.rawValue(value.charAt(i));
        }
        return sum;
    }

    static int howManyOfNumber(char number, String value){
        int result = 0;
        for(int i = 0; i < value.length(); i++){
            if(value.charAt(i) == number){
                result ++;
            }
        }
        return result;
    }

    static int[] countNumbers (String value, int size){
        int[] result = new int[size];
        for (int i = 0; i < size; i ++)
            result[i] = 0;
        for(int i = 0; i < value.length(); i ++)
            result[Task4n5.rawValue(value.charAt(i))] ++;
        return result;
    }

    static boolean allZeroes(Integer[] array){
        int i = 0;
        boolean found = false;
        while (!found && i < array.length){
            found = found | array[i] != 0;
            i ++;
        }
        return !found;
    }

    static boolean allZeroesList(ArrayList<Integer[]> arrayList){
        if (arrayList.size() == 0)
            return true;
        int i = 0;
        boolean found = false;
        while (!found && i < arrayList.size()){
            found = found | allZeroes(arrayList.get(i));
            i++;
        }
        return found;
    }

    static boolean countPlausible(int value, Integer[] numberCount, int[] availableNumbersCount,
                                  boolean firstValue){
        return (!allZeroes(numberCount) | firstValue) && numberCount[value] + 1 <= availableNumbersCount[value];
    }

    static ArrayList<Integer> countPlausibleList(int value, ArrayList<Integer[]> numberCount, int[] availableNumbersCount,
                                                 boolean firstValue,
                                                 int maxNumber){
        int i = 0;
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<Integer[]> junk = new ArrayList<>();
        if(numberCount.size() == 0){
            Integer[] integers = new Integer[maxNumber + 1];
            for(int j = 0; j < integers.length; j ++)
                integers[j] = 0;
            junk.add(integers);
            while (i < junk.size()){
                if (countPlausible(value, junk.get(i), availableNumbersCount,
                        firstValue)){
                    indexes.add(i);
                }
                i ++;
            }
        }
        else {
            while (i < numberCount.size()){
                if (countPlausible(value, numberCount.get(i), availableNumbersCount,
                        firstValue)){
                    indexes.add(i);
                }
                i ++;
            }
        }
        return indexes;
    }

}
