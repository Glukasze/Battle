package com.gracjan.app;

import com.gracjan.app.actors.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Map {

    private boolean playing = true;
    private int turnsSinceSpawnHuman = 10;
    private int turnsSinceSpawnMonster = 10;

    private ArrayList<String> map = new ArrayList<>(Arrays.asList("Human Base:__________________________________________________:Base Monster".split("")));

    private ArrayList<Actor> humans = new ArrayList<>();
    private ArrayList<Actor> monsters = new ArrayList<>();

    public void play() {

        while (playing) {
            try {
                update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void spawn(String faction) {

        if (faction.equals("human") && map.get(11).equals("_") && turnsSinceSpawnHuman > 9) {
            map.set(11, generateActor(faction).getSign());
            turnsSinceSpawnHuman = 0;
        } else if (faction.equals("monster") && map.get(60).equals("_") && turnsSinceSpawnMonster > 9) {
            map.set(60, generateActor(faction).getSign());
            turnsSinceSpawnMonster = 0;
        }
        turnsSinceSpawnHuman++;
        turnsSinceSpawnMonster++;
    }

    public Actor generateActor(String faction) {
        Actor result = null;
        Random random = new Random();
        int temp = random.nextInt(16);
        if (faction.equals("human")) {
            switch (temp) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    result = new Levy();
                    humans.add(result);
                    break;
                case 11:
                case 12:
                case 13:
                case 14:
                    result = new Knight();
                    humans.add(result);
                    break;
                case 15:
                    result = new King();
                    humans.add(result);
                    break;
            }
        } else if (faction.equals("monster")){
            switch (temp) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    result = new Goblin();
                    monsters.add(result);
                    break;
                case 11:
                case 12:
                case 13:
                case 14:
                    result = new Troll();
                    monsters.add(result);
                    break;
                case 15:
                    result = new Dragon();
                    monsters.add(result);
                    break;
            }
        }
        return result;
    }

    public void printMap() {

        updateMap();
        for (String cell : map) {
            System.out.print(cell);
        }
        System.out.print("\n");
    }

    public boolean checkIfAlive(Actor actor) {

        if (actor.getHp() <= 1 && actor.getFaction().equals("human")) {
            humans.remove(actor);
            return true;
        } else if (actor.getHp() <= 1 && actor.getFaction().equals("monster")) {
            monsters.remove(actor);
            return true;
        }
        return false;
    }

    public void updateMap() {

        map = new ArrayList<>(Arrays.asList("Human Base:__________________________________________________:Base Monster".split("")));

        for (Actor human : humans) {
            map.set(human.getCell(), human.getSign());
        }
        for (Actor monster : monsters) {
            map.set(monster.getCell(), monster.getSign());
        }
    }

    public void checkNeighbourCell(Actor actor, String faction) {

        if (faction.equals("human")) {
            if ((map.get(actor.getCell() + 1).equals(":"))) {
                endGame(faction);
            }
            else {
                if (map.get(actor.getCell() + 1).equals("_")) {
                    actor.move();
                } else {
                    Actor enemy = null;
                    for (Actor monster : monsters) {
                        if (monster.getCell() == actor.getCell() + 1 && monster.getFaction().equals("monster")) {
                            enemy = monster;
                            actor.attack(enemy);
                        }
                    }
                    if (enemy != null) {
                        if (checkIfAlive(enemy)) {
                            actor.move();
                        }
                    }
                }
                updateMap();
            }
        } else if (faction.equals("monster")) {
            if ((map.get(actor.getCell() - 1).equals(":"))) {
                endGame(faction);
            }
            else {
                if (map.get(actor.getCell() - 1).equals("_")) {
                    actor.move();
                } else {
                    Actor enemy = null;
                    for (Actor human : humans)
                        if (human.getCell() == actor.getCell() - 1 && human.getFaction().equals("human")) {
                            enemy = human;
                            actor.attack(enemy);
                        }
                    if (enemy != null) {
                        if (checkIfAlive(enemy)) {
                            actor.move();
                        }
                    }
                }
                updateMap();
            }
        }
    }

    public void update() throws InterruptedException {



        spawn("human");
        printMap();
        TimeUnit.MILLISECONDS.sleep(80);
        for (Actor human : humans) {
            checkNeighbourCell(human, human.getFaction());
        }
        printMap();
        TimeUnit.MILLISECONDS.sleep(80);

        spawn("monster");
        printMap();
        TimeUnit.MILLISECONDS.sleep(80);
        for (Actor monster : monsters) {
            checkNeighbourCell(monster, monster.getFaction());
        }
        printMap();
        TimeUnit.MILLISECONDS.sleep(80);

    }

    public void endGame(String faction) {

        this.playing = false;
        System.out.print(faction + "s won!\n");
    }

}
