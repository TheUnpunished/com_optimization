package ru.Yakovlev.Task6;

import ru.Yakovlev.Task4.Task4n5;
import ru.Yakovlev.Task5.Task5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Task6n5 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the length of tickets (n): ");
        int n = scanner.nextInt();
        System.out.printf("Enter the biggest number of the computational system: ");
        int maxNumber = scanner.nextInt();
        String ticket = "";
        for(int i = 0; i < n; i ++){
            ticket += "0";
        }
        ticket = ticket.substring(0, ticket.length() - 1) + "1";
        int count = 1;
        File f = new File("new2.txt");
        ArrayList<String> strings = new ArrayList<>();
        while (ticket.length() <= n){
//            System.out.println(ticket);
            int oldCount = count;
            count += Task5.isHappy(ticket,maxNumber) ? 1 : 0;
            if(count != oldCount){
                String ns = sortString(ticket);
                if(!strings.contains(ns)){
                    strings.add(ns);
                }
            }
            ticket = Task4n5.nextValue(ticket, maxNumber + 1, true);
        }
        System.out.println(count);

    }

    public static String sortString(String inputString)
    {
        // convert input string to Character array
        Character tempArray[] = new Character[inputString.length()];
        for (int i = 0; i < inputString.length(); i++) {
            tempArray[i] = inputString.charAt(i);
        }


        // Sort, ignoring case during sorting
        Arrays.sort(tempArray, new Comparator<Character>(){

            @Override
            public int compare(Character c1, Character c2)
            {
                // ignoring case
                return Character.compare(Character.toLowerCase(c2),
                        Character.toLowerCase(c1));
            }
        });

        // using StringBuilder to convert Character array to String
        StringBuilder sb = new StringBuilder(tempArray.length);
        for (Character c : tempArray)
            sb.append(c.charValue());

        return sb.toString();
    }

    public static Character[] sort(Character[] inputString)
    {
        // convert input string to Character array
        Character tempArray[] = new Character[inputString.length];
        for (int i = 0; i < inputString.length; i++) {
            tempArray[i] = inputString[i];
        }


        // Sort, ignoring case during sorting
        Arrays.sort(tempArray, new Comparator<Character>(){

            @Override
            public int compare(Character c1, Character c2)
            {
                // ignoring case
                return Character.compare(Character.toLowerCase(c2),
                        Character.toLowerCase(c1));
            }
        });

        // using StringBuilder to convert Character array to String
        return tempArray;
    }
}
