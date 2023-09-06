package ru.Yakovlev.Task1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Task1 {

    public static void main(String[] args) {
        String[] lines = new String[9];
        File file = new File("Task1.csv");
        int n = 0;
        int[] aRussia = new int[]{};
        int[] aChina = new int[]{};
        int[] trRussia = new int[]{};
        int[] trChina = new int[]{};
        int sRussia = 0;
        int sChina = 0;
        int fRussia = 0;
        int fChina = 0;
        try{
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
            int i = 0;
            String line;
            while ((line = br.readLine())!=null){
                lines[i++] = line;
            }
            n = Integer.parseInt(lines[0]);
            String[][] convertables = new String[2][n];
            String[][] convertables2 = new String[2][n-1];
            aRussia = new int[n];
            aChina = new int[n];
            trRussia = new int[n];
            trChina = new int[n];
            for (i = 0; i < 2; i++){
                convertables[i] = lines[i+1].split(",");
                convertables2[i] = lines[i+3].split(",");
            }
            for (i = 0; i < n; i++){
                aRussia[i] = Integer.parseInt(convertables[0][i]);
                aChina[i] = Integer.parseInt(convertables[1][i]);
            }
            for(i = 0; i < n - 1; i++){
                trRussia[i] = Integer.parseInt(convertables2[0][i]);
                trChina[i] = Integer.parseInt(convertables2[1][i]);
            }
            sRussia = Integer.parseInt(lines[5]);
            sChina = Integer.parseInt(lines[6]);
            fRussia = Integer.parseInt(lines[7]);
            fChina = Integer.parseInt(lines[8]);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            System.gc();
        }
        boolean isDomestic = true;
        int minRussia = sRussia + aRussia[0];
        int minChina = sChina + aChina[0];
        TaskObject taskObject = new TaskObject("", minRussia, minChina);
        taskObject = vaccinePath(1, aRussia, aChina, trRussia, trChina, taskObject);
        minRussia = taskObject.getMinRussia() + fRussia;
        minChina = taskObject.getMinChina() + fChina;
        String pathRussia = "";
        String pathChina = "";
        for (int i = 0; i < taskObject.getVaccinePath().length(); i+=2){
            pathRussia += taskObject.getVaccinePath().charAt(i);
            pathChina += taskObject.getVaccinePath().charAt(i+1);
        }
        System.out.println("Путь \"Россия\". Длина: " + Integer.toString(minRussia) + " Путь: " +
                pathRussia);
        System.out.println("Путь \"Китай\". Длина: " + Integer.toString(minChina) + " Путь: " +
                pathChina);
        System.out.println("Выбран путь " +((minRussia <= minChina)? "\"Россия\"" : "\"Китай\"") + ".");
    }

    public static TaskObject vaccinePath(int j, int[] aRussia, int[] aChina,
                                     int[] trRussia, int[] trChina, TaskObject  taskObject_prev){
        if (j < aRussia.length){
            char pathRussia;
            int minRussia;
            char pathChina;
            int minChina;
            if (taskObject_prev.getMinRussia() + aRussia[j] <= taskObject_prev.getMinChina() + aRussia[j] + trChina[j-1]){
                pathRussia = 'R';
                minRussia = taskObject_prev.getMinRussia() + aRussia[j];
            }
            else {
                pathRussia = 'C';
                minRussia = taskObject_prev.getMinChina() + aRussia[j] + trChina[j-1];
            }
            if (taskObject_prev.getMinChina() + aChina[j] <= taskObject_prev.getMinRussia() + aChina[j] + trRussia[j-1]){
                pathChina = 'C';
                minChina = taskObject_prev.getMinChina() + aChina[j];
            }
            else {
                pathChina = 'R';
                minChina = taskObject_prev.getMinRussia() + aChina[j] + trRussia[j-1];
            }
            taskObject_prev.setMinChina(minChina);
            taskObject_prev.setMinRussia(minRussia);
            taskObject_prev.setVaccinePath(taskObject_prev.getVaccinePath() + pathRussia + pathChina);
            return vaccinePath(j+1, aRussia, aChina, trRussia, trChina,
                    taskObject_prev);
        }
        else return taskObject_prev;
    }
}
