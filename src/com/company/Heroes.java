package com.company;

public class Heroes extends Creature {

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

}

