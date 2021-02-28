package com.gracjan.app.actors;

import java.util.Random;

public abstract class Actor {

    private ActorType type;
    private String sign;
    private String faction;
    private int hp;
    private int minDamage;
    private int maxDamage;
    private int cell;

    public Actor(ActorType type, String sign, String faction, int hp, int minDamage, int maxDamage) {

        this.type = type;
        this.sign = sign;
        this.faction = faction;
        this.hp = hp;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;

        if (faction.equals("human")) {
            this.cell = 11;
        } else {
            this.cell = 60;
        }
    }

    public int getHp() {
        return hp;
    }

    public void decreaseHp(int damage) {
        this.hp = hp - damage;
    }

    public String getSign() {
        return sign;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getFaction() {
        return faction;
    }

    public void move() {
        if (faction.equals("human")) {
            cell++;
        } else {
            cell--;
        }
    }

    public void attack (Actor actor) {

        Random random = new Random();
        int damage = random.nextInt(maxDamage - minDamage) + minDamage;
        actor.decreaseHp(damage);
    }
}
