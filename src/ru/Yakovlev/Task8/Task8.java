package ru.Yakovlev.Task8;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Task8 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(System.currentTimeMillis());
        System.out.print("Введите размер массива (n): ");
        int n = scanner.nextInt();
        System.out.print("Введите максимальное число: ");
        int maxNumber = scanner.nextInt();
        int size = getSize(maxNumber);
        ArrayList<Integer> sortable = new ArrayList<>();
        for (int i = 0; i < n; i ++){
            sortable.add(random.nextInt(maxNumber + 1));
            System.out.printf("%" + size + "d ", sortable.get(i));
        }
        quickSort(sortable, 0, sortable.size() - 1);
        System.out.println();
        for (int i = 0; i < n; i ++){
            System.out.printf("%" + size + "d ", sortable.get(i));
        }
    }

    static void quickSort(ArrayList<Integer> sortable, int l, int r){
        if (l >= r)
            return;
        else{
            Random random = new Random(System.currentTimeMillis());
            int index = l + random.nextInt((r + 1) - l);
            Integer q = sortable.get(index);
            int i = l;
            int j = r;
            while (i <= j){
                while (sortable.get(i) < q)
                    i ++;
                while (sortable.get(j) > q)
                    j --;
                if (i <= j){
                    Integer switchable = sortable.get(i);
                    sortable.set(i, sortable.get(j));
                    sortable.set(j, switchable);
                    i ++;
                    j --;
                }
            }
            quickSort(sortable, l, j);
            quickSort(sortable, i, r);
        }
    }

    static int getSize(int maxNumber){
        int i = 0;
        while (maxNumber > 0){
            maxNumber /= 10;
            i ++;
        }
        return i;
    }

}
