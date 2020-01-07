package com.company;

public class Creature {
    private int hp;
    private int damage;
    private String name;
    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Creature(int hp, int damage, String name) {
        this.hp = hp;
        this.damage = damage;
        this.name = name;
        this.pos = pos;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
