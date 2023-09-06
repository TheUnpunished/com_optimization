package ru.Yakovlev.Task3;

public class TaskObject {
    private int value;
    private char direction;

    public TaskObject() {
        this.value = 0;
        this.direction = ' ';
    }

    public TaskObject(int value, char direction) {
        this.value = value;
        this.direction = direction;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value) + this.direction;
    }
}
