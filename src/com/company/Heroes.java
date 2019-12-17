package com.company;

public class Heroes extends Creature {
    private int mageCounter = 0;
    private int rogueMana;


    public Heroes(int hp, int damage, String name) {
        super(hp, damage, name);
    }
    public void printHero() {
        System.out.println("You choose "+ getName() + "!");
        System.out.print(getName() + "s hp is: ");
        System.out.println(getHp());
        System.out.print(getName() + "s damage is: ");
        System.out.println(getDamage());

    }
    public int getMageCounter() {
        return mageCounter;
    }

    public void setMageCounter(int mageCounter) {
        this.mageCounter = mageCounter;
    }

    public int getRogueMana() {
        return rogueMana;
    }

    public void setRogueMana(int rogueMana) {
        this.rogueMana = rogueMana;
    }
}

