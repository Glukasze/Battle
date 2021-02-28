package com.gracjan.app.actors;

public class Dragon extends Actor {
    public Dragon() {
        super(ActorType.DRAGON, "\033[1;31mD\033[0m", "monster", 250, 10, 40);
    }
}
