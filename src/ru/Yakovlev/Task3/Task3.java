package ru.Yakovlev.Task3;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        //ACCGGTCGAGTGCGCGGAAGCCGGCCGAA
        //ABCBDAB
        String X;
        //BDCABA
        //GTCGTTCGGAATGCCGTTGCTCTGTAAA
        String Y;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введие последовательность X:");
        X = scanner.nextLine();
        X = " " + X;
        System.out.println("Введие последовательность Y:");
        Y = scanner.nextLine();
        Y = " " + Y;
        int m = X.length();
        int n = Y.length();
        TaskObject[][] c = new TaskObject[m][n];
        for(int i = 0; i < m; i ++){
            for (int j = 0; j < n; j++){
                c[i][j] = new TaskObject();
            }
        }
        for (int i = 1; i < m; i++){
            for (int j = 1; j < n; j++){
                if (X.charAt(i) == Y.charAt(j)){
                    c[i][j].setValue(c[i-1][j-1].getValue() + 1);
                    c[i][j].setDirection('D');
                }
                else {
                    if (c[i - 1][j].getValue() >= c[i][j - 1].getValue()) {
                        c[i][j].setValue(c[i - 1][j].getValue());
                        c[i][j].setDirection('U');
                    } else {
                        c[i][j].setValue(c[i][j - 1].getValue());
                        c[i][j].setDirection('L');
                    }
                }
            }
        }
        System.out.println("Таблицы c и b:");
        for (int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                System.out.printf("%5s", c[i][j].toString());
            }
            System.out.println();
        }
        System.out.println("Самая длинная подпоследовательность: " +
                printLCS(c, X, m - 1, n - 1) + " длиной " + Integer.toString(c[m-1][n-1].getValue()));
    }

    public static String printLCS(TaskObject[][] c, String X, int i, int j){
        if (i == 0 || j == 0)
            return "";
        if (c[i][j].getDirection() == 'D')
            return printLCS(c, X, i - 1, j - 1) + X.charAt(i);
        else if (c[i][j].getDirection() == 'U')
            return printLCS(c, X, i - 1, j);
        else
            return printLCS(c, X, i, j - 1);
    }

}
