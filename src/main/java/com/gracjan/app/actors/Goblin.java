package com.gracjan.app.actors;

public class Goblin extends Actor {
    public Goblin() {
        super(ActorType.GOBLIN, "\033[0;31mg\033[0m", "monster", 10, 2, 6);
    }
}
