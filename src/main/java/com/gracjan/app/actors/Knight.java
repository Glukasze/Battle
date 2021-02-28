package com.gracjan.app.actors;

public class Knight extends Actor {
    public Knight() {
        super(ActorType.KNIGHT, "\033[1;34mk\u001B[0m", "human", 50, 15, 20);
    }
}
