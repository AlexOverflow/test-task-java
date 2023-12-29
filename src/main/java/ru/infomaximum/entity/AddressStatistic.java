package ru.infomaximum.entity;

public class AddressStatistic {
    private int repeatCounter;

    public AddressStatistic(int repeatCounter) {
        this.repeatCounter = 0;
    }

    public void increaseCounter () {
        this.repeatCounter++;
    }

    public AddressStatistic() {
        this.repeatCounter = 0;
    }

    public int getRepeatCounter() {
        return repeatCounter;
    }

    public void setRepeatCounter(int repeatCounter) {
        this.repeatCounter = repeatCounter;
    }
}
