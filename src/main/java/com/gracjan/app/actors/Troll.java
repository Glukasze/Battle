package com.gracjan.app.actors;

public class Troll extends Actor {
    public Troll() {
        super(ActorType.TROLL, "\033[1;31mt\033[0m", "monster", 100, 5, 15);
    }
}
