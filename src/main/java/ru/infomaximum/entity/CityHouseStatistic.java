package ru.infomaximum.entity;

public class CityHouseStatistic {
    private int oneStoryHouseCounter;
    private int twoStoryHouseCounter;
    private int threeStoryHouseCounter;
    private int fourStoryHouseCounter;
    private int fiveStoryHouseCounter;

    public CityHouseStatistic() {
        this.oneStoryHouseCounter = 0;
        this.twoStoryHouseCounter = 0;
        this.threeStoryHouseCounter = 0;
        this.fourStoryHouseCounter = 0;
        this.fiveStoryHouseCounter = 0;
    }

    public void updateHouseCounter(int floor) {
        switch (floor) {
            case 1: oneStoryHouseCounter++;
            break;
            case 2: twoStoryHouseCounter++;
            break;
            case 3: threeStoryHouseCounter++;
            break;
            case 4: fourStoryHouseCounter++;
            break;
            case 5: fiveStoryHouseCounter++;
        }
    }

    public int getOneStoryHouseCounter() {
        return oneStoryHouseCounter;
    }

    public void setOneStoryHouseCounter(int oneStoryHouseCounter) {
        this.oneStoryHouseCounter = oneStoryHouseCounter;
    }

    public int getTwoStoryHouseCounter() {
        return twoStoryHouseCounter;
    }

    public void setTwoStoryHouseCounter(int twoStoryHouseCounter) {
        this.twoStoryHouseCounter = twoStoryHouseCounter;
    }

    public int getThreeStoryHouseCounter() {
        return threeStoryHouseCounter;
    }

    public void setThreeStoryHouseCounter(int threeStoryHouseCounter) {
        this.threeStoryHouseCounter = threeStoryHouseCounter;
    }

    public int getFiveStoryHouseCounter() {
        return fiveStoryHouseCounter;
    }

    public void setFiveStoryHouseCounter(int fiveStoryHouseCounter) {
        this.fiveStoryHouseCounter = fiveStoryHouseCounter;
    }

    public int getFourStoryHouseCounter() {
        return fourStoryHouseCounter;
    }

    public void setFourStoryHouseCounter(int fourStoryHouseCounter) {
        this.fourStoryHouseCounter = fourStoryHouseCounter;
    }

    @Override
    public String toString() {
        return "одноэтажных домов: " + oneStoryHouseCounter +
                ", двухэтажных домов:" + twoStoryHouseCounter +
                ", трех этажных домов:" + threeStoryHouseCounter +
                ", четырех этажных домов:" + fourStoryHouseCounter +
                ", пятиэтажных домов:" + fiveStoryHouseCounter + " ";
    }
}
