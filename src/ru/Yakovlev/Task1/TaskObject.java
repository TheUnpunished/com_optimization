package ru.Yakovlev.Task1;

public class TaskObject {

    private String vaccinePath;
    private int minRussia;
    private int minChina;

    public String getVaccinePath() {
        return vaccinePath;
    }

    public void setVaccinePath(String vaccinePath) {
        this.vaccinePath = vaccinePath;
    }

    public int getMinRussia() {
        return minRussia;
    }

    public void setMinRussia(int minRussia) {
        this.minRussia = minRussia;
    }

    public int getMinChina() {
        return minChina;
    }

    public void setMinChina(int minChina) {
        this.minChina = minChina;
    }

    public TaskObject(String vaccinePath, int minRussia, int minChina) {
        this.vaccinePath = vaccinePath;
        this.minRussia = minRussia;
        this.minChina = minChina;
    }
}
