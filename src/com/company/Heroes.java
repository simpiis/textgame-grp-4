package com.company;

public class Heroes extends Creature {

    public Heroes(int hp, int damage) {
        super(hp, damage);
    }
    public void printRogue() {
        System.out.println("You choose Rogue!");
        System.out.print("Rogues hp is: ");
        System.out.println(getHp());
        System.out.print("Rogues damage is: ");
        System.out.println(getDamage());
    }
    public void printWarrior() {
        System.out.println("You choose Warrior!");
        System.out.print("Warriors hp is: ");
        System.out.println(getHp());
        System.out.print("Warriors damage is: ");
        System.out.println(getDamage());
    }
    public void printMage() {
        System.out.println("You choose Mage!");
        System.out.print("Mages hp is: ");
        System.out.println(getHp());
        System.out.print("Mages damage is: ");
        System.out.println(getDamage());
    }

}

