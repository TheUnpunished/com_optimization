package ru.Yakovlev.Task4;

import java.math.BigInteger;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the biggest number of computational system: ");
        int maxNumber = scanner.nextInt();
        System.out.printf("Enter the length of the ticket (n): ");
        int n = scanner.nextInt();
        System.out.printf("Is it HALF of the length? (y/n): ");
        scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        boolean half = false;
        while (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N")){
            System.out.printf("Is it HALF of the length? (y/n): ");
            answer = scanner.nextLine();
        }
        if (answer.equals("y") || answer.equals("Y"))
            half = !half;
        BigInteger[] result = calculateCount(n * ((half) ? 2 : 1), 0, maxNumber, new BigInteger[0]);
        int j = 0;
        System.out.println("Moscow/Leningrad style happy ticket count (n = " + Integer.toString(n) + ") : " +
                result[maxNumber * (n / 2)].toString());
    }

    static  BigInteger[] calculateCount (int n, int i, int maxNumber, BigInteger[] prevSheet){
        if (i == 0){
            BigInteger[] sheet = new BigInteger[maxNumber + 1];
            for (int k = 0; k < sheet.length; k ++){
                sheet[k] = BigInteger.ZERO;
            }
            sheet[0] = BigInteger.ONE;
            return calculateCount(n, i + 1, maxNumber, sheet);
        }
        else {
            if(i <= n){
                BigInteger[] sheet = new BigInteger[maxNumber * i + 1];
                for (int k = 0; k < sheet.length; k ++){
                    BigInteger sum = BigInteger.ZERO;
                    for (int j = maxNumber; j >= 0; j --){
                        try{
                            sum = sum.add(prevSheet[k - j]);
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            continue;
                        }
                    }
                    sheet[k] = sum;
                }
                return calculateCount(n, i + 1, maxNumber, sheet);
            }
            else {
                return prevSheet;
            }
        }
    }
}
