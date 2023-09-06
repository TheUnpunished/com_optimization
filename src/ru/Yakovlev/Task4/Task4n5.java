package ru.Yakovlev.Task4;

import java.util.Scanner;

public class Task4n5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter the biggest number of computational system: ");
        int maxNumber = scanner.nextInt();
        System.out.printf("Enter the length of the ticket (n): ");
        int n = scanner.nextInt();
        String ticket = "";
        int count = 0;
        for (int i = 0; i < n; i++){
            ticket += "0";
        }
        while (ticket.length() != n + 1){
            int[] sum = sumOfNumbersEvenOdd(ticket, true);
            if(sum[0] == sum[1])
                count ++;
            ticket = nextValue(ticket, maxNumber + 1, true);
        }
        System.out.println(count);
    }

    public static int[] sumOfNumbersEvenOdd(String number, boolean startWithZero){
        int[] result = new int[2]; // 0 - even, 1 - odd
        for (int i = 0; i < number.length(); i++){
            result[(i + ((startWithZero) ? 0 : 1)) % 2] += rawValue(number.charAt(i));
        }
        return result;
    }

    public static String nextValue(String value, int size, boolean startWithZero){
        if (nextNumber(value.charAt(0), size) == ofRawValue(0) && value.length() == 1)
            return "" + ((startWithZero) ? ofRawValue(0) : ofRawValue(1)) + ofRawValue(0);
        else if (nextNumber(value.charAt(value.length() - 1), size) == ofRawValue(0))
            return nextValue(value.substring(0, value.length() - 1), size, startWithZero) + ofRawValue(0);
        else return value.substring(0, value.length() - 1) + nextNumber(value.charAt(value.length() - 1), size);
    }

    public static char nextNumber(char number, int size){
        if(rawValue(number) + 1 == size){
            return ofRawValue(0);
        }
        else {
            return ofRawValue(rawValue(number) + 1);
        }
    }

    public static int rawValue(char number){
        if((int) number > 57)
            return 10 + (((int) number) - 65);

        else
            return ((int) number) - 48;
    }

    public static char ofRawValue(int rawValue){
        if(rawValue > 9){
            return (char) (rawValue - 10 + 65);
        }
        else
            return (char) (rawValue + 48);
    }

}
