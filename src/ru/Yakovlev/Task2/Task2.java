package ru.Yakovlev.Task2;

import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        int n;
        int d;
        int m;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число этажей:");
        n = scanner.nextInt();
        System.out.println("Введите число бросков:");
        d = scanner.nextInt();
        System.out.println("Введите число шаров:");
        m = scanner.nextInt();
        if(rowCounter(d, m) < n){
            System.out.println("ВНИМАНИЕ! Данный алгоритм вам не поможет узнать " +
                    "максимальный возможный этаж, так как вам не хватает попыток " +
                    "или шаров!");
        }
        int low = 0;
        int hi = n + 1;
        int drops = 0;
        int f = 0;
        while (rowCounter(drops, m) < n)
            drops += 1;
        System.out.println("Гарантировано с " + Integer.toString(drops) + " попыток мы узнаем макс. этаж.");
        while (low < hi - 1 && d > 0 && m > 0) {
            f = low + rowCounter(drops - 1, m - 1) + 1;
            if(f > n)
                f = n;
            System.out.println("Бросьте шар с " + f + " этажа. Разбился ли он? (y/n):");
            scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            while (!answer.equals("Y") && !answer.equals("y") && !answer.equals("N") && !answer.equals("n")){
                System.out.println("Бросьте шар с " + f + " этажа. Разбился ли он? (y/n):");
                answer = scanner.nextLine();
            }
            if(answer.equals("Y") | answer.equals("y")){
                hi = f;
                m-= 1;
            }
            else {
                low = f;
            }
            drops-=1;
            d-=1;
        }
        System.out.println("Самый высокий этаж, с которого шар не разбивается: " + Integer.toString(low));
    }

    public static int rowCounter(int d, int m){
        if (d <= 1){
            return d;
        }
        if(m == 1){
            return d;
        }
        if (m == 0){
            return 0;
        }
        return rowCounter(d-1, m-1) + 1 + rowCounter(d-1, m);
    }

}
