package com.company;

public class Treasure {

    int healthPotion, manaPotion, coins, shinyWeapon, shinyArmor;


    public int getHealthPotion() {
        return healthPotion;
    }

    public void setHealthPotion(int healthPotion) {
        this.healthPotion = healthPotion;
    }

    public int getManaPotion() {
        return manaPotion;
    }

    public void setManaPotion(int manaPotion) {
        this.manaPotion = manaPotion;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getShinyWeapon() {
        return shinyWeapon;
    }

    public void setShinyWeapon(int shinyWeapon) {
        this.shinyWeapon = shinyWeapon;
    }

    public int getShinyArmor() {
        return shinyArmor;
    }

    public void setShinyArmor(int shinyArmor) {
        this.shinyArmor = shinyArmor;
    }

    public Treasure(int healthPotion, int manaPotion, int coins, int shinyWeapon, int shinyArmor){
        this.healthPotion = healthPotion;
        this.manaPotion = manaPotion;
        this.coins = coins;
        this.shinyWeapon = shinyWeapon;
        this.shinyArmor = shinyArmor;
    }

}
